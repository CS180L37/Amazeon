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
        for (Product product : Amazeon.products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    public static ArrayList<Product> getProductsByIds(ArrayList<Integer> productIds) {
        ArrayList<Product> products = new ArrayList<Product>();
        for (int productID : productIds) {
            products.add(getProductById(productID));
        }
        return products;
    }

    public static ArrayList<Integer> getProductIds(ArrayList<Product> products) {
        ArrayList<Integer> productIDs = new ArrayList<>();
        for (Product product : products) {
            productIDs.add(product.getProductId());
        }
        return productIDs;
    }

    // Cart methods
    public static Cart getCartById(int parseInt) {
        for (Cart cart : Amazeon.carts) {
            if (cart.getCustomerID() == parseInt) {
                return cart;
            }
        }
    }

    public static ArrayList<Product> getCartsById(ArrayList<Integer> cartIds) {
        ArrayList<Cart> carts = new ArrayList<Cart>();
        for (int cartID : cartIds) {
            carts.add(getCartById(cartID));
        }
    }
    public static ArrayList<Integer> getCartIds(ArrayList<Cart> carts) {
        ArrayList<Integer> cartIDs = new ArrayList<Integer>();
        for (Cart cart : carts) {
            cartIDs.add(cart.getCustomerID());
        }
    }

    // Customer methods
    public static Customer getCustomerByEmailAndPassword(String email, String password) {
        for (Customer customer : Amazeon.customers) {
            if (customer.get(1) == email || customer.get(2) == password) {
                return customer;
            }
        }
    }

    public static Customer getCustomerById(int customerId) {
        for (Customer customer : Amazeon.customers) {
            if (customer.getId == customerId) {
                return customer;
            }
        }
    }

    public static int getNextCustomerId() {

    }

    public static ArrayList<Customer> getCustomersByIds(ArrayList<Integer> customerIds) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        for (int customerId : customerIds) {
            customers.add(getCustomerById(customerId));
        }
    }

    public static ArrayList<Integer> getCustomerIds(ArrayList<Customer> customers) {
        ArrayList<Integer> customerIDs = new ArrayList<Integer>();
        for (Customer customer : customers) {
            customerIDs.add(customer.getId());
        }
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

    public static ArrayList<Store> getStoresByIds(ArrayList<Integer> storeIds) {
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
        return null;
    }

    public static Seller getSellerById(int sellerId) {
        for (Seller seller : Amazeon.sellers) {
            if (seller.getId() == sellerId) {
                return seller;
            }
        }
        return null;
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
        for (Store store : Market.getStores()) {
            if (store.getId() == storeID) {
                return store.getName();
            }
        }
        return String.valueOf(Utils.NO);
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
        //throw new UnsupportedOperationException("Unsupported operation: 'customerLoop'");
    }

    public void sellerLoop(SellerMarket sellerMarket, Seller seller) {
        sellerMarket.displayMarketplace();
        boolean error = true;
        do {
            System.out.println(
                    "What would you like to do?\n1) Create\n2) Edit\n3) Delete\n4) View Sales\n5) Display Dashboard" +
                            "6) View Carts\n");
            int customerAction = Integer.parseInt(Utils.SCANNER.nextLine());
            if (customerAction == 1) {
                System.out.println("Which product would you like to purchase?");
                int productID = Integer.parseInt(Utils.SCANNER.nextLine());
                seller.purchaseProduct(getProductById(productID));
            } else if (customerAction == 2) {
                boolean valid = true;
                do {
                    System.out.println("Would you like to search by name (1), storedId (2) or description (3)? ");
                    int searchCriteria = Integer.parseInt(Utils.SCANNER.nextLine());
                    if (searchCriteria == 1) {
                        System.out.println("Enter the name of the product: ");
                        String name = Utils.SCANNER.nextLine();
                        sellerMarket.search(name, null, null);
                    } else if (searchCriteria == 2) {
                        System.out.println("Enter the storeId of the product: ");
                        String storeId = Utils.SCANNER.nextLine();
                        sellerMarket.search(null, storeId, null);
                    } else if (searchCriteria == 3) {
                        System.out.println("Enter the description of the product: ");
                        String description = Utils.SCANNER.nextLine();
                        sellerMarket.search(null, null, description);
                    } else {
                        valid = false;
                    }
                } while (!valid);
            } else if (customerAction == 3) {
                sellerMarket.displayDashboard();
            } else if (customerAction == 4) {
                System.out.println("Would you like to sort by price (y) or quantity (n)");
                int sortCriteria = Utils.yesOrNoToInt(Utils.SCANNER.nextLine());
                if (sortCriteria == 1) {
                    sellerMarket.sort(true, false);
                } else {
                    sellerMarket.sort(false, true);
                }

            } else if (customerAction == 5) {
                sellerMarket.displayCart();
            } else {
                System.out.println("Please do choose a valid option.");
                error = false;
            }
        } while (!error);
        //throw new UnsupportedOperationException("Unsupported operation: 'sellerLoop'");
    }
}
