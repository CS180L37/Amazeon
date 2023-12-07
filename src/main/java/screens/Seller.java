package screens;

import java.io.IOException;
import java.util.ArrayList;

public class Seller {
    private int sellerId;
    private String name;
    private String email;
    private String password;
    private ArrayList<Product> products;
    private ArrayList<Sale> sales;

    public Seller(int sellerId, String name, String email, String password, ArrayList<Product> products,
                  ArrayList<Sale> sales) throws IOException {
        this.sellerId = sellerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.products = products;
        this.sales = sales;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }
}