import java.util.ArrayList;

/// Our entry point and data manager class
public class Amazeon {
    // The data that persists
    ArrayList<Product> products;
    ArrayList<Cart> carts;
    ArrayList<Customer> customers;
    ArrayList<Sale> sales;
    ArrayList<Store> stores;
    ArrayList<Seller> sellers;

    public Amazeon() {
        this.products = Product.readProducts();
        this.carts = Cart.readCarts();
        this.customers = Customer.readCustomers();
        this.sales = Sale.readSales();
        this.stores = Store.readStores();
        this.sellers = Seller.readSellers();
    }

    public static void main(String[] args) {
        // Initialize data
        Amazeon amazeon = new Amazeon();
        // Test code
        System.out.println(amazeon.products.toString());
        System.out.println(amazeon.carts.toString());
        System.out.println(amazeon.customers.toString());
        System.out.println(amazeon.sales.toString());
        System.out.println(amazeon.stores.toString());
        System.out.println(amazeon.sellers.toString());

        CustomerMarket customerMarket;
        SellerMarket sellerMarket;
        Customer customer;
        Seller seller;

        // Welcome the user
        if (hasAccount()) {
            // Login
            if (isCustomer()) {
                // Get the email and password
                String email = emailPrompt();
                String password = passwordPrompt();
                // Get the customer by email and password
                customer = getCustomerByEmailAndPassword(email, password);
                // Create the customer market (accesses all stores)
                customerMarket = new CustomerMarket(customer, amazeon.stores);
                // Open up options to customer
                customerLoop();
                writeData();
            } else {
                // Get the email and password
                String email = emailPrompt();
                String password = passwordPrompt();
                // Get the seller by email and password
                seller = getSellerByEmailAndPassword(email, password);
                // Create the seller market (only accesses stores associated with the seller)
                sellerMarket = new SellerMarket(seller, seller.getStores(),
                        amazeon.customers, amazeon.products);
                // Open up options to seller
                sellerLoop();
                writeData();
            }
        } else {
            // Create
            if (isCustomer()) {
                // Get the email and password
                String email = emailPrompt();
                String password = passwordPrompt();
                // Create a new customer based on email and password
                customer = new Customer(email, password);
                // Create a customer market using the customer
                customerMarket = new CustomerMarket(customer, amazeon.stores);
                // Open up options to customer
                customerLoop();
                writeData();
            } else {
                // Get the email and password
                String email = emailPrompt();
                String password = passwordPrompt();
                // Create a new seller based on email and password
                seller = new Seller(email, password);
                // Create a seller market using the seller
                sellerMarket = new SellerMarket(seller, new ArrayList<Store>(),
                        amazeon.customers, amazeon.products);
                // Open up options to seller
                sellerLoop();
                writeData();
            }
        }
    }

    // Utility methods
    public static boolean hasAccount() {
        String userInput = Utils.inputPrompt("Welcome to Amazeon! Do you have an account? (y/n)",
                input -> Utils.validateYesOrNo(input));
        switch (Utils.yesOrNoToInt(userInput)) {
            case Utils.YES:
                return true;
            case Utils.NO:
                return false;
            default:
                return false; // Never calls
        }
    }

    public static boolean isCustomer() {
        String userType = Utils.inputPrompt("Are you a customer (y) or a seller (n)?",
                input -> Utils.validateYesOrNo(input));
        switch (Utils.yesOrNoToInt(userType)) {
            case Utils.YES:
                return true;
            case Utils.NO:
                return false;
            default:
                return false; // Never calls
        }
    }

    public static void writeData() {
        throw new UnsupportedOperationException("Unsupported operation: 'writeData'");
    }

    public static String emailPrompt() {
        return Utils.inputPrompt("What is your email: ", input -> Utils.validateEmail(input),
                "Please enter a valid email: ");
    }

    public static String passwordPrompt() {
        return Utils.inputPrompt("What is your password: ", input -> Utils.validatePassword(input),
                "Please enter a valid password: ");
    }

    // Customer methods
    public static Customer getCustomerByEmailAndPassword(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static Customer getCustomerById(int customerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public static ArrayList<Customer> getCustomersByIds(ArrayList<Integer> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static ArrayList<Integer> getCustomerIds(ArrayList<Customer> customers) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static int getNextCustomerId() {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    // Seller methods
    public static Seller getSellerByEmailAndPassword(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static Seller getSellerById(int sellerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public static ArrayList<Seller> getSellersByIds(ArrayList<Integer> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static ArrayList<Integer> getSellerIds(ArrayList<Seller> sellers) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static int getNextSellerId() {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    // Store methods
    public static Store getStoreById(int id) {
        throw new UnsupportedOperationException("Unsupported operation: 'getStoreById'");
    }

    public static ArrayList<Store> getStoresByIds(ArrayList<Integer> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static ArrayList<Integer> getStoreIds(ArrayList<Store> stores) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    // Product methods
    public static Product getProductById(int id) {
        throw new UnsupportedOperationException("Unsupported operation: 'getStoreById'");
    }

    public static ArrayList<Product> getProductByIds(ArrayList<Integer> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static ArrayList<Integer> getProductIds(ArrayList<Product> products) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    // Sale methods
    public static Sale getSaleById(int id) {
        throw new UnsupportedOperationException("Unsupported operation: 'getStoreById'");
    }

    public static ArrayList<Sale> getSalesByIds(ArrayList<Integer> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static ArrayList<Integer> getSaleIds(ArrayList<Sale> sales) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    // Cart methods
    public static Cart getCartById(int id) {
        throw new UnsupportedOperationException("Unsupported operation: 'getStoreById'");
    }

    public static ArrayList<Cart> getCartsByIds(ArrayList<Integer> ids) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static ArrayList<Integer> getCartIds(ArrayList<Cart> carts) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    // Loops
    public static void customerLoop() {
        throw new UnsupportedOperationException("Unsupported operation: 'customerLoop'");
    }

    public static void sellerLoop() {
        throw new UnsupportedOperationException("Unsupported operation: 'sellerLoop'");
    }
}
