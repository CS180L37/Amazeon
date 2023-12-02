package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import utils.Utils;

import java.io.*;
import java.util.*;

public class Sale {
    private int saleId;
    private int customerId;
    private int productId;
    private double cost;
    private int numPurchased;

    private DocumentReference documentReference;

    public static CollectionReference saleCollection;

    private Sale(double cost, int customerId, int numPurchased, int productId, int saleId) {
        if (customerId == 0 || productId == 0) {
            return;
        }
        this.cost = cost;
        this.customerId = customerId;
        this.numPurchased = numPurchased;
        this.productId = productId;
        this.saleId = saleId;
        // if (!Amazeon.sellers.isEmpty()) {
        // System.out.printf("%s purchased %s at a total cost of %.2f\n",
        // customer.getId(), product.getName(), cost);
        // }
        // Amazeon.sales.add(this);
    }

    private Sale(QueryDocumentSnapshot document) throws IOException {
        int saleId = document.getLong("saleId").intValue();
        this.saleId = saleId;
        int customerId = document.getLong("customerId").intValue();
        this.customerId = customerId;
        int productId = document.getLong("productId").intValue();
        this.productId = productId;
        int numPurchased = document.getLong("numPurchased").intValue();
        this.numPurchased = numPurchased;
        double cost = document.getLong("cost").intValue();
        this.documentReference = getSaleDocument();
    }

    private DocumentReference getSaleDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = saleCollection
                .whereEqualTo("productId", this.getSaleId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the sale document");
        }
        return documents.get(0).getReference();
    }

    // TODO: alternative constructor
    public static Sale createSale(double cost, int saleId, int customerId, int productId, int numPurchased) {
        Map<String, Object> saleData = new HashMap<String, Object>();
        // Add data to db
        saleData.put("cost", cost);
        saleData.put("customerId", customerId);
        saleData.put("numPurchased", numPurchased);
        saleData.put("productId", productId);
        saleData.put("saleId", saleId);
        saleCollection.add(saleData);
        // Create a new instance
        return new Sale(cost, customerId, numPurchased, productId, saleId);
    }

    public void deleteSale() throws IOException {
        this.documentReference.delete();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("customerId", customerId);
        this.documentReference.update(data);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("cost", cost);
        this.documentReference.update(data);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("productId", productId);
        this.documentReference.update(data);
    }

    public int getNumPurchased() {
        return numPurchased;
    }

    public void setNumPurchased(int numPurchased) {
        this.numPurchased = numPurchased;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("numPurchased", numPurchased);
        this.documentReference.update(data);
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("saleId", saleId);
        this.documentReference.update(data);
    }

    // TODO: adapt these for backend
    public static Sale getSaleById(int id) throws IOException {
        ApiFuture<QuerySnapshot> future = saleCollection.select("saleId")
                .where(Filter.equalTo("saleId", id)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Sale(documents.get(0));
    }

    public static ArrayList<Sale> getSalesByIds(List<Integer> saleIds) throws IOException {
        ArrayList<Sale> saleList = new ArrayList<Sale>();
        for (int saleID : saleIds) {
            saleList.add(getSaleById(saleID));
        }
        return saleList;
    }

    public static int getNextSaleId() throws IOException {
        ApiFuture<QuerySnapshot> future = saleCollection.orderBy("saleId", Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("saleId").intValue() + 1;
    }

    // Calculate the total cost of a sale
    public double calculateTotal() {
        return getNumPurchased() * getCost();
    }
}
