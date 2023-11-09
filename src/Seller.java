import java.util.ArrayList;

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
        System.out.println("""
How do you want to sort?
1. Customer name
2. Product name
3. 
""");
        for (Sale sale : sales) {

        }
    }


}
