import java.io.BufferedReader;
import java.io.*;
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
    public static void createProduct(String filename) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("filename.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[][] data = new String[][]{line.split(",")};
                for (int i = 0; i < data.length; i += 6) {
                    String[][] strArray = data[i+3].split(",");
                    int[] intArray = new int[strArray.length];
                    for (int j = 0; j < strArray.length; j++) {
                        intArray[j] = Integer.parseInt(strArray[i]);
                    }

                    Product product = new Product(Integer.parseInt(data[i]), Integer.parseInt(data[i+1]), data[i+2], intArray, data[i+4], Double.parseDouble(data[i+5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
