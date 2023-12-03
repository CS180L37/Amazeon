package models;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

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
    public static CollectionReference sellersCollection;

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
        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), "productIds");
        this.products = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
        ArrayList<Integer> saleIds = Utils.firestoreDocToIDArray(document.getData(), "saleIds");
        this.sales = Sale.getSalesByIds((saleIds != null) ? saleIds : new ArrayList<Integer>());
        this.documentReference = getSellerDocument();
    }

    // Utility method for retrieving a customers document by id
    private DocumentReference getSellerDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .whereEqualTo("sellerId", this.getSellerId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if sellerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the seller document");
        }
        return documents.get(0).getReference();
    }

    private static int getNextSellerId() throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection.orderBy("sellerId", Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("sellerId").intValue() + 1;
    }

    public void deleteSeller() throws IOException {
        this.documentReference.delete();
    }

    public static Seller getSellerById(int sellerId) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .where(Filter.equalTo("sellerId", sellerId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Seller(documents.get(0));
    }

    public static ArrayList<Seller> getSellersByIds(ArrayList<Integer> sellerIds) throws IOException {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        for (int id : sellerIds) {
            Seller seller = getSellerById(id);
            if (seller != null) {
                sellers.add(seller);
            }
        }
        return sellers;
    }

    // Called in login
    public static Boolean sellerExists(String email, String password) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
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
        sellersCollection.add(sellerData);
        // Create a new instance
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Sale> sales = new ArrayList<Sale>();
        return new Seller(sellerId, name, email, password, products, sales);
    }

    // Called to retrieve a specific seller
    public static Seller getSellerByEmail(String email) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .where(Filter.equalTo("email", email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Seller(documents.get(0));
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        // Set locally
        this.sellerId = sellerId;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("sellerId", sellerId);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Set locally
        this.name = name;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", name);
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        // Set locally
        this.products = products;
        // Set on the backend
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

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        // Set locally
        this.sales = sales;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        ArrayList<Integer> saleIds = new ArrayList<Integer>();
        for (Sale sale : sales) {
            saleIds.add(sale.getSaleId());
        }
        data.put("saleIds", saleIds);
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

    public void removeProduct(Product product) {
        // Set locally
        this.products.remove(product);
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

    public void addSale(Sale sale) {
        // Set locally
        this.sales.add(sale);
        // Set associated product ids on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        ArrayList<Integer> saleIds = new ArrayList<Integer>();
        for (Sale salesMade : sales) {
            saleIds.add(salesMade.getSaleId());
        }
        data.put("saleIds", saleIds);
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
                    sellerId: %d
                    name: %s
                    email: %s
                    password: %s
                    products: %s
                    sales: %s
                }""", this.getSellerId(), this.getName(),
                this.getEmail(),
                this.getPassword(),
                this.getProducts().toString(), this.getSales().toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Seller) {
            Seller seller = (Seller) obj;
            if (seller.getSellerId() == this.getSellerId() && seller.getName().equals(this.getName())
                    && seller.getEmail().equals(this.getEmail())
                    && seller.getPassword().equals(this.getPassword()) && seller.getProducts() == this.getProducts()
                    && seller.getSales() == this.getSales()) {
                return true;
            }
        }
        return false;
    }
}