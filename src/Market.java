// Parent class for SellerMarket and CustomerMarket

import java.util.ArrayList;

public class Market<T> {
    private ArrayList<Store> stores;
    private T user;

    public Market(T user, ArrayList<Store> stores) {
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
}
