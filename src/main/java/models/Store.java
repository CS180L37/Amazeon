package models;

import java.util.ArrayList;

public class Store {
    private int storeId;
    private String name;
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;

    private Store(int id, String name, ArrayList<Product> products, ArrayList<Customer> customers) {
        this.name = name;
        this.products = products;
        this.id = id;
        this.customers = customers;
    }

    // TODO: alternative constructor
    public static Store createStore() {
        return null;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public static Store getStoreById(int id) {
        Store store = null;
        for (Store currentStore : Amazeon.stores) {
            if (currentStore.getId() == id) {
                store = currentStore;
            }
        }
        return store;
    }

    public static ArrayList<Store> getStoresByIds(List<Integer> storeIds) {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int id : storeIds) {
            stores.add(getStoreById(id));
        }
        return stores;
    }

    public static int getNextStoreId() {
        // Store store = null;
        // for (Store currentStore : Amazeon.stores) {
        // if (currentStore.getId() == id) {
        // store = currentStore;
        // }
        // }
        // return store;
        return 0;
    }
}
