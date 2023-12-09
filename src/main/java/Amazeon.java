import static utils.Utils.initializeDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
        // try {
        // ArrayList<Product> products = Product.getNonDeletedProductsByName("and");
        // System.out.println(products.size());
        // products = Product.getNonDeletedProductsByDescription("percy");
        // System.out.println(products.size());
        // products = Product.getNonDeletedProductsByStoreName("i");
        // System.out.println(products.size());
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        SwingUtilities.invokeLater(new LoginGUI());
    }
}
