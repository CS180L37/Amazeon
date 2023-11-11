// Parent class for SellerMarket and CustomerMarket

import java.util.ArrayList;

public class Market<T> {
    private ArrayList<Store> stores;
    private T user;

    public Market(ArrayList<Store> stores, T user) {
        this.stores = stores;
        this.user = user;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public void setUserById(int userId) {
        throw new UnsupportedOperationException("Unimplemented method 'setUserById(int id)'");
    }

    // Read the data to restore a previous session
    public void readData(String filename) {
        throw new UnsupportedOperationException("Unimplemented method 'readData'");
    }

    // Persist the data by writing it to storage
    public void writeData(String filename) {
        throw new UnsupportedOperationException("Unimplemented method 'writeData'");
    }
}
