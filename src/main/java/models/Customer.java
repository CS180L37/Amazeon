package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;
import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiService.Listener;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;

import utils.Utils;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Customer {
    private Cart cart;
    private int customerId;
    private String email;
    private String password;

    private ArrayList<Product> products;

    // Create a Customer from all the fields
    private Customer(Cart cart, int customerId, String email, String password, ArrayList<Product> products) {
        this.cart = cart;
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.products = products;
    }

    // Create a Customer from a document
    private Customer(QueryDocumentSnapshot document) {
        document.get(email)
    }

    public int getId() {
        if (cart == null) {
            return -1;
        }
        return cart.getCustomerID();
    }

    // Purchases a product
    public void purchaseProduct(Product product) {
        // Add to customers products
        for (int i = 0; i < cart.getCartProducts().size(); i++) { // goes through every product in cart products
            for (int j = 0; j < Amazeon.getSellerById(cart.getCartProducts().get(i).getSellerId()).getSales()
                    .size(); j++) { // gets each product's seller id and uses seller id to get seller --> which then
                                    // gets the seller's list of sales
                if (Amazeon.getSellerById(cart.getCartProducts().get(i).getSellerId()).getSales().get(j).getProduct()
                        .getProductId() == cart.getCartProducts().get(i).getProductId()) { // uses each sale to get
                                                                                           // product's id and compares
                                                                                           // this value to cartProducts
                                                                                           // product at index i
                    Sale sale = new Sale(
                            Amazeon.getSellerById(cart.getCartProducts().get(i).getSellerId()).getSales().get(j)
                                    .getSaleId(),
                            Amazeon.getCustomerById(this.getId()), cart.getCartProducts().get(i),
                            cart.getCartProducts().get(i).getQuantity()); // purchases product
                    break;
                }
            }
        }
        // throw new UnsupportedOperationException("Unsupported operation
        // 'purchaseProduct");
    }

    public void editAccount(String email, String password) {
        if (Utils.validateEmail(email)) {
            this.setEmail(email);
        } else {
            System.out.println("Make sure to enter a valid email if you want to change your email!");
        }
        if (Utils.validatePassword(password)) {
            this.setPassword(password);
        } else {
            System.out.println("Make sure to enter a valid email if you want to change your email!");
        }
    }

    @Override
    public void deleteAccount() {
        Amazeon.customers.remove(this);
    }

    // TODO: adapt these for backend
    public static Customer getCustomerById(int customerId) {
        for (Customer customer : Amazeon.customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return new Customer(-1, "", "", new ArrayList<>(), new Cart(-1, new ArrayList<>()));
    }

    public static ArrayList<Customer> getCustomersByIds(ArrayList<Integer> customerIds) {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        for (int customerId : customerIds) {
            customerList.add(Amazeon.getCustomerById(customerId));
        }
        return customerList;
    }

    public static int getNextCustomerId() {
        // int customerListSize = customers.size() - 1;
        // if (customerListSize < 0) {
        // return 1;
        // }
        // return customers.get(customerListSize).getId() + 1;
        return 0;
    }

    // Called in login
    public static Boolean customerExists(String email, String password) throws IOException {
        ApiFuture<QuerySnapshot> future = Utils.db.collection("customers").select("email", email).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents != null) ? true : false;
    }

    // Called to create a customer
    public static Customer createCustomer(String email, String password) {
        Map<String, Object> customerData = new HashMap<String, Object>();
        int customerId = Customer.getNextCustomerId();
        // Add data to db
        customerData.put("cartId", customerId);
        customerData.put("customerId", customerId);
        customerData.put("email", email);
        customerData.put("password", password);
        customerData.put("productIds", Arrays.asList());
        Utils.db.collection("customers").add(customerData);
        // Create a new instance
        Cart cart = Cart.createCart(customerId);
        return new Customer(cart, customerId, email, password, new ArrayList<Product>());
    }

    // Called to retrieve a customer
    public static Customer getCustomer(String email) throws IOException {
        ApiFuture<QuerySnapshot> future = Utils.db.collection("customers").select("email", email).limit(1).get();
        List<QueryDocumentSnapshot> documents;
        documents = Utils.retrieveData(future);
        return new Customer(documents.get(0));
    }
}
