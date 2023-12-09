import static utils.Utils.initializeDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;
import screens.LoginGUI;
import utils.Utils;
import utils.fields;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Query.Direction;

import javax.swing.*;

/// Our entry point and data manager class
/// NOTE: NOTHING SHOULD IMPORT AMAZEON; It is the entry point and imports

public class Amazeon {
    public static void main(String[] args) {
        try {
            Utils.initializeDatabase();
        } catch (IOException e) {
            // TODO: display error message
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "title", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(new LoginGUI());


        // Event Loop
        // if (hasAccount()) {
        // // Login
        // if (isCustomer()) {
        // // Get the email and password
        // String email = emailPrompt();
        // String password = passwordPrompt();
        // // Get the customer by email and password
        // customer = Amazeon.getCustomerByEmailAndPassword(email, password);
        // // Create the customer market (accesses all stores)
        // customerMarket = new CustomerMarket(customer, Amazeon.stores);
        // // Open up options to customer
        // amazeon.customerLoop(customerMarket, customer);
        // amazeon.writeData();
        // } else {
        // // Get the email and password
        // String email = emailPrompt();
        // String password = passwordPrompt();
        // // Get the seller by email and password
        // seller = Amazeon.getSellerByEmailAndPassword(email, password);
        // // Create the seller market (only accesses stores associated with the seller)
        // sellerMarket = new SellerMarket(seller, seller.getStores(),
        // Amazeon.customers, Amazeon.products);
        // // Open up options to seller
        // amazeon.sellerLoop(sellerMarket, seller);
        // amazeon.writeData();
        // }
        // } else {
        // // Create
        // if (isCustomer()) {
        // // Get the email and password
        // String email = emailPrompt();
        // String password = passwordPrompt();
        // // Create a new customer based on email and password
        // customer = new Customer(email, password);
        // // Create a customer market using the customer
        // customerMarket = new CustomerMarket(customer, Amazeon.stores);
        // // Open up options to customer
        // amazeon.customerLoop(customerMarket, customer);
        // amazeon.writeData();
        // } else {
        // // Get the email and password
        // String email = emailPrompt();
        // String password = passwordPrompt();
        // // Create a new seller based on email and password
        // seller = new Seller(email, password);
        // // Create a seller market using the seller
        // sellerMarket = new SellerMarket(seller, new ArrayList<Store>(),
        // Amazeon.customers, Amazeon.products);
        // // Open up options to seller
        // amazeon.sellerLoop(sellerMarket, seller);
        // amazeon.writeData();
        // }
        // }
    }
}
