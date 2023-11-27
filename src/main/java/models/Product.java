package models;

import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Product {
    private int productId;
    private String name;
    private int quantity;
    private String description;
    private double price;
    private Seller seller;
    private Store store;

    public Product(int productId, String name, int quantity, String description,
            double price, int sellerId, int storeId) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        this.sellerId = sellerId;
        this.storeId = storeId;
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
    public static Product getProductById(int id) {
        for (Product product : getProducts()) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return new Product(-1, "", -1, "", -1, -1, -1);
    }

    public static ArrayList<Product> getProductsByIds(ArrayList<Integer> productIds) {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int productID : productIds) {
            productList.add(getProductById(productID));
        }
        return productList;
    }

    public static int getNextProductId() {
        // int sellerListSize = customers.size() - 1;
        // if (sellerListSize < 0) {
        // return 1;
        // }
        // return sellers.get(sellers.size() - 1).getId() + 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name=" + name + '\'' +
                ", quantity=" + quantity +
                ", description=" + description + '\'' +
                ", price=" + price +
                ", sellerId=" + sellerId +
                ", storeId=" + storeId +
                '}';
    }
}
