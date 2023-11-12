import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
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

    public static BufferedReader createReader(String filename) throws IOException {
        System.out.println(filename);
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        System.out.println("AFTER READER");
        return br;
    }

    public static BufferedWriter createWriter(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
        return bw;
    }

    public static ArrayList<Integer> splitIdsByPipe(String input) {
        ArrayList<Integer> data = new ArrayList<Integer>();
        String[] idList = input.split("|");
        for (String id : idList) {
            if (id.equals("NA")) {
                data.add(-1);
            } else {
                data.add(Integer.parseInt(id));
            }
        }
        return data;
    }

    public static String convertToIdString(String input) {
        return input.substring(1, input.length() - 1).replace(",", "|");
    }

    public static String convertToProductString(Product product) {
        return Integer.toString(product.getProductId()) + "," + product.getName()
                + Integer.toString(product.getQuantity()) + "," + product.getDescription() + ","
                + Double.toString(product.getPrice()) + "," + Integer.toString(product.getSellerId()) + ","
                + Integer.toString(product.getStoreId());
    }

    public static Product convertFromProductString(String[] data) {
        return new Product(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),
                data[3], Double.parseDouble(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]));
    }

    public static String convertToCartString(Cart cart) {
        return Integer.toString(cart.getCustomerID()) + ","
                + Utils.convertToIdString((Amazeon.getProductIds(cart.getCartProducts())).toString());
    }

    public static Cart convertFromCartString(String[] data) {
        return new Cart(Integer.parseInt(data[0]),
                (!data[1].equals(Utils.NA)) ? Amazeon.getProductsByIds(Utils.splitIdsByPipe(data[1]))
                        : new ArrayList<Product>());
    }

    public static String convertToCustomerString(Customer customer) {
        return Integer.toString(Integer.parseInt(customer.getId() + "," + customer.getEmail()
                + "," + customer.getPassword() + ","
                + Utils.convertToIdString(Amazeon.getProductIds(customer.getProducts()).toString())));
    }

    public static Customer convertFromCustomerString(String[] data) {
        return new Customer(Integer.parseInt(data[0]), data[1], data[2],
                (!data[3].equals(Utils.NA)) ? Amazeon.getProductsByIds(Utils.splitIdsByPipe(data[3]))
                        : new ArrayList<Product>(),
                Amazeon.getCartById(Integer.parseInt(data[0])));
    }

    public static String convertToSaleString(Sale sale) {
        return Integer.toString(sale.getSaleId()) + "," + Integer.toString(sale.getCustomer().getId()) + ","
                + Integer.toString(sale.getProduct().getProductId()) + ","
                + Integer.toString(sale.getNumPurchased());
    }

    public static Sale convertFromSaleString(String[] data) {
        return new Sale(Integer.parseInt(data[0]), Amazeon.getCustomerById(Integer.parseInt(data[1])),
                Amazeon.getProductById(Integer.parseInt(data[2])),
                Integer.parseInt(data[4]));
    }

    public static String convertToStoreString(Store store) {
        return Integer.toString(store.getId()) + "," + store.getName() + ","
                + Utils.convertToIdString((Amazeon.getProductIds(store.getProducts())).toString())
                + Utils.convertToIdString((Amazeon.getCustomerIds(store.getCustomers())).toString());
    }

    public static Store convertFromStoreString(String[] data) {
        return new Store(Integer.parseInt(data[0]), data[1],
                (!data[2].equals(Utils.NA)) ? Amazeon.getProductsByIds(Utils.splitIdsByPipe(data[2]))
                        : new ArrayList<Product>(),
                (!data[3].equals(Utils.NA)) ? Amazeon.getCustomersByIds(Utils.splitIdsByPipe(data[3]))
                        : new ArrayList<Customer>());
    }

    public static String convertToSellerString(Seller seller) {
        return Integer.toString(seller.getId()) + "," + seller.getEmail() + "," + seller.getPassword() + ","
                + Utils.convertToIdString(Amazeon.getProductIds(seller.getProducts()).toString()) + ","
                + Utils.convertToIdString(Amazeon.getSaleIds(seller.getSales()).toString());
    }

    public static Seller convertFromSellerString(String[] data) {
        return new Seller(
                Integer.parseInt(data[0]),
                (!data[3].equals(Utils.NA)) ? Amazeon.getProductsByIds(Utils.splitIdsByPipe(data[3]))
                        : new ArrayList<Product>(),
                data[2], data[1],
                (!data[4].equals(Utils.NA)) ? Amazeon.getSalesByIds(Utils.splitIdsByPipe(data[4]))
                        : new ArrayList<Sale>());
    }

    public static String inputPrompt(String prompt, ValidateInterface validateInterface, String... reprompt) {
        System.out.println(prompt);
        String userInput;
        do {
            userInput = Utils.SCANNER.nextLine();
            if (validateInterface.validate(userInput)) {
                return userInput;
            }
            System.out.println((reprompt != null) ? reprompt : prompt);
        } while (true);
    }
}
