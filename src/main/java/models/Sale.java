package models;

import java.io.*;
import java.util.ArrayList;

public class Sale {
    private int saleId;
    private Customer customer;
    private Product product;
    private double cost;
    private int numPurchased;

    private Sale(int saleID, Customer customer, Product product, int numPurchased) {
        if (customer == null || product == null) {
            return;
        }
        this.saleId = saleID;
        this.customer = customer;
        this.product = product;
        this.numPurchased = numPurchased;
        this.cost = calculateCost();
        // if (!Amazeon.sellers.isEmpty()) {
        // System.out.printf("%s purchased %s at a total cost of %.2f\n",
        // customer.getId(), product.getName(), cost);
        // }
        // Amazeon.sales.add(this);
    }

    // TODO: alternative constructor
    public static Sale createSale() {
        return null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getNumPurchased() {
        return numPurchased;
    }

    public void setNumPurchased(int numPurchased) {
        this.numPurchased = numPurchased;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    // TODO: adapt these for backend
    public static Sale getSaleById(int i) {
        Sale sale = null;
        for (Sale currentSale : Amazeon.sales) {
            if (currentSale.getSaleId() == i) {
                sale = currentSale;
            }
        }
        return sale;
    }

    public static ArrayList<Sale> getSalesByIds(ArrayList<Integer> saleIds) {
        ArrayList<Sale> sales = new ArrayList<>();
        for (int currentID : saleIds) {
            sales.add(getSaleById(currentID));
        }
        return sales;
    }

    public static int getNextSaleId() {
        // ArrayList<Integer> saleIDs = new ArrayList<>();
        // for (Sale sale : saleList) {
        // saleIDs.add(sale.getSaleId());
        // }
        // return saleIDs;
        return 0;
    }

    // Calculate the total cost of a sale
    public double calculateCost() {
        return getNumPurchased() * getProduct().getPrice();
    }
}
