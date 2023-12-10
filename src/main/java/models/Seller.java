package models;

import static utils.Utils.DOWNLOADS;
import static utils.Utils.createReader;
import static utils.Utils.createWriter;

import java.io.*;
import java.lang.reflect.Array;
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
import utils.fields;

public class Seller {
    private int sellerId;
    private String name;
    private String email;
    private String password;
    private boolean isDeleted;
    private ArrayList<Product> products;
    private ArrayList<Sale> sales;

    // For writing and updating operations on the current instance
    // For reading use the collection and queries
    private DocumentReference documentReference;

    // NOTE no setters and getters for sellerCollection;
    // the field should not be accessible by the frontend
    public static CollectionReference sellersCollection;

    private Seller(int sellerId, String name, String email, String password, ArrayList<Product> products,
            ArrayList<Sale> sales) throws IOException {
        this.sellerId = sellerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.products = products;
        this.sales = sales;
        this.isDeleted = false;
        this.documentReference = getSellerDocument();
    }

    private Seller(QueryDocumentSnapshot document) throws IOException {
        this.sellerId = document.getLong(fields.sellerId).intValue();
        this.name = document.getString(fields.name);
        this.email = document.getString(fields.email);
        this.password = document.getString(fields.password);
        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), fields.productIds);
        this.products = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
        ArrayList<Integer> saleIds = Utils.firestoreDocToIDArray(document.getData(), fields.saleIds);
        this.sales = Sale.getSalesByIds((saleIds != null) ? saleIds : new ArrayList<Integer>());
        this.isDeleted = document.getBoolean(fields.isDeleted).booleanValue();
        this.documentReference = getSellerDocument();
    }

    // Utility method for retrieving a customers document by id
    private DocumentReference getSellerDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .whereEqualTo(fields.sellerId, this.getSellerId())
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
        ApiFuture<QuerySnapshot> future = sellersCollection.orderBy(fields.sellerId, Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong(fields.sellerId).intValue() + 1;
    }

    // Write products to downloads
    public boolean exportProducts() {
        BufferedWriter bw;
        try {
            bw = createWriter(DOWNLOADS
                    + "products.csv");
            for (Product product : getProducts()) {
                bw.write(String.format("%d,%s,%d,%s,%f,%d,%d", product.getProductId(), product.getName(),
                        product.getQuantity(), product.getDescription(), product.getPrice(), product.getSellerId(),
                        product.getStoreId()));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Import products from downloads
    public ArrayList<Product> importProducts(String path) {
        BufferedReader br;
        try {
            br = createReader(path);
            String line = "";
            ArrayList<Product> newProducts = new ArrayList<Product>();
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                int id = Integer.parseInt(line.split(",")[0]);
                newProducts.add(Product.getProductById(id));
            }
            return newProducts;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public static ArrayList<Seller> sortSellers(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection.orderBy(field, direction).get();
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            sellers.add(new Seller(doc));
        }
        return sellers;
    }

    public static ArrayList<Seller> sortNonDeletedSellers(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection.orderBy(fields.isDeleted)
                .whereNotEqualTo(fields.isDeleted, true)
                .orderBy(field, direction).get();
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            sellers.add(new Seller(doc));
        }
        return sellers;
    }

    public static Seller getSellerById(int sellerId) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .where(Filter.equalTo(fields.sellerId, sellerId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Seller(documents.get(0));
    }

    public static Seller getNonDeletedSellerById(int sellerId) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .where(Filter.equalTo(fields.sellerId, sellerId)).whereNotEqualTo(fields.isDeleted, true).limit(1)
                .get();
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

    public static ArrayList<Seller> getNonDeletedSellersByIds(ArrayList<Integer> sellerIds) throws IOException {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        for (int id : sellerIds) {
            Seller seller = getNonDeletedSellerById(id);
            if (seller != null) {
                sellers.add(seller);
            }
        }
        return sellers;
    }

    // Called in login
    public static Boolean sellerExists(String email, String password) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .where(Filter.equalTo(fields.email, email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents != null) ? true : false;
    }

    // Called to create a seller
    public static Seller createSeller(String email, String password, String name) throws IOException {
        if (!Utils.validateEmail(email)) {
            throw new IOException("Invalid email");
        }
        if (!Utils.validatePassword(password)) {
            throw new IOException("Invalid password");
        }
        Map<String, Object> sellerData = new HashMap<String, Object>();
        int sellerId = Seller.getNextSellerId();
        // Add data to db
        sellerData.put(fields.sellerId, sellerId);
        sellerData.put(fields.name, name);
        sellerData.put(fields.email, email);
        sellerData.put(fields.password, password);
        sellerData.put(fields.productIds, Arrays.asList());
        sellerData.put(fields.saleIds, Arrays.asList());
        sellerData.put(fields.isDeleted, false);
        try {
            sellersCollection.add(sellerData).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // Create a new instance
        ArrayList<Product> products = new ArrayList<Product>();
        ArrayList<Sale> sales = new ArrayList<Sale>();
        return new Seller(sellerId, name, email, password, products, sales);
    }

    // Called to retrieve a specific seller
    public static Seller getSellerByEmail(String email) throws IOException {
        ApiFuture<QuerySnapshot> future = sellersCollection
                .where(Filter.equalTo(fields.email, email)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Seller(documents.get(0));
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        // Set locally
        this.sellerId = sellerId;
        // Set on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.sellerId, sellerId);
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
        data.put(fields.name, name);
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
        data.put(fields.productIds, productIds);
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
        data.put(fields.saleIds, saleIds);
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

    public void removeProduct(Product product) {
        // Set locally
        this.products.remove(product);
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

    public void addSale(Sale sale) {
        // Set locally
        this.sales.add(sale);
        // Set associated product ids on the backend
        HashMap<String, Object> data = new HashMap<String, Object>();
        ArrayList<Integer> saleIds = new ArrayList<Integer>();
        for (Sale salesMade : sales) {
            saleIds.add(salesMade.getSaleId());
        }
        data.put(fields.saleIds, saleIds);
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
                    isDeleted: %b
                }""", this.getSellerId(), this.getName(),
                this.getEmail(),
                this.getPassword(),
                this.getProducts().toString(), this.getSales().toString(), this.isDeleted());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Seller) {
            Seller seller = (Seller) obj;
            if (seller.getSellerId() == this.getSellerId() && seller.getName().equals(this.getName())
                    && seller.getEmail().equals(this.getEmail())
                    && seller.getPassword().equals(this.getPassword())
                    && seller.getProducts().equals(this.getProducts())
                    && seller.getSales().equals(this.getSales()) && seller.isDeleted() == this.isDeleted()) {
                return true;
            }
        }
        return false;
    }
}