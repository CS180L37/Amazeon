package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query.Direction;

import utils.Utils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Store {
//    private int storeId;
//    private String name;
//    private ArrayList<Product> products;
//    private ArrayList<Customer> customers;
//    public static CollectionReference storesCollection;
//    private DocumentReference documentReference;
//
//    private Store(QueryDocumentSnapshot document) throws IOException {
//        // int x = Integer.parseInt(String.valueOf(document.getLong("customerId")));
//        int id = document.getLong("storeId").intValue();
//        this.name = document.getString("name");
//        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), "productIds");
//        this.products = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
//        this.storeId = id;
//        ArrayList<Integer> customerIds = Utils.firestoreDocToIDArray(document.getData(), "customerIds");
//        this.customers = Customer.getCustomersByIds((customerIds != null) ? customerIds : new ArrayList<Integer>());
//        this.documentReference = getStoreDocument();
//
//    }
//
//    private Store(int storeId, String name, ArrayList<Product> products, ArrayList<Customer> customers)
//            throws IOException {
//        this.storeId = storeId;
//        this.name = name;
//        this.products = products;
//        this.customers = customers;
//        this.documentReference = getStoreDocument();
//    }
//
//    public static Store createStore(int storeId, String name) throws IOException {
//        // Add document data with auto-generated id.
//        Map<String, Object> data = new HashMap<>();
//        data.put("storeId", storeId);
//        data.put("name", name);
//        data.put("productIds", Arrays.asList());
//        data.put("customerIds", Arrays.asList());
//        storesCollection.add(data);
//        return new Store(storeId, name, new ArrayList<Product>(), new ArrayList<Customer>());
//    }
//
//    public static Store getStoreById(int givenStoreId) throws IOException {
//        ApiFuture<QuerySnapshot> future = storesCollection
//                .where(Filter.equalTo("storeId", givenStoreId)).limit(1).get();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        return (documents != null) ? null : new Store(documents.get(0));
//    }
//
//    public static ArrayList<Store> getStoresByIds(ArrayList<Integer> storeIds) throws IOException {
//        ArrayList<Store> stores = new ArrayList<Store>();
//        for (int id : storeIds) {
//            Store store = getStoreById(id);
//            if (store != null) {
//                stores.add(store);
//            }
//        }
//        return stores;
//    }
//
//    public static int getNextStoreId() throws IOException {
//        ApiFuture<QuerySnapshot> future = storesCollection.orderBy("storeId", Query.Direction.DESCENDING)
//                .limit(1).get();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        return documents.get(0).getLong("storeId").intValue() + 1;
//    }
//
//    private void updateRemoteStore(String remoteFieldName, Object value) {
//        HashMap<String, Object> data2 = new HashMap<String, Object>();
//        data2.put(remoteFieldName, value);
//        this.documentReference.update(data2);
//    }
//
//    private DocumentReference getStoreDocument() throws IOException {
//        ApiFuture<QuerySnapshot> future = storesCollection
//                .whereEqualTo("storeId", this.getStoreId())
//                .limit(1)
//                .get();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        // Throws an exception if customerDocument is null for some reason
//        if (documents == null) {
//            throw new IOException("Could not retrieve the store document");
//        }
//        return documents.get(0).getReference();
//    }
//
//    public static ArrayList<Store> sortStores(String field, Direction direction) throws IOException {
//        ApiFuture<QuerySnapshot> future = storesCollection.orderBy(field, direction).get();
//        ArrayList<Store> stores = new ArrayList<Store>();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        if (documents == null) {
//            return null;
//        }
//        for (QueryDocumentSnapshot doc : documents) {
//            stores.add(new Store(doc));
//        }
//        return stores;
//    }
//
//    public ArrayList<Integer> getStoreProductIds() {
//        return (ArrayList<Integer>) products.stream()
//                .map(Product::getProductId)
//                .collect(Collectors.toList());
//    }
//
//    public ArrayList<Integer> getStoreCustomerIds() {
//        return (ArrayList<Integer>) customers.stream()
//                .map(Customer::getCustomerId)
//                .collect(Collectors.toList());
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public int getStoreId() {
//        return this.storeId;
//    }
//
//    public ArrayList<Product> getProducts() {
//        return this.products;
//    }
//
//    public ArrayList<Customer> getCustomers() {
//        return this.customers;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        updateRemoteStore("name", name);
//    }
//
//    public void setStoreId(int id) {
//        this.storeId = id;
//        updateRemoteStore("storeId", id);
//    }
//
//    public void setProducts(ArrayList<Product> products) {
//        this.products = products;
//        ArrayList<Integer> productIds = new ArrayList<Integer>();
//        for (Product product : products) {
//            productIds.add(product.getProductId());
//        }
//        updateRemoteStore("productIds", productIds);
//    }
//
//    public void setCustomers(ArrayList<Customer> customers) {
//        this.customers = customers;
//        ArrayList<Integer> customerIds = new ArrayList<Integer>();
//        for (Customer customer : customers) {
//            customerIds.add(customer.getCustomerId());
//        }
//        updateRemoteStore("customerIds", getStoreCustomerIds());
//    }
//
//    @Override
//    public String toString() {
//        return String.format("""
//                {
//                    storeId: %d
//                    name: %s
//                    products: %s
//                    customers: %s
//                }""", this.getStoreId(), this.getName(), this.getProducts().toString(), this.getCustomers().toString());
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof Store) {
//            Store store = (Store) obj;
//            if (store.getStoreId() == this.getStoreId() && store.getName().equals(this.getName())
//                    && store.getProducts().equals(this.getProducts())
//                    && store.getCustomers().equals(this.getCustomers())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
