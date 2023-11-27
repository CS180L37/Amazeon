package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Amazeon;
import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

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
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        return br;
    }

    public static BufferedWriter createWriter(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
        return bw;
    }

    public static ArrayList<Integer> splitIdsByPipe(String input) {
        ArrayList<Integer> data = new ArrayList<Integer>();
        String[] idList = input.split("\\|");
        for (String id : idList) {
            if (id.equals("NA")) {
                data.add(-1);
            } else {
                data.add(Integer.parseInt(id.trim()));
            }
        }
        return data;
    }

    public static String convertToIdString(String input) {
        return input.substring(1, input.length() - 1).replace(",", "|").replace(" ", "");
    }

    public static String convertToProductString(Product product) {
        return Integer.toString(product.getProductId()) + "," + product.getName() + ","
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
        return Integer.toString(customer.getId()) + "," + customer.getEmail()
                + "," + customer.getPassword() + ","
                + Utils.convertToIdString(Amazeon.getProductIds(customer.getProducts()).toString());
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
                Integer.parseInt(data[3]));
    }

    public static String convertToStoreString(Store store) {
        return Integer.toString(store.getId()) + "," + store.getName() + ","
                + Utils.convertToIdString((Amazeon.getProductIds(store.getProducts())).toString()) + ","
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
                (!data[4].equals(Utils.NA)) ? Amazeon.getProductsByIds(Utils.splitIdsByPipe(data[4]))
                        : new ArrayList<Product>(),
                data[2], data[1],
                (!data[5].equals(Utils.NA)) ? Amazeon.getSalesByIds(Utils.splitIdsByPipe(data[5]))
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
            if (reprompt != null) {
                System.out.println(reprompt);
            } else {
                System.out.println(prompt);
            }
        } while (true);
    }

    // TODO: id methods
    // Product methods
    public static Product getProductById(int id) {
        // Why is it empty here?
        for (Product product : getProducts()) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return new Product(-1, "", -1, "", -1, -1, -1);
    }

    public static ArrayList<Product> getProducts() {
        return Amazeon.products;
    }

    public static ArrayList<Product> getProductsByIds(ArrayList<Integer> productIds) {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (int productID : productIds) {
            productList.add(getProductById(productID));
        }
        return productList;
    }

    public static ArrayList<Integer> getProductIds(ArrayList<Product> productList) {
        ArrayList<Integer> productIds = new ArrayList<Integer>();
        for (Product product : productList) {
            productIds.add(product.getProductId());
        }
        return productIds;
    }

    // Cart methods
    public static Cart getCartById(int cartId) {
        for (Cart cart : Amazeon.carts) {
            if (cart.getCustomerID() == cartId) {
                return cart;
            }
        }
        return new Cart(-1, new ArrayList<Product>());
    }

    public static ArrayList<Cart> getCartsByIds(ArrayList<Integer> cartIds) {
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        for (int cartID : cartIds) {
            cartList.add(getCartById(cartID));
        }
        return cartList;
    }

    public static ArrayList<Integer> getCartIds(ArrayList<Cart> cartList) {
        ArrayList<Integer> cartIDs = new ArrayList<Integer>();
        for (Cart cart : cartList) {
            cartIDs.add(cart.getCustomerID());
        }
        return cartIDs;
    }

    // Customer methods
    public static Customer getCustomerByEmailAndPassword(String email, String password) {
        for (Customer customer : Amazeon.customers) {
            if (customer.getEmail().equals(email) || customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return new Customer(-1, "", "", new ArrayList<>(), new Cart(-1, new ArrayList<>()));
    }

    public static Customer getCustomerById(int customerId) {
        for (Customer customer : Amazeon.customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return new Customer(-1, "", "", new ArrayList<>(), new Cart(-1, new ArrayList<>()));
    }

    public static int getNextCustomerId() {
        int customerListSize = customers.size() - 1;
        if (customerListSize < 0) {
            return 1;
        }
        return customers.get(customerListSize).getId() + 1;
    }

    public static ArrayList<Customer> getCustomersByIds(ArrayList<Integer> customerIds) {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        for (int customerId : customerIds) {
            customerList.add(Amazeon.getCustomerById(customerId));
        }
        return customerList;
    }

    public static ArrayList<Integer> getCustomerIds(ArrayList<Customer> customerList) {
        ArrayList<Integer> customerIds = new ArrayList<Integer>();
        for (Customer customer : customerList) {
            customerIds.add(customer.getId());
        }
        return customerIds;
    }

    // Sale methods
    public static Sale getSaleById(int i) {
        Sale sale = null;
        for (Sale currentSale : Amazeon.sales) {
            if (currentSale.getSaleId() == i) {
                sale = currentSale;
            }
        }
        return sale;
    }

    public static ArrayList<Sale> getSalesByIds(ArrayList<Integer> saleIds) {
        ArrayList<Sale> sales = new ArrayList<>();
        for (int currentID : saleIds) {
            sales.add(getSaleById(currentID));
        }
        return sales;
    }

    public static ArrayList<Integer> getSaleIds(ArrayList<Sale> saleList) {
        ArrayList<Integer> saleIDs = new ArrayList<>();
        for (Sale sale : saleList) {
            saleIDs.add(sale.getSaleId());
        }
        return saleIDs;
    }

    // Stores methods
    public static Store getStoreById(int id) {
        Store store = null;
        for (Store currentStore : Amazeon.stores) {
            if (currentStore.getId() == id) {
                store = currentStore;
            }
        }
        return store;
    }

    public int getStoreIDFromName(String storeName) {
        for (Store store : stores) {
            if (store.getName().equalsIgnoreCase(storeName)) {
                return store.getId();
            }
        }
        return Utils.NO;
    }

    public static String getStoreNameFromID(int storeID) {
        for (Store store : Amazeon.stores) {
            if (store.getId() == storeID) {
                return store.getName();
            }
        }
        return String.valueOf(Utils.NO);
    }

    public static ArrayList<Store> getStoresByIds(ArrayList<Integer> storeIds) {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int id : storeIds) {
            stores.add(getStoreById(id));
        }
        return stores;
    }

    public static ArrayList<Integer> getStoreIds(ArrayList<Store> storeList) {
        ArrayList<Integer> storeIDs = new ArrayList<>();
        for (Store store : storeList) {
            storeIDs.add(store.getId());
        }
        return storeIDs;
    }

    public static ArrayList<Integer> getIDsByString(String prodIDs) {
        String[] IDs = prodIDs.split("\\|");
        ArrayList<Integer> numIDs = new ArrayList<>();
        for (String currentID : IDs) {
            int ID = Integer.parseInt(currentID);
            numIDs.add(ID);
        }
        return numIDs;
    }

    // Seller methods
    public static Seller getSellerByEmailAndPassword(String email, String password) {
        for (Seller seller : sellers) {
            if (seller.getEmail().equalsIgnoreCase(email) && seller.getPassword().equalsIgnoreCase(password)) {
                return seller;
            }
        }
        return new Seller(-1, new ArrayList<>(), "", "", new ArrayList<>());
    }

    public static Seller getSellerById(int sellerId) {
        for (Seller seller : Amazeon.sellers) {
            if (seller.getId() == sellerId) {
                return seller;
            }
        }
        return new Seller(-1, new ArrayList<>(), "", "", new ArrayList<>());
    }

    public static int getNextSellerId() {
        int sellerListSize = customers.size() - 1;
        if (sellerListSize < 0) {
            return 1;
        }
        return sellers.get(sellers.size() - 1).getId() + 1;
    }

    public static ArrayList<Seller> getSellersByIds(ArrayList<Integer> sellerIds) {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        for (int sellerId : sellerIds) {
            sellers.add(getSellerById(sellerId));
        }
        return sellers;
    }

    public static ArrayList<Integer> getSellerIds(ArrayList<Seller> sellerList) {
        ArrayList<Integer> sellerIds = new ArrayList<Integer>();
        for (Seller seller : sellerList) {
            sellerIds.add(seller.getId());
        }
        return sellerIds;
    }
}
