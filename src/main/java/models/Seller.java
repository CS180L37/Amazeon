package models;

import java.io.*;
import java.util.*;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Filter;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import utils.Utils;

public class Seller {
    private int sellerId;
    private String name;
    private String email;
    private String password;
    private ArrayList<Product> products;
    private ArrayList<Sale> sales;

    // For writing and updating operations on the current instance
    // For reading use the collection and queries
    private DocumentReference documentReference;

    // NOTE no setters and getters for sellerCollection;
    // the field should not be accessible by the frontend
    private static CollectionReference sellerCollection = Utils.db.collection("sellers");

    protected Seller(int sellerId, String name, String email, String password, ArrayList<Product> products,
            ArrayList<Sale> sales) throws IOException {
        this.sellerId = sellerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.products = products;
        this.sales = sales;
        this.documentReference = getSellerDocument();
    }

    private Seller(QueryDocumentSnapshot document) throws IOException {
        this.sellerId = document.getLong("sellerId").intValue();
        this.name = document.getString("name");
        this.email = document.getString("email");
        this.password = document.getString("password");
        List<Integer> productIds = (List<Integer>) document.getData().get("productIds");
        this.products = Product.getProductsByIds((productIds != null) ? productIds : Arrays.asList());
        List<Integer> saleIds = (List<Integer>) document.getData().get("saleIds");
        this.sales = Sale.getSalesByIds((saleIds != null) ? saleIds : Arrays.asList());
        this.documentReference = getSellerDocument();
    }

    // Utility method for retrieving a customers document by id
    private DocumentReference getSellerDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = sellerCollection
                .whereEqualTo("sellerId", this.getSellerId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getReference();
    }

    private static int getNextSellerId() throws IOException {
        ApiFuture<QuerySnapshot> future = sellerCollection.orderBy("sellerId", Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("sellerId").intValue() + 1;
    }

    public void deleteSeller() throws IOException {
        this.documentReference.delete();
    }

    public static Seller getSellerById(int sellerId) throws IOException {
        ApiFuture<QuerySnapshot> future = sellerCollection.select("sellerId")
                .where(Filter.equalTo("sellerId", sellerId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Seller(documents.get(0));
    }

    public static ArrayList<Seller> getSellersByIds(List<Integer> sellerIds) throws IOException {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        for (int id : sellerIds) {
            sellers.add(getSellerById(id));
        }
        return sellers;
    }

    // Called in login
    public static Boolean sellerExists(String email, String password) throws IOException {
        ApiFuture<QuerySnapshot> future = sellerCollection.select("email")
                .where(Filter.equalTo("email", email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents != null) ? true : false;
    }

    // Called to create a seller
    public static Seller createSeller(String email, String password, String name) throws IOException {
        Map<String, Object> sellerData = new HashMap<String, Object>();
        int sellerId = Seller.getNextSellerId();
        // Add data to db
        sellerData.put("sellerId", sellerId);
        sellerData.put("name", name);
        sellerData.put("email", email);
        sellerData.put("password", password);
        sellerData.put("productIds", Arrays.asList());
        sellerData.put("saleIds", Arrays.asList());
        sellerCollection.add(sellerData);
        // Create a new instance
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Sale> sales = new ArrayList<Sale>();
        return new Seller(sellerId, name, email, password, products, sales);
    }

    // Called to retrieve a specific seller
    public static Seller getSellerByEmail(String email) throws IOException {
        ApiFuture<QuerySnapshot> future = sellerCollection.select("email")
                .where(Filter.equalTo("email", email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Seller(documents.get(0));
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) throws IOException {
        // Set locally
        this.sellerId = sellerId;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("sellerId", sellerId);
        this.documentReference.update(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IOException {
        // Set locally
        this.name = name;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", name);
        this.documentReference.update(data);
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
        this.documentReference.update(data);
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
        this.documentReference.update(data);
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
        this.documentReference.update(data);
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) throws IOException {
        // Set locally
        this.sales = sales;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        ArrayList<Integer> saleIds = new ArrayList<Integer>();
        for (Sale sale : sales) {
            saleIds.add(sale.getSaleId());
        }
        data.put("saleIds", saleIds);
        this.documentReference.update(data);
    }
}