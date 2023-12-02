package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class Utils {
    public static Firestore db;
    public static final int YES = 1;
    public static final int NO = 0;
    public static final int ERROR = -1;
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String DATA_DIR = ".data";
    public static final String TEST_FILE = "/.test.csv";
    public static final String PRODUCT_FILE = "/.product.csv";
    public static final String CART_FILE = "/.cart.csv";
    public static final String CUSTOMER_FILE = "/.customer.csv";
    public static final String SALE_FILE = "/.sale.csv";
    public static final String STORE_FILE = "/.store.csv";
    public static final String CUSTOMER_PURCHASE_HISTORY = "/.customer_purchase_history.csv";
    public static final String SELLER_PRODUCTS = "/.seller_products.csv";
    public static final String SELLER_FILE = "/.seller.csv"; // in the format int id, ArrayList<Product> products, //
                                                             // String email, String password, ArrayList<Sale> sales
    public static final String NA = "NA";

    public static boolean validateYesOrNo(String input) {
        input = input.toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            return true;
        } else
            return input.equals("n") || input.equals("no");
    }

    public static int yesOrNoToInt(String input) {
        input = input.toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            return YES;
        } else if (input.equals("n") || input.equals("no")) {
            return NO;
        }
        return ERROR;
    }

    // Check if the email is valid
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9_.]*@[A-Za-z0-9_.].[A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            return false;
        }
        return password.length() >= 7;
    }

    // public static BufferedReader createReader(String filename) throws IOException
    // {
    // BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
    // return br;
    // }

    // public static BufferedWriter createWriter(String filename) throws IOException
    // {
    // BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
    // return bw;
    // }

    public static String inputPrompt(String prompt, ValidateInterface validateInterface, String... reprompt) {
        System.out.println(prompt);
        String userInput;
        do {
            userInput = Utils.SCANNER.nextLine();
            if (validateInterface.validate(userInput)) {
                return userInput;
            }
            if (reprompt != null) {
                System.out.println(reprompt);
            } else {
                System.out.println(prompt);
            }
        } while (true);
    }

    public static void initializeDatabase() throws IOException {
        // Initialize Firestore
        FirestoreOptions firestoreOptions;
        firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("amazeon-405720")
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
        db = firestoreOptions.getService();
        initializeCollections();
    }

    public static void initializeCollections() throws IOException {
        Customer.customerCollection = Utils.db.collection("customers");
        Cart.cartsCollection = Utils.db.collection("carts");
        Product.productCollection = Utils.db.collection("products");
        Sale.saleCollection = Utils.db.collection("sales");
        Store.storeCollection = Utils.db.collection("stores");
        Seller.sellerCollection = Utils.db.collection("sellers");
    }

    public static List<QueryDocumentSnapshot>

            retrieveData(ApiFuture<QuerySnapshot> future) throws IOException {
        QuerySnapshot query;
        try {
            query = future.get();
        } catch (InterruptedException e) {
            throw new IOException();
        } catch (ExecutionException e) {
            throw new IOException();
        }
        List<QueryDocumentSnapshot> documents = query.getDocuments();
        if (documents.isEmpty()) {
            return null;
        }
        return documents;
    }
}
