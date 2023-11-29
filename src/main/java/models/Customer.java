package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Filter;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query.Direction;

import utils.Utils;

import java.io.IOException;

public class Customer {
    private Cart cart;
    private int customerId;
    private String email;
    private String password;

    private ArrayList<Product> products;
    private static CollectionReference customerCollection = Utils.db.collection("customers");

    // Create a Customer from all the fields
    private Customer(Cart cart, int customerId, String email, String password, ArrayList<Product> products) {
        this.cart = cart;
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.products = products;
    }

    // Create a Customer instance from a document (existing data)
    // Null safety only needs to be handled for instances that can potentially be
    // null,
    // such as productIds
    private Customer(QueryDocumentSnapshot document) {
        int id = document.getLong("customerId").intValue();
        this.cart = Cart.getCartById(id);
        this.customerId = id;
        this.email = document.getString("email");
        this.password = document.getString("password");
        List<Integer> productIds = (List<Integer>) document.getData().get("productIds");
        this.products = Product.getProductsByIds((productIds != null) ? productIds : Arrays.asList());
    }

    // Utility method for retrieving a customers document by id
    private DocumentReference getCustomerDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = customerCollection
                .whereEqualTo("customerId", this.getCustomerId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getReference();
    }

    private static int getNextCustomerId() throws IOException {
        ApiFuture<QuerySnapshot> future = customerCollection.orderBy("customerId", Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("customerId").intValue() + 1;
    }

    // Frontend should redirect the user to the authentication page upon account
    // deletion
    public void deleteCustomer() throws IOException {
        getCustomerDocument().delete();
    }

    public static Customer getCustomerById(int customerId) throws IOException {
        ApiFuture<QuerySnapshot> future = customerCollection.select("customerId")
                .where(Filter.equalTo("customerId", customerId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Customer(documents.get(0));
    }

    public static ArrayList<Customer> getCustomersByIds(List<Integer> customerIds) throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        for (int id : customerIds) {
            customers.add(getCustomerById(id));
        }
        return customers;
    }

    // Called in login
    public static Boolean customerExists(String email, String password) throws IOException {
        ApiFuture<QuerySnapshot> future = customerCollection.select("email")
                .where(Filter.equalTo("email", email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents != null) ? true : false;
    }

    // Called to create a customer
    public static Customer createCustomer(String email, String password) throws IOException {
        Map<String, Object> customerData = new HashMap<String, Object>();
        int customerId = Customer.getNextCustomerId();
        // Add data to db
        customerData.put("cartId", customerId);
        customerData.put("customerId", customerId);
        customerData.put("email", email);
        customerData.put("password", password);
        customerData.put("productIds", Arrays.asList());
        customerCollection.add(customerData);
        // Create a new instance
        Cart cart = Cart.createCart(customerId);
        return new Customer(cart, customerId, email, password, new ArrayList<Product>());
    }

    // Called to retrieve a specific customer
    public static Customer getCustomer(String email) throws IOException {
        ApiFuture<QuerySnapshot> future = customerCollection.select("email")
                .where(Filter.equalTo("email", email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Customer(documents.get(0));
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) throws IOException {
        // Set locally
        this.cart = cart;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("cartId", cart.getCustomerID());
        this.getCustomerDocument().update(data);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) throws IOException {
        // Set locally
        this.customerId = customerId;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("customerId", customerId);
        this.getCustomerDocument().update(data);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IOException {
        if (!Utils.validateEmail(email)) {
            throw new IOException("Invalid email");
        }
        // Set locally
        this.email = email;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("email", email);
        this.getCustomerDocument().update(data);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IOException {
        if (!Utils.validatePassword(password)) {
            throw new IOException("Invalid password");
        }
        // Set locally
        this.password = password;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("password", password);
        this.getCustomerDocument().update(data);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) throws IOException {
        // Set locally
        this.products = products;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        ArrayList<Integer> productIds = new ArrayList<Integer>();
        for (Product product : products) {
            productIds.add(product.getProductId());
        }
        data.put("productIds", productIds);
        this.getCustomerDocument().update(data);
    }
}
