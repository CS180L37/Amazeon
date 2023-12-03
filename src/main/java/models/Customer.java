package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

    // For writing and updating operations on the current instance
    // For reading use the collection and queries
    private DocumentReference documentReference;

    public static CollectionReference customersCollection;

    // Create a Customer from all the fields
    private Customer(Cart cart, int customerId, String email, String password, ArrayList<Product> products)
            throws IOException {
        this.cart = cart;
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.products = products;
        this.documentReference = getCustomerDocument();
    }

    // Create a Customer instance from a document (existing data)
    // Null safety only needs to be handled for instances that can potentially be
    // null,
    // such as productIds
    private Customer(QueryDocumentSnapshot document) throws IOException {
        int id = document.getLong("customerId").intValue();
        this.cart = Cart.getCartById(id);
        this.customerId = id;
        this.email = document.getString("email");
        this.password = document.getString("password");
        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), "productIds");
        this.products = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
        this.documentReference = getCustomerDocument();
    }

    // Utility method for retrieving a customers document by id
    // Only called once to minimize queries for efficiency
    private DocumentReference getCustomerDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .whereEqualTo("customerId", this.getCustomerId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the customer document");
        }
        return documents.get(0).getReference();
    }

    private static int getNextCustomerId() throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection.orderBy("customerId", Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("customerId").intValue() + 1;
    }

    // Frontend should redirect the user to the authentication page upon account
    // deletion
    public void deleteCustomer() throws IOException {
        this.documentReference.delete();
    }

    public static Customer getCustomerById(int customerId) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .where(Filter.equalTo("customerId", customerId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Customer(documents.get(0));
    }

    public static ArrayList<Customer> getCustomersByIds(ArrayList<Integer> customerIds) throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        for (int id : customerIds) {
            Customer customer = getCustomerById(id);
            if (customer != null) {
                customers.add(customer);
            }
        }
        return customers;
    }

    // Called in login
    public static Boolean customerExists(String email, String password) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
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
        customersCollection.add(customerData);
        // Create a new instance
        Cart cart = Cart.createCart(customerId);
        return new Customer(cart, customerId, email, password, new ArrayList<Product>());
    }

    // Called to retrieve a specific customer
    public static Customer getCustomerByEmail(String email) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .where(Filter.equalTo("email", email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Customer(documents.get(0));
    }

    public Cart getCart() {
        return cart;
    }

    // This method is unnecessary because you're never going to need a new cart
    // instance
    // You'll just modify the existing cart via cart model methods
    // public void setCartId(Cart cart) throws IOException {
    // // Set locally
    // this.cart = cart;
    // // Set on the backend
    // HashMap<String, Object> data = new HashMap<String, Object>();
    // data.put("cartId", cart.getCustomerID());
    // this.documentReference.update(data);
    // }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        // Set locally
        this.customerId = customerId;
        // If customerId changes, cartId for the instance should change as well
        this.getCart().setCustomerID(customerId);
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("customerId", customerId);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    /// Throws an IOException if the email is invalid
    public void setEmail(String email) throws IOException {
        if (!Utils.validateEmail(email)) {
            throw new IOException("Invalid email");
        }
        // Set locally
        this.email = email;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("email", email);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getPassword() {
        return password;
    }

    /// Throws an IOException if the password is invalid
    public void setPassword(String password) throws IOException {
        if (!Utils.validatePassword(password)) {
            throw new IOException("Invalid password");
        }
        // Set locally
        this.password = password;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("password", password);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // With array lists, you should always call the supported and add, remove, and
    // set methods in the class
    // If you get the field and then add manually, the database WILL NOT sync the
    // changes
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        // Set locally
        this.products = products;
        // Set associated product ids on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        ArrayList<Integer> productIds = new ArrayList<Integer>();
        for (Product product : products) {
            productIds.add(product.getProductId());
        }
        data.put("productIds", productIds);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        // Set locally
        this.products.add(product);
        // Set associated product ids on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        ArrayList<Integer> productIds = new ArrayList<Integer>();
        for (Product productPurchased : products) {
            productIds.add(productPurchased.getProductId());
        }
        data.put("productIds", productIds);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("""
                {
                    cart: %s
                    customerId: %d
                    email: %s
                    password: %s
                    products: %s
                }""", this.getCart().toString(), this.getCustomerId(), this.getEmail(), this.getPassword(),
                this.getProducts());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {
            Customer customer = (Customer) obj;
            if (customer.getCart().equals(this.getCart()) && customer.getCustomerId() == this.getCustomerId()
                    && customer.getEmail().equals(this.getEmail())
                    && customer.getPassword().equals(this.getPassword())
                    && customer.getProducts().equals(this.getProducts())) {
                return true;
            }
        }
        return false;
    }
}
