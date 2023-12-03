package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import utils.Utils;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.io.IOException;

public class Product {
    private int productId;
    private String name;
    private int quantity;
    private String description;
    private double price;
    private int sellerId;
    private int storeId;

    private DocumentReference documentReference;

    public static CollectionReference productsCollection;

    private Product(int productId, String name, int quantity, String description,
            double price, int sellerId, int storeId) throws IOException {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.sellerId = sellerId;
        this.storeId = storeId;
        this.documentReference = getProductDocument();
    }

    private Product(QueryDocumentSnapshot document) throws IOException {
        int productId = document.getLong("productId").intValue();
        this.name = document.getString("name");
        this.productId = productId;
        this.quantity = document.getLong("quantity").intValue();
        this.description = document.getString("description");
        this.price = document.getLong("price").doubleValue();
        int sellerId = document.getLong("sellerId").intValue();
        this.sellerId = sellerId;
        int storeId = document.getLong("storeId").intValue();
        this.storeId = storeId;
        this.documentReference = getProductDocument();
    }

    private DocumentReference getProductDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .whereEqualTo("productId", this.getProductId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the product document");
        }
        return documents.get(0).getReference();
    }

    public static Product createProduct(String description, String name, int price, int productId, int quantity,
            int sellerId, int storeId) throws IOException {
        Map<String, Object> productData = new HashMap<String, Object>();
        // Add data to db
        productData.put("productId", productId);
        productData.put("name", name);
        productData.put("price", price);
        productData.put("description", description);
        productData.put("quantity", quantity);
        productData.put("sellerId", sellerId);
        productData.put("storeId", storeId);
        productsCollection.add(productData);
        // Create a new instance
        return new Product(productId, name, price, description, quantity, sellerId, storeId);
    }

    public void deleteProduct() throws IOException {
        this.documentReference.delete();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("productId", productId);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public int getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("sellerId", sellerId);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("quantity", quantity);
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
        this.name = name;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", name);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("storeId", storeId);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("description", description);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public double getPrice() {
        return price;

    }

    public void setPrice(double price) {
        this.price = price;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("price", price);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static Product getProductById(int id) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .where(Filter.equalTo("productId", id)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static ArrayList<Product> getProductsByIds(ArrayList<Integer> productIds) throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int productID : productIds) {
            Product product = getProductById(productID);
            if (product != null) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static int getNextProductId() throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection.orderBy("productId", Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("productId").intValue() + 1;
    }

    @Override
    public String toString() {
        return String.format("""
                {
                    productId: %d
                    name: %s
                    quantity: %d
                    description: %s
                    price: %f
                    sellerId: %d
                    storeId: %d
                }""", this.getProductId(), this.getName(), this.getQuantity(), this.getDescription(), this.getPrice(),
                this.getSellerId(), this.getStoreId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product product = (Product) obj;
            if (product.getProductId() == this.getProductId() && product.getName().equals(this.getName())
                    && product.getQuantity() == this.getQuantity()
                    && product.getDescription().equals(this.getDescription())
                    && product.getPrice() == this.getPrice() && product.getSellerId() == this.getSellerId()
                    && product.getStoreId() == this.getStoreId()) {
                return true;
            }
        }
        return false;
    }
}
