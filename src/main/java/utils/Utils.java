package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;

import com.google.api.core.ApiFuture;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

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
        // GoogleCredentials.fromStream(new
        // FileInputStream("amazeon-405720-6089a258377a.json"));
        // Create the database
        FirestoreOptions firestoreOptions;
        FileInputStream f = new FileInputStream(
                "src/main/resources/service_account.json");
        firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(GoogleCredentials.fromStream(f)))
                .build();
        db = firestoreOptions.getService();
        initializeCollections(db);
    }

    // This method takes a database as a parameter to allow flexibility regarding
    // testing as well
    public static void initializeCollections(Firestore firestoreDB) {
        Customer.customersCollection = firestoreDB.collection("customers");
        Cart.cartsCollection = firestoreDB.collection("carts");
        Product.productsCollection = firestoreDB.collection("products");
        Sale.salesCollection = firestoreDB.collection("sales");
        Store.storesCollection = firestoreDB.collection("stores");
        Seller.sellersCollection = firestoreDB.collection("sellers");
    }

    public static void clearCollections(Firestore firestoreDB) {
        firestoreDB.recursiveDelete(Customer.customersCollection);
        firestoreDB.recursiveDelete(Cart.cartsCollection);
        firestoreDB.recursiveDelete(Product.productsCollection);
        firestoreDB.recursiveDelete(Sale.salesCollection);
        firestoreDB.recursiveDelete(Store.storesCollection);
        firestoreDB.recursiveDelete(Seller.sellersCollection);
    }

    public static ArrayList<Integer> firestoreDocToIDArray(Map<String, Object> map, String docPath) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().equals(docPath)) {
                if (!entry.getValue().toString().equals("[]")) {
                    String[] idArray = entry.getValue().toString().replace("[", "").replace("]", "").replace(" ", "")
                            .split(",");
                    for (String id : idArray) {
                        array.add(Integer.parseInt(id));
                    }
                }
            }
        }
        return array;
    }

    public static List<QueryDocumentSnapshot>

            retrieveData(ApiFuture<QuerySnapshot> future) throws IOException {
        QuerySnapshot query;

        try {
            query = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IOException();
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new IOException();
        }
        List<QueryDocumentSnapshot> documents = query.getDocuments();
        if (documents.isEmpty()) {
            return null;
        }
        return documents;
    }

    public static void

            writeData(ApiFuture<WriteResult> future) throws IOException {
        WriteResult result;

        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IOException();
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new IOException();
        }
        return;
    }

    public static void

            writeDataWithDoc(ApiFuture<DocumentReference> future) throws IOException {
        DocumentReference result;

        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IOException();
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new IOException();
        }
        return;
    }
}
