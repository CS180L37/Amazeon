package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query.Direction;

import utils.Utils;
import utils.fields;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

public class Store {
    private int storeId;
    private String name;
    private boolean isDeleted;
    private ArrayList<Product> storeProducts;
    private ArrayList<Customer> storeCustomers;
    public static CollectionReference storesCollection;
    private DocumentReference documentReference;

    private Store(QueryDocumentSnapshot document) throws IOException {
        // int x =
        // Integer.parseInt(String.valueOf(document.getLong(fields.customerId)));
        int id = document.getLong(fields.storeId).intValue();
        this.name = document.getString(fields.name);
        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), fields.productIds);
        this.storeProducts = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
        this.storeId = id;
        ArrayList<Integer> customerIds = Utils.firestoreDocToIDArray(document.getData(), "customerIds");
        this.storeCustomers = Customer
                .getCustomersByIds((customerIds != null) ? customerIds : new ArrayList<Integer>());
        this.isDeleted = document.getBoolean(fields.isDeleted).booleanValue();
        this.documentReference = getStoreDocumentReference();
    }

    private Store(int storeId, String name, ArrayList<Product> storeProducts, ArrayList<Customer> storeCustomers)
            throws IOException {
        this.storeId = storeId;
        this.name = name;
        this.storeProducts = storeProducts;
        this.storeCustomers = storeCustomers;
        this.isDeleted = false;
        this.documentReference = getStoreDocumentReference();
        // this.documentReference = getStoreDocument();
    }

    public static Store createStore(int storeId, String name) throws IOException {
        // Add document data with auto-generated id.
        Map<String, Object> data = new HashMap<>();
        data.put(fields.storeId, storeId);
        data.put(fields.name, name);
        data.put(fields.productIds, Arrays.asList());
        data.put("customerIds", Arrays.asList());
        data.put(fields.isDeleted, false);
        try {
            storesCollection.add(data).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new Store(storeId, name, new ArrayList<Product>(), new ArrayList<Customer>());
    }

    public static Store getStoreById(int givenStoreId) throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection
                .where(Filter.equalTo(fields.storeId, givenStoreId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Store(documents.get(0));
    }

    public static Store getNonDeletedStoreById(int givenStoreId) throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection
                .where(Filter.equalTo(fields.storeId, givenStoreId)).whereNotEqualTo(fields.isDeleted, true).limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return (documents == null) ? null : new Store(documents.get(0));
    }

    public static ArrayList<Store> getStoresByIds(ArrayList<Integer> storeIds) throws IOException {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int id : storeIds) {
            Store store = getStoreById(id);
            if (store != null) {
                stores.add(store);
            }
        }
        return stores;
    }

    public static ArrayList<Store> getNonDeletedStoresByIds(ArrayList<Integer> storeIds) throws IOException {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int id : storeIds) {
            Store store = getNonDeletedStoreById(id);
            if (store != null) {
                stores.add(store);
            }
        }
        return stores;
    }

    public static int getNextStoreId() throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection.orderBy(fields.storeId, Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong(fields.storeId).intValue() + 1;
    }

    private void updateRemoteStore(String remoteFieldName, Object value) {
        HashMap<String, Object> data2 = new HashMap<String, Object>();
        data2.put(remoteFieldName, value);
        try {
            this.documentReference.update(data2).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private DocumentReference getStoreDocumentReference() throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection
                .whereEqualTo(fields.storeId, this.getStoreId())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the store document");
        }
        return documents.get(0).getReference();
    }

    public static ArrayList<Store> sortStores(String field, Direction direction) throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection.orderBy(field, direction).get();
        ArrayList<Store> stores = new ArrayList<Store>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            stores.add(new Store(doc));
        }
        return stores;
    }

    public static ArrayList<Store> sortNonDeletedStores(@Nonnull String field, @Nonnull Direction direction)
            throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection.orderBy(fields.isDeleted)
                .whereNotEqualTo(fields.isDeleted, true)
                .orderBy(field, direction).get();
        ArrayList<Store> stores = new ArrayList<Store>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            stores.add(new Store(doc));
        }
        return stores;
    }

    public static ArrayList<Store> sortStoresByUserPurchased(int userId)
            throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection.whereArrayContains(fields.customerIds, userId).get();
        ArrayList<Store> stores = new ArrayList<Store>();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        for (QueryDocumentSnapshot doc : documents) {
            if (!doc.getBoolean(fields.isDeleted)) {
                stores.add(new Store(doc));
            }
            ArrayList<Store> allStores = sortStores(fields.storeId, Direction.ASCENDING);
            if (allStores.size() != stores.size()) {
                for (Store store : allStores) {
                    if (!stores.contains(store)) {
                        stores.add(store);
                    }
                }
            }
        }
        return stores;
    }

    public ArrayList<Integer> getStoreProductIds() {
        return (ArrayList<Integer>) storeProducts.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());
    }

    public ArrayList<Integer> getStoreCustomerIds() {
        return (ArrayList<Integer>) storeCustomers.stream()
                .map(Customer::getCustomerId)
                .collect(Collectors.toList());
    }

    public static ArrayList<Store> getStoresByStoreName(String storeName)
            throws IOException {
        ArrayList<Store> storeList = new ArrayList<Store>();
        ApiFuture<QuerySnapshot> future = Store.storesCollection.get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return storeList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Store store = new Store(document);
            if (store.name.toLowerCase().contains(storeName.toLowerCase())) {
                storeList.add(store);
            }
        }
        return storeList;
    }

    public static Store getNonDeletedStoreByStoreName(String storeName)
            throws IOException {
        ApiFuture<QuerySnapshot> future = storesCollection
                .where(Filter.equalTo(fields.name, storeName)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return null;
        }
        Store store = new Store(documents.get(0));
        if (store.isDeleted()) {
            return null;
        }
        return store;
    }

    public static ArrayList<Store> getNonDeletedStoresByStoreName(String storeName)
            throws IOException {
        ArrayList<Store> storeList = new ArrayList<Store>();
        ApiFuture<QuerySnapshot> future = Store.storesCollection.whereNotEqualTo(fields.isDeleted, true).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        if (documents == null) {
            return storeList;
        }
        for (QueryDocumentSnapshot document : documents) {
            Store store = new Store(document);
            if (store.name.toLowerCase().contains(storeName.toLowerCase())) {
                storeList.add(store);
            }
        }
        return storeList;
    }

    public String getName() {
        return this.name;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public ArrayList<Product> getStoreProducts() {
        return this.storeProducts;
    }

    public ArrayList<Customer> getStoreCustomers() {
        return this.storeCustomers;
    }

    public DocumentReference getDocumentReference() {
        return documentReference;
    }

    public void setName(String name) {
        this.name = name;
        updateRemoteStore(fields.name, name);
    }

    public void setStoreId(int id) {
        this.storeId = id;
        updateRemoteStore(fields.storeId, id);
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

    public void setStoreProducts(ArrayList<Product> storeProducts) {
        this.storeProducts = storeProducts;
        ArrayList<Integer> productIds = new ArrayList<Integer>();
        for (Product product : storeProducts) {
            productIds.add(product.getProductId());
        }
        updateRemoteStore(fields.productIds, productIds);
    }

    public void setStoreCustomers(ArrayList<Customer> storeCustomers) {
        this.storeCustomers = storeCustomers;
        ArrayList<Integer> customerIds = new ArrayList<Integer>();
        for (Customer customer : storeCustomers) {
            customerIds.add(customer.getCustomerId());
        }
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
                    isDeleted: %b
                }""", this.getStoreId(), this.getName(), this.getStoreProducts().toString(),
                this.getStoreCustomers().toString(), this.isDeleted());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Store) {
            Store store = (Store) obj;
            if (store.getStoreId() == this.getStoreId() && store.getName().equals(this.getName())
                    && store.getStoreProducts().equals(this.getStoreProducts())
                    && store.getStoreCustomers().equals(this.getStoreCustomers())
                    && store.isDeleted() == this.isDeleted()) {
                return true;
            }
        }
        return false;
    }
}
