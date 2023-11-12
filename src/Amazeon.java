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
    public static int counterSellers = 0;
    public static int counterBuyers = 0;

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

    public static String emailPrompt() {
        return Utils.inputPrompt("What is your email: ", input -> Utils.validateEmail(input),
                "Please enter a valid email: ");
    }

    public static String passwordPrompt() {
        return Utils.inputPrompt("What is your password: ", input -> Utils.validatePassword(input),
                "Please enter a valid password: ");
    }

    public void writeData() {
        Product.writeProducts(this.products);
        Cart.writeCarts(this.carts);
        Customer.writeCustomers(this.customers);
        Sale.writeSales(this.sales);
        Store.writeStores(this.stores);
        Seller.writeSellers(this.sellers);
    }

    // Product methods
    public Product getProductById(int id) {

    }

    public ArrayList<Product> getProductByIds(ArrayList<Integer> productIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    public ArrayList<Integer> getProductIds(ArrayList<Product> products) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductIds'");
    }

    // Cart methods
    public Cart getCartById(int parseInt) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCartById'");
    }

    public ArrayList<Product> getCartsById(ArrayList<Integer> cartIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    public ArrayList<Integer> getCartIds(ArrayList<Cart> carts) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    // Customer methods
    public Customer getCustomerByEmailAndPassword(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public Customer getCustomerById(int customerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public int getNextCustomerId() {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public ArrayList<Customer> getCustomersByIds(ArrayList<Integer> customerIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCustomersByIds'");
    }

    public ArrayList<Integer> getCustomerIds(ArrayList<Customer> customers) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCustomerIds'");
    }

    // Sale methods
    public Sale getSaleById(int i) {
        throw new UnsupportedOperationException("Unsupported operation: 'getSaleById'");
    }

    public ArrayList<Sale> getSalesById(ArrayList<Integer> saleIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    public ArrayList<Integer> getSaleIds(ArrayList<Sale> sales) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    // Stores methods
    public Store getStoreById(int id) {
        throw new UnsupportedOperationException("Unsupported operation: 'getStoreById'");
    }

    public ArrayList<Store> getStoresById(ArrayList<Integer> storeIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    public ArrayList<Integer> getStoreIds(ArrayList<Store> stores) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    // Seller methods
    public Seller getSellerByEmailAndPassword(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public Seller getSellerById(int sellerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public int getNextSellerId() {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public ArrayList<Seller> getSellersByIds(ArrayList<Integer> sellerIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCustomersByIds'");
    }

    public ArrayList<Seller> getSellerIds(ArrayList<Seller> sellers) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCustomerIds'");
    }

    public void customerLoop(CustomerMarket customerMarket, Customer customer) {
        customerMarket.displayMarketplace();
        boolean error = true;
        do {
            System.out.println(
                    "What would you like to do?\n1) Purchase\n2) Search\n3) Display Dashboard\n4) Sort MarketPlace\n5) View Cart");
            int customerAction = Integer.parseInt(Utils.SCANNER.nextLine());
            if (customerAction == 1) {
                System.out.println("Which product would you like to purchase?");
                int productID = Integer.parseInt(Utils.SCANNER.nextLine());
                customer.purchaseProduct(getProductById(productID));
            } else if (customerAction == 2) {
                boolean valid = true;
                do {
                    System.out.println("Would you like to search by name (1), storedId (2) or description (3)? ");
                    int searchCriteria = Integer.parseInt(Utils.SCANNER.nextLine());
                    if (searchCriteria == 1) {
                        System.out.println("Enter the name of the product: ");
                        String name = Utils.SCANNER.nextLine();
                        customerMarket.search(name, null, null);
                    } else if (searchCriteria == 2) {
                        System.out.println("Enter the storeId of the product: ");
                        String storeId = Utils.SCANNER.nextLine();
                        customerMarket.search(null, storeId, null);
                    } else if (searchCriteria == 3) {
                        System.out.println("Enter the description of the product: ");
                        String description = Utils.SCANNER.nextLine();
                        customerMarket.search(null, null, description);
                    } else {
                        valid = false;
                    }
                } while (!valid);
            } else if (customerAction == 3) {
                customerMarket.displayDashboard();
            } else if (customerAction == 4) {
                System.out.println("Would you like to sort by price (y) or quantity (n)");
                int sortCriteria = Utils.yesOrNoToInt(Utils.SCANNER.nextLine());
                if (sortCriteria == 1) {
                    customerMarket.sort(true, false);
                } else {
                    customerMarket.sort(false, true);
                }

            } else if (customerAction == 5) {
                customerMarket.displayCart();
            } else {
                System.out.println("Please do choose a valid option.");
                error = false;
            }
        } while (!error);
        throw new UnsupportedOperationException("Unsupported operation: 'customerLoop'");
    }

    public void sellerLoop() {
        throw new UnsupportedOperationException("Unsupported operation: 'sellerLoop'");
    }
}
