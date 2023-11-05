// Parent class for SellerMarket and CustomerMarket

import java.util.ArrayList;

public class Market<T> {
    private ArrayList<Store> stores;
    private T user;
    private Dashboard<T> dashboard;

    public Market(ArrayList<Store> stores, T user, Dashboard<T> dashboard) {
        this.stores = stores;
        this.user = user;
        this.dashboard = dashboard;
    }

    public static void main(String[] args) {
        throw new UnsupportedOperationException("Unimplemented method 'main'");
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

    public Dashboard<T> getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard<T> dashboard) {
        this.dashboard = dashboard;
    }

    public void readData(String filename) {
        throw new UnsupportedOperationException("Unimplemented method 'readData'");
    }

    public void writeData(String filename) {
        throw new UnsupportedOperationException("Unimplemented method 'writeData'");
    }
}
