// Parent class for SellerMarket and CustomerMarket

import java.util.ArrayList;
import java.util.Scanner;

public class Market<T> {
    private ArrayList<Store> stores;
    private T user;

    public Market(ArrayList<Store> stores, T user) {
        this.stores = stores;
        this.user = user;
    }

    // Entry point for the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazeon! Do you have an account? (y/n)");
        String hasAccount;
        do {
            hasAccount = scanner.nextLine();
            if (Utils.validInput(hasAccount) != Utils.ERROR) {
                break;
            }
            System.out.println("Welcome to Amazeon! Do you have an account? (y/n)");
        } while (true);
        switch (Utils.validInput(hasAccount)) {
            case Utils.YES:
                // login();
                break;
            case Utils.NO:
                // createAccount(email, password);
                break;
            default:
                break;
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

    public void loginProcess() {
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.pritnln("Would you like to create an account as a customer (y) or seller (n)?");
        String userType;
        do {
            usrType = scanner.nextLine();
            if (Utils.validInput(userType) != Utils.ERROR) {
                break;
            }
        } while (true);
        switch (Utils.validInput(userType)) {
            case Utils.YES:
                createAccount(email, password, "Customer");
                break;
            case Utils.NO:
                createAccount(email, password, "Seller");
                break;
            default:
                break;
        }
    }
}
