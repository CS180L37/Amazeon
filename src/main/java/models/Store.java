package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import utils.Utils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private int storeId;
    private String name;
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    public static CollectionReference storesCollection;
    private DocumentReference documentReference;

    private Store(QueryDocumentSnapshot document) throws IOException {
        // int x = Integer.parseInt(String.valueOf(document.getLong("customerId")));
        int id = document.getLong("storeId").intValue();
        this.name = document.getString("name");
        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), "productIds");
        this.products = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
        this.storeId = id;
        ArrayList<Integer> customerIds = Utils.firestoreDocToIDArray(document.getData(), "customerIds");
        this.customers = Customer.getCustomersByIds(customerIds);
        this.documentReference = getStoreDocument();

    }

    private Store(int cartId, ArrayList<Product> products) throws IOException {
        this.storeId = cartId;
        this.products = products;
        this.documentReference = getStoreDocument();
    }

    public static Store createStore(int cartId, ArrayList<Product> products) throws IOException {
        // Add document data with auto-generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("storeId", getNextStoreId());
        data.put("productIds", Arrays.asList());
        storesCollection.add(data);
        return new Store(cartId, products);
    }

    public static Store getStoreById(int givenStoreId) throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection
                .where(Filter.equalTo("storeId", givenStoreId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Store(documents.get(0));
    }

    public static ArrayList<Store> getStoresByIds(ArrayList<Integer> storeIds) throws IOException {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int id : storeIds) {
            stores.add(getStoreById(id));
        }
        return stores;
    }

    public static int getNextStoreId() throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection.orderBy("storeId", Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("storeId").intValue() + 1;
    }

    private void updateRemoteStore(String remoteFieldName, Object value) {
        HashMap<String, Object> data2 = new HashMap<String, Object>();
        data2.put(remoteFieldName, value);
        this.documentReference.update(data2);
    }

    private DocumentReference getStoreDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection
                .whereEqualTo("storeId", this.getStoreDocument())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the store document");
        }
        return documents.get(0).getReference();
    }

    public ArrayList<Integer> getStoreProductIds() {
        return (ArrayList<Integer>) products.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());
    }

    public ArrayList<Integer> getStoreCustomerIds() {
        return (ArrayList<Integer>) customers.stream()
                .map(Customer::getCustomerId)
                .collect(Collectors.toList());
    }

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
        updateRemoteStore("productIds", getStoreProductIds());
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
        updateRemoteStore("customerIds", getStoreCustomerIds());
    }

    @Override
    public String toString() {
        return String.format("""
                {
                    storeId: %d
                    name: %s
                    products: %s
                    customers: %s
                }""", this.getStoreId(), this.getName(), this.getProducts().toString(), this.getCustomers().toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Store) {
            Store store = (Store) obj;
            if (store.getStoreId() == this.getStoreId() && store.getName().equals(this.getName())
                    && store.getProducts() == this.getProducts()
                    && store.getCustomers() == this.getCustomers()) {
                return true;
            }
        }
        return false;
    }
}
