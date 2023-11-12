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
    public static final String PRODUCT_FILE = "/.product.csv";
    public static final String CART_FILE = "/.cart.csv";
    public static final String CUSTOMER_FILE = "/.customer.csv";
    public static final String SALE_FILE = "/.sale.csv";
    public static final String STORE_FILE = "/.store.csv";
    public static final String SELLER_FILE = "/.seller.csv"; //in the format int id, ArrayList<Product> products, String email, String password, ArrayList<Sale> sales
    public static final String NA = "NA";

    public static boolean validateYesOrNo(String input) {
        input = input.toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            return true;
        } else return input.equals("n") || input.equals("no");
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
