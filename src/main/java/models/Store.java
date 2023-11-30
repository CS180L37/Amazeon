package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import utils.Utils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static utils.Utils.db;

public class Store {
    private int storeId;
    private String name;
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private static CollectionReference storeCollection = db.collection("stores");


    public String getName() {
        return this.name;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setStoreId(int id) {
        this.storeId = id;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }


    private Store(QueryDocumentSnapshot document) throws IOException {
//        int x = Integer.parseInt(String.valueOf(document.getLong("customerId")));
        int id = document.getLong("storeId").intValue();
        this.name = document.getString("name");
        List<Integer> productIds = (List<Integer>) document.getData().get("productIds");
        this.products = Product.getProductsByIds((productIds != null) ? productIds : Arrays.asList());
        this.storeId = id;
        List<Integer> customerIds = (List<Integer>) document.getData().get("customerIds");
        this.customers = Customer.getCustomersByIds(customerIds);
    }
    private Store(int cartId, ArrayList<Product> products) {
        this.storeId = cartId;
        this.products = products;
    }
//
//    // TODO: alternative constructor
    public static Store createStore(int cartId, ArrayList<Product> products) throws ExecutionException, InterruptedException {
        // Add document data with auto-generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("cartId", "id");
        data.put("productIds", Arrays.asList());
        ApiFuture<DocumentReference> addedDocRef = db.collection("carts").add(data);
        System.out.println("Added document with ID: " + addedDocRef.get().getId());
        return new Store(cartId, products);
    }


    public static Store getStoreById(int givenStoreId) throws IOException {
        ApiFuture<QuerySnapshot> future = storeCollection.select("customerId")
                .where(Filter.equalTo("customerId", givenStoreId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Store(documents.get(0));
    }

    public static ArrayList<Store> getStoresByIds(List<Integer> storeIds) throws IOException {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int id : storeIds) {
            stores.add(getStoreById(id));
        }
        return stores;
    }

    public static int getNextStoreId() throws IOException {
        ApiFuture<QuerySnapshot> future = storeCollection.orderBy("storeId", Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("storeId").intValue() + 1;
    }
}
