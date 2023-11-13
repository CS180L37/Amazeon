import java.util.ArrayList;

/// Our entry point and data manager class
public class Amazeon {
    // The data that persists
    public String pathToDataDir;

    public static ArrayList<Product> products;
    public static ArrayList<Cart> carts;
    public static ArrayList<Customer> customers;
    public static ArrayList<Sale> sales;
    public static ArrayList<Store> stores;
    public static ArrayList<Seller> sellers;

    public Amazeon(String pathToDataDir) {
        this.pathToDataDir = pathToDataDir;
        if (products == null) {
            products = new ArrayList<>();
        }
        if (carts == null) {
            carts = new ArrayList<>();
        }
        if (customers == null) {
            customers = new ArrayList<>();
        }
        if (sales == null) {
            sales = new ArrayList<>();
        }
        if (stores == null) {
            stores = new ArrayList<>();
        }
        if (sellers == null) {
            sellers = new ArrayList<>();
        }
        // Amazeon.products = Product.readProducts(pathToDataDir + Utils.PRODUCT_FILE);
        // System.out.println(Amazeon.products);
        // Amazeon.carts = Cart.readCarts(pathToDataDir + Utils.CART_FILE);
        // Amazeon.customers = Customer.readCustomers(pathToDataDir +
        // Utils.CUSTOMER_FILE);
        // Amazeon.sales = Sale.readSales(pathToDataDir + Utils.SALE_FILE);
        // Amazeon.stores = Store.readStores(pathToDataDir + Utils.STORE_FILE);
        // Amazeon.sellers = Seller.readSellers(pathToDataDir + Utils.SELLER_FILE);
    }

    public static void main(String[] args) {
        // Initialize data
        Amazeon amazeon = new Amazeon(Utils.DATA_DIR);
        Seller.readSellers(amazeon.pathToDataDir);
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
                        Amazeon.customers, Amazeon.products);
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
        Product.writeProducts(Amazeon.products, this.getPathToDataDir() + Utils.PRODUCT_FILE);
        Cart.writeCarts(Amazeon.carts, this.getPathToDataDir() + Utils.CART_FILE);
        Customer.writeCustomers(Amazeon.customers, this.getPathToDataDir() + Utils.CUSTOMER_FILE);
        Sale.writeSales(Amazeon.sales, this.getPathToDataDir() + Utils.SALE_FILE);
        Store.writeStores(Amazeon.stores, this.getPathToDataDir() + Utils.STORE_FILE);
        Seller.writeSellers(Amazeon.sellers, this.getPathToDataDir() + Utils.SELLER_FILE);
    }

    // Product methods
    public static Product getProductById(int id) {
        System.out.println(Amazeon.products);
        for (Product product : Amazeon.products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return new Product(-1, "", -1, "", -1, -1, -1);
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
            customerListSize = 0;
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
        return new Seller(-1, new ArrayList<>(),"", "", new ArrayList<>());
    }

    public static Seller getSellerById(int sellerId) {
        for (Seller seller : Amazeon.sellers) {
            if (seller.getId() == sellerId) {
                return seller;
            }
        }
        return new Seller(-1, new ArrayList<>(),"", "", new ArrayList<>());
    }

    public static int getNextSellerId() {
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

    public void customerLoop(CustomerMarket customerMarket, Customer customer) {
        customerMarket.displayMarketplace();
        do {
            System.out.println(
                    "What would you like to do?\n1) Purchase\n2) Search\n3) Display Dashboard\n4) Sort MarketPlace\n5) View Cart\n6 Log Out");
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
            } else if (customerAction == 6) {
                break;
            }
            else {
                System.out.println("Please do choose a valid option.");
            }
        } while (true);
        // throw new UnsupportedOperationException("Unsupported operation:
        // 'customerLoop'");
    }

    public void sellerLoop(SellerMarket sellerMarket, Seller seller) {
        sellerMarket.displayMarketplace();
        do {
            System.out.println(
                    "What would you like to do?\n1) Create\n2) Edit\n3) Delete\n4) View Sales\n5) Display Dashboard" +
                            "\n6) View Carts\n7) Log Out");

            int sellerAction = Integer.parseInt(Utils.SCANNER.nextLine());
            if (sellerAction == 1) {
                seller.createProduct();
            } else if (sellerAction == 2) {
                System.out.println("Enter productId of product you would like to edit: ");
                int productId = Integer.parseInt(Utils.SCANNER.nextLine());
                seller.updateProduct(productId);
            } else if (sellerAction == 3) {
                System.out.println("Enter productId of product you would like to delete: ");
                int productId = Integer.parseInt(Utils.SCANNER.nextLine());
                seller.deleteProduct(getProductById(productId));
            } else if (sellerAction == 4) {
                seller.viewSales();
            } else if (sellerAction == 5) {
                sellerMarket.displayDashboard();
            } else if (sellerAction == 6) {
                sellerMarket.displayCart();
            } else if (sellerAction == 7) {
                break;
            } else {
                System.out.println("Please choose a valid option.");
            }
        } while (true);
    }

    public String getPathToDataDir() {
        return pathToDataDir;
    }

    public void setPathToDataDir(String pathToDataDir) {
        this.pathToDataDir = pathToDataDir;
    }
}