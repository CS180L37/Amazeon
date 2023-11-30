package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import utils.Utils;

import java.util.*;
import java.io.IOException;

public class Product {
    private static int productId;
    private String name;
    private int quantity;
    private String description;
    private double price;
    private int sellerId;
    private int storeId;

    private DocumentReference documentReference;

    private static CollectionReference productCollection = Utils.db.collection("products");

    private Product(String name, int quantity, String description,
            double price, int sellerId, int storeId) {
        int id = document.getLong("productId").intValue();
        this.productId = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.sellerId = sellerId;
        this.storeId = storeId;
    }

    private Product (QueryDocumentSnapshot document) throws IOException {
        int productId = document.getLong("productId").intValue();
        this.name = document.getString("name");
        this.productId = productId;
        this.quantity = document.getLong("quantity").intValue();
        this.description = document.getString(description);
        this.price = document.getLong("price").intValue();
        int sellerId = document.getLong("sellerId").intValue();
        this.sellerId = sellerId;
        int storeId = document.getLong("storeId").intValue();
        this.storeId = storeId;
    }

        // TODO: alternative constructor
        public static Product createProduct(String description, String name, int price, int productId, int quantity, int sellerId, int storeId) throws IOException {
            Map<String, Object> productData = new HashMap<String, Object>();
            // Add data to db
            productData.put("description", description);
            productData.put("name", name);
            productData.put("price", price);
            productData.put("productId", productId);
            productData.put("quantity", quantity);
            productData.put("sellerId", sellerId);
            productData.put("storeId", storeId);
            productCollection.add(productData);
            // Create a new instance
            return new Product(productId, name, price, description, quantity, sellerId, storeId);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // TODO: adapt these for backend
    public static Product getProductById(int Id) throws IOException {
            ApiFuture<QuerySnapshot> future = productCollection.select("productId")
                    .where(Filter.equalTo("productId", productId)).limit(1).get();
            List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
            return new Product(documents.get(0));
            }

    public static ArrayList<Product> getProductsByIds(List<Integer> productIds) throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int productID : productIds) {
            productList.add(getProductById(productID));
        }
        return productList;
    }

    public static int getNextProductId() throws IOException {
            ApiFuture<QuerySnapshot> future = productCollection.orderBy("productId", Query.Direction.DESCENDING)
                    .limit(1).get();
            List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
            return documents.get(0).getLong("productId").intValue() + 1;
    }
}
