// Parent class for SellerMarket and CustomerMarket

import java.util.ArrayList;
import java.util.Scanner;

public class Market<T> {
    private static final Scanner SCANNER = new Scanner(System.in);
    private ArrayList<Store> stores;
    private T user;

    public Market(ArrayList<Store> stores, T user) {
        this.stores = stores;
        this.user = user;
    }

    // Entry point for the program
    public boolean hasAccount() {
        System.out.println("Welcome to Amazeon! Do you have an account? (y/n)");
        String hasAccount;
        do {
            hasAccount = SCANNER.nextLine();
            if (Utils.validInput(hasAccount) != Utils.ERROR) {
                break;
            }
            System.out.println("Welcome to Amazeon! Do you have an account? (y/n)");
        } while (true);
        switch (Utils.validInput(hasAccount)) {
            case Utils.YES:
                return true;
            case Utils.NO:
                return false;
            default:
                return false;
        }
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

    public boolean login() {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    public boolean createAccount() {
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

    // public T authentication() {

    // throw new UnsupportedOperationException("Unimplemented method
    // 'authentication'");
    // }

    // public T loginProcess() {
    // System.out.println("Enter your email: ");
    // String email = scanner.nextLine();
    // System.out.println("Enter your password: ");
    // String password = scanner.nextLine();
    // System.out.println("Would you like to create an account as a customer (y) or
    // seller (n)?");
    // String userType;
    // do {
    // usrType = scanner.nextLine();
    // if (Utils.validInput(userType) != Utils.ERROR) {
    // break;
    // }
    // } while (true);
    // switch (Utils.validInput(userType)) {
    // case Utils.YES:
    // int customerId = createAccount(email, password, "Customer");
    // user = Customer.getCustomerById(customerId);
    // break;
    // case Utils.NO:
    // int sellerId = createAccount(email, password, "Seller");
    // user = Seller.getSellerById(sellerId);
    // break;
    // default:
    // break;
    // }
    // }
}
