package screens;

import java.io.IOException;
import java.util.ArrayList;

public class Store {
    private int storeId;
    private String name;
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;


    public Store(int cartId, ArrayList<Product> products) throws IOException {
        this.storeId = cartId;
        this.products = products;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}