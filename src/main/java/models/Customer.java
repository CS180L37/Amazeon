package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Filter;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query.Direction;

import utils.Utils;
import utils.fields;

import static utils.Utils.DOWNLOADS;
import static utils.Utils.createWriter;

import java.io.BufferedWriter;
import java.io.IOException;

public class Customer {
    private Cart cart;
    private int customerId;
    private String email;
    private String password;
    private boolean isDeleted;

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
        this.isDeleted = false;
        this.documentReference = getCustomerDocument();
    }

    // Create a Customer instance from a document (existing data)
    // Null safety only needs to be handled for instances that can potentially be
    // null,
    // such as productIds
    private Customer(QueryDocumentSnapshot document) throws IOException {
        int id = document.getLong(fields.customerId).intValue();
        this.cart = Cart.getCartById(id);
        this.customerId = id;
        this.email = document.getString(fields.email);
        this.password = document.getString(fields.password);
        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), fields.productIds);
        this.products = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
        this.isDeleted = document.getBoolean(fields.isDeleted).booleanValue();
        this.documentReference = getCustomerDocument();
    }

    // Utility method for retrieving a customers document by id
    // Only called once to minimize queries for efficiency
    private DocumentReference getCustomerDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .whereEqualTo(fields.customerId, this.getCustomerId())
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
        ApiFuture<QuerySnapshot> future = customersCollection.orderBy(fields.customerId, Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong(fields.customerId).intValue() + 1;
    }

    // Frontend should redirect the user to the authentication page upon account
    // deletion
    // public void deleteCustomer() throws IOException {
    // try {
    // this.getCart().deleteCart();
    // this.documentReference.delete().get();
    // } catch (InterruptedException | ExecutionException e) {
    // e.printStackTrace();
    // }
    // }

    public static Customer getCustomerById(int customerId) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .where(Filter.equalTo(fields.customerId, customerId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Customer(documents.get(0));
    }

    public static Customer getNonDeletedCustomerById(int customerId) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .where(Filter.equalTo(fields.customerId, customerId)).whereNotEqualTo(fields.isDeleted, true).limit(1)
                .get();
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

    public static ArrayList<Customer> getNonDeletedCustomersByIds(ArrayList<Integer> customerIds) throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        for (int id : customerIds) {
            Customer customer = getNonDeletedCustomerById(id);
            if (customer != null) {
                customers.add(customer);
            }
        }
        return customers;
    }

    public static ArrayList<Customer> sortCustomers(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection.orderBy(field, direction).get();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            customers.add(new Customer(doc));
        }
        return customers;
    }

    public static ArrayList<Customer> sortNonDeletedCustomers(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection.orderBy(fields.isDeleted)
                .whereNotEqualTo(fields.isDeleted, true)
                .orderBy(field, direction).get();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            customers.add(new Customer(doc));
        }
        return customers;
    }

    public static ArrayList<Customer> sortNonDeletedCustomersByNumProducts()
            throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection.orderBy(fields.isDeleted)
                .whereNotEqualTo(fields.isDeleted, true).get();
        TreeMap<Integer, List<Customer>> sortedCustomers = new TreeMap<Integer, List<Customer>>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            Customer customer = new Customer(doc);
            ApiFuture<QuerySnapshot> saleFuture = Sale.salesCollection
                    .whereEqualTo(fields.customerId, customer.getCustomerId()).get();
            List<QueryDocumentSnapshot> saleDocuments = Utils.retrieveData(saleFuture);
            if (saleDocuments == null) {
                continue;
            }
            int numProductsSold = 0;
            for (QueryDocumentSnapshot saleDoc : saleDocuments) {
                numProductsSold += saleDoc.getLong(fields.numPurchased).intValue();
            }
            if (sortedCustomers.containsKey(numProductsSold)) {
                sortedCustomers.get(numProductsSold).add(customer);
                sortedCustomers.put(numProductsSold, sortedCustomers.get(customer));
            }
            sortedCustomers.put(numProductsSold, List.of(customer));
        }
        NavigableMap<Integer, List<Customer>> sortedCustomersDescending = sortedCustomers.descendingMap();
        for (List<Customer> customerList : sortedCustomersDescending.values()) {
            for (Customer customer : customerList) {
                customers.add(customer);
            }
        }
        return customers;
    }

    // Called in login
    public static Boolean customerExists(String email, String password) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .where(Filter.equalTo(fields.email, email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents != null) ? true : false;
    }

    // Called to create a customer
    public static Customer createCustomer(String email, String password) throws IOException {
        if (!Utils.validateEmail(email)) {
            throw new IOException("Invalid email");
        }
        if (!Utils.validatePassword(password)) {
            throw new IOException("Invalid password");
        }
        Map<String, Object> customerData = new HashMap<String, Object>();
        int customerId = Customer.getNextCustomerId();
        // Add data to db
        customerData.put("cartId", customerId);
        customerData.put(fields.customerId, customerId);
        customerData.put(fields.email, email);
        customerData.put(fields.password, password);
        customerData.put(fields.productIds, Arrays.asList());
        customerData.put(fields.isDeleted, false);
        try {
            customersCollection.add(customerData).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // Create a new instance
        Cart cart = Cart.createCart(customerId);
        return new Customer(cart, customerId, email, password, new ArrayList<Product>());
    }

    // Called to retrieve a specific customer
    public static Customer getCustomerByEmail(String email) throws IOException {
        ApiFuture<QuerySnapshot> future = customersCollection
                .where(Filter.equalTo(fields.email, email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Customer(documents.get(0));
    }

    // Write file to user
    public boolean exportPurchaseHistory() {
        BufferedWriter bw;
        try {
            bw = createWriter(DOWNLOADS
                    + "purchase_history.csv");
            for (Product product : getProducts()) {
                bw.write(String.format("%d,%s,%d,%s,%f", product.getProductId(), product.getName(),
                        product.getQuantity(), product.getDescription(), product.getPrice()));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
        data.put(fields.customerId, customerId);
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
        data.put(fields.email, email);
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
        data.put(fields.password, password);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        // Set locally
        this.isDeleted = isDeleted;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.isDeleted, isDeleted);
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
        data.put(fields.productIds, productIds);
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
        data.put(fields.productIds, productIds);
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
                    isDeleted: %b
                }""", this.getCart().toString(), this.getCustomerId(), this.getEmail(), this.getPassword(),
                this.getProducts(), this.isDeleted());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {
            Customer customer = (Customer) obj;
            if (customer.getCart().equals(this.getCart()) && customer.getCustomerId() == this.getCustomerId()
                    && customer.getEmail().equals(this.getEmail())
                    && customer.getPassword().equals(this.getPassword())
                    && customer.getProducts().equals(this.getProducts()) && customer.isDeleted() == this.isDeleted()) {
                return true;
            }
        }
        return false;
    }
}
