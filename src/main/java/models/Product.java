package models;

import models.Store;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query.Direction;

import utils.Utils;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.io.IOException;
import utils.fields;

public class Product {
    private int productId;
    private String name;
    private int quantity;
    private String description;
    private double price;
    private int sellerId;
    private int storeId;
    private boolean isDeleted;
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
        this.isDeleted = false;
        this.documentReference = getProductDocument();
    }

    private Product(QueryDocumentSnapshot document) throws IOException {
        int productId = document.getLong(fields.productId).intValue();
        this.name = document.getString(fields.name);
        this.productId = productId;
        this.quantity = document.getLong(fields.quantity).intValue();
        this.description = document.getString(fields.description);
        this.price = document.getDouble(fields.price).doubleValue();
        int sellerId = document.getLong(fields.sellerId).intValue();
        this.sellerId = sellerId;
        int storeId = document.getLong(fields.storeId).intValue();
        this.storeId = storeId;
        this.isDeleted = document.getBoolean(fields.isDeleted).booleanValue();
        this.documentReference = getProductDocument();
    }

    private DocumentReference getProductDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .whereEqualTo(fields.productId, this.getProductId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the product document");
        }
        return documents.get(0).getReference();
    }

    public static Product createProduct(String description, String name, double price, int productId, int quantity,
            int sellerId, int storeId) throws IOException {
        Map<String, Object> productData = new HashMap<String, Object>();
        // Add data to db
        productData.put(fields.productId, productId);
        productData.put(fields.name, name);
        productData.put(fields.price, price);
        productData.put(fields.description, description);
        productData.put(fields.quantity, quantity);
        productData.put(fields.sellerId, sellerId);
        productData.put(fields.storeId, storeId);
        productData.put(fields.isDeleted, false);
        try {
            productsCollection.add(productData).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // Create a new instance
        return new Product(productId, name, quantity, description, price, sellerId, storeId);
    }

    // public void deleteProduct() throws IOException {
    // try {
    // this.documentReference.delete().get();
    // } catch (InterruptedException | ExecutionException e) {
    // e.printStackTrace();
    // }
    // }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.productId, productId);
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
        data.put(fields.sellerId, sellerId);
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
        data.put(fields.quantity, quantity);
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
        data.put(fields.name, name);
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
        data.put(fields.storeId, storeId);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.description, description);
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
        data.put(fields.price, price);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static Product getProductById(int id) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .where(Filter.equalTo(fields.productId, id)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static Product getNonDeletedProductById(int id) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .where(Filter.equalTo(fields.productId, id)).whereNotEqualTo(fields.isDeleted, true).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static Product getProductByName(String name) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .whereEqualTo(fields.name, name).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static Product getNonDeletedProductByName(String name) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .whereEqualTo(fields.name, name).whereNotEqualTo(fields.isDeleted, true).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static Product getProductByStoreName(String storeName) throws IOException {
        ApiFuture<QuerySnapshot> future = Store.storesCollection
                .whereEqualTo(fields.name, storeName).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static Product getNonDeletedProductByStoreName(String storeName) throws IOException {
        ApiFuture<QuerySnapshot> future = Store.storesCollection
                .whereEqualTo(fields.name, storeName).whereNotEqualTo(fields.isDeleted, true).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static Product getProductByDescription(String description) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .whereEqualTo(fields.description, description).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static Product getNonDeletedProductByDescription(String description) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection
                .whereEqualTo(fields.description, description).whereNotEqualTo(fields.isDeleted, true).limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Product(documents.get(0));
    }

    public static ArrayList<Product> getProductsByName(String productName) throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        ApiFuture<QuerySnapshot> future = productsCollection.get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return productList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Product product = new Product(document);
            if (product.name.toLowerCase().contains(productName.toLowerCase())) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static ArrayList<Product> getNonDeletedProductsByName(String productName) throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        ApiFuture<QuerySnapshot> future = productsCollection.whereNotEqualTo(fields.isDeleted, true).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return productList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Product product = new Product(document);
            if (product.name.toLowerCase().contains(productName.toLowerCase())) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static ArrayList<Product> getProductsByDescription(String productDescription)
            throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        ApiFuture<QuerySnapshot> future = productsCollection.get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return productList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Product product = new Product(document);
            if (product.description.toLowerCase().contains(productDescription.toLowerCase())) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static ArrayList<Product> getNonDeletedProductsByDescription(String productDescription)
            throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        ApiFuture<QuerySnapshot> future = productsCollection.whereNotEqualTo(fields.isDeleted, true)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return productList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Product product = new Product(document);
            if (product.description.toLowerCase().contains(productDescription.toLowerCase())) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static ArrayList<Product> getProductsByStoreName(String storeName)
            throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        ApiFuture<QuerySnapshot> future = productsCollection.get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return productList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Product product = new Product(document);
            ArrayList<Store> stores = Store.getNonDeletedStoresByStoreName(storeName);
            for (Store store : stores) {
                if (product.getStoreId() == store.getStoreId()) {
                    productList.add(product);
                }
            }
        }
        return productList;
    }

    public static ArrayList<Product> getNonDeletedProductsByStoreName(String storeName)
            throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        ApiFuture<QuerySnapshot> future = productsCollection.whereNotEqualTo(fields.isDeleted, true).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return productList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Product product = new Product(document);
            ArrayList<Store> stores = Store.getNonDeletedStoresByStoreName(storeName);
            for (Store store : stores) {
                if (product.getStoreId() == store.getStoreId()) {
                    productList.add(product);
                }
            }
        }
        return productList;
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

    public static ArrayList<Product> getNonDeletedProductsByIds(ArrayList<Integer> productIds) throws IOException {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int productID : productIds) {
            Product product = getNonDeletedProductById(productID);
            if (product != null) {
                productList.add(product);
            }
        }
        return productList;
    }

    public static int getNextProductId() throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection.orderBy(fields.productId, Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong(fields.productId).intValue() + 1;
    }

    public static ArrayList<Product> sortProducts(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection.orderBy(field, direction).get();
        ArrayList<Product> products = new ArrayList<Product>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            products.add(new Product(doc));
        }
        return products;
    }

    public static ArrayList<Product> sortNonDeletedProducts(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = productsCollection.orderBy(fields.isDeleted)
                .whereNotEqualTo(fields.isDeleted, true)
                .orderBy(field, direction).get();
        ArrayList<Product> products = new ArrayList<Product>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            products.add(new Product(doc));
        }
        return products;
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
                    isDeleted: %b
                }""", this.getProductId(), this.getName(), this.getQuantity(), this.getDescription(), this.getPrice(),
                this.getSellerId(), this.getStoreId(), this.isDeleted());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product product = (Product) obj;
            if (product.getProductId() == this.getProductId() && product.getName().equals(this.getName())
                    && product.getQuantity() == this.getQuantity()
                    && product.getDescription().equals(this.getDescription())
                    && product.getPrice() == this.getPrice() && product.getSellerId() == this.getSellerId()
                    && product.getStoreId() == this.getStoreId() && product.isDeleted() == this.isDeleted()) {
                return true;
            }
        }
        return false;
    }
}
