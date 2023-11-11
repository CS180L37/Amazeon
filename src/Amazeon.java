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
        // ArrayList<Product> ourProducts = new ArrayList<Product>();
        // ourProducts.add(new Product(0, 10, "Switch", 0, "Switching it up", 300.00));
        // Product.writeProducts(ourProducts);
        this.products = Product.readProducts(); // .products.json
        // System.out.println(this.products.toString());
        this.carts = Cart.readCarts(products); // .carts.json
        this.customers = Customer.readCustomers(products, carts); // .customers.json
        this.sales = Sale.readSales(customers); // .sales
        this.stores = Store.readStores(products, customers);
        this.sellers = Seller.readSellers(products, sales);
    }

    public static void main(String[] args) {
        // Initialize data
        Amazeon amazeon = new Amazeon();

        CustomerMarket customerMarket;
        SellerMarket sellerMarket;
        Customer customer;
        Seller seller;

        // Welcome the user
        if (hasAccount()) {
            // Login
            if (isCustomer()) {
                // Get the email and password
                String email = getEmail();
                String password = getPassword();
                // Get the customer by email and password
                customer = getCustomerByEmailAndPassword(email, password);
                // Create the customer market (accesses all stores)
                customerMarket = new CustomerMarket(customer, amazeon.stores);
                // Open up options to customer
                customerLoop();
                writeData();
            } else {
                // Get the email and password
                String email = getEmail();
                String password = getPassword();
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
                String email = getEmail();
                String password = getPassword();
                // Create a new customer based on email and password
                customer = new Customer(email, password);
                // Create a customer market using the customer
                customerMarket = new CustomerMarket(customer, amazeon.stores);
                // Open up options to customer
                customerLoop();
                writeData();
            } else {
                // Get the email and password
                String email = getEmail();
                String password = getPassword();
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

    // Entry point for the program
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

    // Prompt methods
    public static String getEmail() {
        return Utils.inputPrompt("What is your email: ", input -> Utils.validateEmail(input),
                "Please enter a valid email: ");
    }

    public static String getPassword() {
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

    public static int getNextSellerId() {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    // Store methods
    public static Store getStoreById(int id) {
        throw new UnsupportedOperationException("Unsupported operation: 'getStoreById'");
    }

    public static Product getProductById(int id){
        throw new UnsupportedOperationException("Unsupported operation: 'getProductById' ");
    }

    public static void customerLoop(CustomerMarket customerMarket, Customer customer) {
        customerMarket.displayMarketplace();
        boolean error = true;
        do{
            System.out.println("What would you like to do?\n1) Purchase\n2) Search\n3) Display Dashboard\n4) Sort MarketPlace\n5) View Cart");
            int customerAction = Integer.parseInt(Utils.SCANNER.nextLine());
            if(customerAction == 1){
                System.out.println("Which product would you like to purchase?");
                int productID = Integer.parseInt(Utils.SCANNER.nextLine());
                customer.purchaseProduct(getProductById(productID));
            } else if(customerAction == 2){
                System.out.println("Enter the name of product: ");
                String name = Utils.SCANNER.nextLine();
                customerMarket.search(name, null, null);
            } else if (customerAction == 3){
                customerMarket.displayDashboard();
            } else if (customerAction == 4){
                System.out.println("Would you like to sort by price (y) or quantity (n)");
                int sortCriteria = Utils.yesOrNoToInt(Utils.SCANNER.nextLine());
                if(sortCriteria == 1){
                    customerMarket.sort(true, false);
                } else {
                    customerMarket.sort(false, true);
                }

            } else if (customerAction == 5){
                customerMarket.displayCart();
            } else {
                System.out.println("Please choose a valid option.");
                error = false;
            }
        } while(error);

        throw new UnsupportedOperationException("Unsupported operation: 'customerLoop'");
    }

    public static void sellerLoop() {
        throw new UnsupportedOperationException("Unsupported operation: 'sellerLoop'");
    }

    public static void writeData() {
        throw new UnsupportedOperationException("Unsupported operation: 'writeData'");
    }
}
