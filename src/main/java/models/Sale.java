package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query.Direction;

import utils.Utils;
import utils.fields;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Sale {
    private int saleId;
    private int customerId;
    private int productId;
    private double cost;
    private int numPurchased;
    private boolean isDeleted;
    private DocumentReference documentReference;

    public static CollectionReference salesCollection;

    private Sale(double cost, int customerId, int numPurchased, int productId, int saleId) {
        this.cost = cost;
        this.customerId = customerId;
        this.numPurchased = numPurchased;
        this.productId = productId;
        this.saleId = saleId;
        this.isDeleted = false;
    }

    private Sale(QueryDocumentSnapshot document) throws IOException {
        int saleId = document.getLong(fields.saleId).intValue();
        this.saleId = saleId;
        int customerId = document.getLong(fields.customerId).intValue();
        this.customerId = customerId;
        int productId = document.getLong(fields.productId).intValue();
        this.productId = productId;
        int numPurchased = document.getLong(fields.numPurchased).intValue();
        this.numPurchased = numPurchased;
        double cost = document.getDouble(fields.cost).doubleValue();
        System.out.println(cost);
        System.err.println(cost);
        this.cost = cost;
        this.isDeleted = document.getBoolean(fields.isDeleted).booleanValue();
        this.documentReference = getSaleDocument();
    }

    private DocumentReference getSaleDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = salesCollection
                .whereEqualTo(fields.saleId, this.getSaleId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the sale document");
        }
        return documents.get(0).getReference();
    }

    public static Sale createSale(double cost, int saleId, int customerId, int productId, int numPurchased) {
        Map<String, Object> saleData = new HashMap<String, Object>();
        // Add data to db
        saleData.put(fields.cost, cost);
        saleData.put(fields.customerId, customerId);
        saleData.put(fields.numPurchased, numPurchased);
        saleData.put(fields.productId, productId);
        saleData.put(fields.isDeleted, false);
        saleData.put(fields.saleId, saleId);
        try {
            salesCollection.add(saleData).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // Create a new instance
        return new Sale(cost, customerId, numPurchased, productId, saleId);
    }

    // public void deleteSale() throws IOException {
    // try {
    // this.documentReference.delete().get();
    // } catch (InterruptedException e) {
    // throw new RuntimeException(e);
    // } catch (ExecutionException e) {
    // throw new RuntimeException(e);
    // }
    // }

    public static ArrayList<Sale> sortSales(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = salesCollection.orderBy(field, direction).get();
        ArrayList<Sale> sales = new ArrayList<Sale>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            sales.add(new Sale(doc));
        }
        return sales;
    }

    public static ArrayList<Sale> sortNonDeletedSales(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = salesCollection.whereNotEqualTo(fields.isDeleted, true)
                .orderBy(field, direction).get();
        ArrayList<Sale> sales = new ArrayList<Sale>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            sales.add(new Sale(doc));
        }
        return sales;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.customerId, customerId);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.cost, cost);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

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

    public int getNumPurchased() {
        return numPurchased;
    }

    public void setNumPurchased(int numPurchased) {
        this.numPurchased = numPurchased;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.numPurchased, numPurchased);
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

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(fields.saleId, saleId);
        try {
            this.documentReference.update(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static Sale getSaleById(int id) throws IOException {
        ApiFuture<QuerySnapshot> future = salesCollection
                .where(Filter.equalTo(fields.saleId, id)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Sale(documents.get(0));
    }

    public static Sale getNonDeletedSaleById(int id) throws IOException {
        ApiFuture<QuerySnapshot> future = salesCollection
                .where(Filter.equalTo(fields.saleId, id)).whereNotEqualTo(fields.isDeleted, true).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Sale(documents.get(0));
    }

    public static ArrayList<Sale> getSalesByIds(ArrayList<Integer> saleIds) throws IOException {
        ArrayList<Sale> saleList = new ArrayList<Sale>();
        for (int saleID : saleIds) {
            Sale sale = getSaleById(saleID);
            if (sale != null) {
                saleList.add(sale);
            }
        }
        return saleList;
    }

    public static ArrayList<Sale> getNonDeletedSalesByIds(ArrayList<Integer> saleIds) throws IOException {
        ArrayList<Sale> saleList = new ArrayList<Sale>();
        for (int saleID : saleIds) {
            Sale sale = getNonDeletedSaleById(saleID);
            if (sale != null) {
                saleList.add(sale);
            }
        }
        return saleList;
    }

    public static int getNextSaleId() throws IOException {
        ApiFuture<QuerySnapshot> future = salesCollection.orderBy(fields.saleId, Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong(fields.saleId).intValue() + 1;
    }

    // Calculate the total cost of a sale
    public double calculateTotal() {
        return getNumPurchased() * getCost();
    }

    @Override
    public String toString() {
        return String.format("""
                {
                    cost: %f
                    customerId: %s
                    numPurchased: %s
                    productId: %s
                    saleId: %s
                    isDeleted: %b
                }""", this.getCost(), this.getCustomerId(),
                this.getNumPurchased(),
                this.getProductId(),
                this.getSaleId(), this.isDeleted());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sale) {
            Sale sale = (Sale) obj;
            if (sale.getCost() == this.getCost() && sale.getCustomerId() == this.getCustomerId()
                    && sale.getNumPurchased() == this.getNumPurchased()
                    && sale.getProductId() == this.getProductId()
                    && sale.getSaleId() == this.getSaleId() && sale.isDeleted() == this.isDeleted()) {
                return true;
            }
        }
        return false;
    }
}
