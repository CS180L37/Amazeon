import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class Seller {
    private static String name;
    private static int ID;
    private static ArrayList<Product> productsSold;
    private static ArrayList<Sale> sales;
    public static void displayProducts() {
        for (int i = 0; i < productsSold.size(); i++) {
            System.out.printf("%d. %s\n", i, productsSold.get(i));
        }
    }

    public static void displayDashboard() {
        System.out.println("How do you want to sort?\n1. Customer name\n2. Product name\n3.");
        for (Sale sale : sales) {

        }
    }
    public static void createProduct(String filename) throws FileNotFoundException {
        Scanner sc  = new Scanner(new File("filename.csv"));
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            
        }


    }


}
