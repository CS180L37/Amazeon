import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/// Our entry point and data manager class
public class Amazeon {
    // The data that persists
    static ArrayList<Product> products;
    static ArrayList<Cart> carts;
    static ArrayList<Customer> customers;
    static ArrayList<Sale> sales;
    static ArrayList<Store> stores;
    static ArrayList<Seller> sellers;
    public static int counterSellers = 0;
    public static int counterBuyers = 0;

    public Amazeon() {
        products = Product.readProducts();
        carts = Cart.readCarts();
        customers = Customer.readCustomers();
        sales = Sale.readSales();
        stores = Store.readStores();
        sellers = Seller.readSellers();
    }

    public static void main(String[] args) {
        // Initialize data
        Amazeon amazeon = new Amazeon();
        // Test code
        System.out.println(products.toString());
        System.out.println(carts.toString());
        System.out.println(customers.toString());
        System.out.println(sales.toString());
        System.out.println(stores.toString());
        System.out.println(sellers.toString());

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
                customer = Amazeon.getCustomerByEmailAndPassword(email, password);
                // Create the customer market (accesses all stores)
                customerMarket = new CustomerMarket(customer, Amazeon.stores);
                // Open up options to customer
                amazeon.customerLoop(customerMarket, customer);
                amazeon.writeData();
            } else {
                // Get the email and password
                String email = emailPrompt();
                String password = passwordPrompt();
                // Get the seller by email and password
                seller = Amazeon.getSellerByEmailAndPassword(email, password);
                // Create the seller market (only accesses stores associated with the seller)
                sellerMarket = new SellerMarket(seller, seller.getStores(),
                        amazeon.customers, Amazeon.products);
                // Open up options to seller
                amazeon.sellerLoop(sellerMarket, seller);
                amazeon.writeData();
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
                customerMarket = new CustomerMarket(customer, Amazeon.stores);
                // Open up options to customer
                amazeon.customerLoop(customerMarket, customer);
                amazeon.writeData();
            } else {
                // Get the email and password
                String email = emailPrompt();
                String password = passwordPrompt();
                // Create a new seller based on email and password
                seller = new Seller(email, password);
                // Create a seller market using the seller
                sellerMarket = new SellerMarket(seller, new ArrayList<Store>(),
                        Amazeon.customers, Amazeon.products);
                // Open up options to seller
                amazeon.sellerLoop(sellerMarket, seller);
                amazeon.writeData();
            }
        }
    }

    private void sellerLoop(SellerMarket sellerMarket, Seller seller) {
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
        Product.writeProducts(Amazeon.products);
        Cart.writeCarts(Amazeon.carts);
        Customer.writeCustomers(Amazeon.customers);
        Sale.writeSales(Amazeon.sales);
        Store.writeStores(Amazeon.stores);
        Seller.writeSellers(Amazeon.sellers);
    }

    // Product methods
    public static Product getProductById(int id) {

    }

    public static ArrayList<Product> getProductByIds(ArrayList<Integer> productIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    public static ArrayList<Integer> getProductIds(ArrayList<Product> products) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductIds'");
    }

    // Cart methods
    public static Cart getCartById(int parseInt) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCartById'");
    }

    public static ArrayList<Product> getCartsById(ArrayList<Integer> cartIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
    }

    public static ArrayList<Integer> getCartIds(ArrayList<Cart> carts) {
        throw new UnsupportedOperationException("Unsupported operation: 'getProductByIds'");
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

    public static ArrayList<Customer> getCustomersByIds(ArrayList<Integer> customerIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCustomersByIds'");
    }

    public static ArrayList<Integer> getCustomerIds(ArrayList<Customer> customers) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCustomerIds'");
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

    public static ArrayList<Sale> getSalesById(ArrayList<Integer> saleIds) {
        ArrayList<Sale> sales = new ArrayList<>();
        for (int currentID : saleIds) {
            sales.add(getSaleById(currentID));
        }
        return sales;
    }

    public static ArrayList<Integer> getSaleIds(ArrayList<Sale> sales) {
        ArrayList<Integer> saleIDs = new ArrayList<>();
        for (Sale sale : sales) {
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

    public static ArrayList<Store> getStoresById(ArrayList<Integer> storeIds) {
        ArrayList<Store> stores = new ArrayList<>();
        for (int id : storeIds) {
            stores.add(getStoreById(id));
        }
        return stores;
    }

    public static ArrayList<Integer> getStoreIds(ArrayList<Store> stores) {
        ArrayList<Integer> storeIDs = new ArrayList<>();
        for (Store store : stores) {
            storeIDs.add(store.getId());
        }
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
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(new File(Utils.DATA_DIR + Utils.SELLER_FILE));
            br = new BufferedReader(fr);
            String currentLine = br.readLine();
            while (currentLine != null) {
                String[] parts = currentLine.split(",");
                if (parts[1].equals(email)) {
                    if (parts[2].equals(password)) {
                        return new Seller(Integer.parseInt(parts[0]), getProductByIds(getIDsByString(parts[1])), parts[2], parts[3], getSalesById(getIDsByString(parts[4])));
                    }
                }
                currentLine = br.readLine();
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Seller getSellerById(int sellerId) {
        for (Seller seller : Amazeon.sellers) {
            if (seller.getId() == sellerId) {
                return seller;
            }
        }
        return null;
    }

    public static int getNextSellerId() {
        throw new UnsupportedOperationException("Unimplemented method 'getNextCustomerId'");
    }

    public static ArrayList<Seller> getSellersByIds(ArrayList<Integer> sellerIds) {
        throw new UnsupportedOperationException("Unsupported operation: 'getCustomersByIds'");
    }

    public static ArrayList<Seller> getSellerIds(ArrayList<Seller> sellers) {
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
