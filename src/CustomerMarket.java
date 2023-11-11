import java.util.ArrayList;

public class CustomerMarket extends Market<Customer> implements MarketInterface<Customer, Store, Store> {
    private Dashboard<Store, Store> dashboard;

    public CustomerMarket(ArrayList<Store> stores, Customer user, Dashboard<Store, Store> dashboard) {
        super(stores, user);
        this.dashboard = dashboard;
    }

    public static void main(String[] args) {
        CustomerMarket customerMarket = new CustomerMarket(null, null, null);
        if (hasAccount()) {
            // Login
            customerMarket.setUser(customerMarket.authentication());
        } else {
            // Create
            customerMarket.setUser(customerMarket.authentication());
        }

    }

    // Displays the list of products
    @Override
    public void displayMarketplace() {
        throw new UnsupportedOperationException("Unimplemented method 'displayMarketplace'");
    }

    // When clicking on an individual product
    @Override
    public void displayProductPage(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'displayProductPage'");
    }

    // Display/work with the dashboard for customers
    @Override
    public void displayDashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'displayDashboard'");
    }

    @Override
    public Dashboard<Store, Store> getDashboard() {
        return dashboard;
    }

    @Override
    public void setDashboard(Dashboard<Store, Store> dashboard) {
        this.dashboard = dashboard;
    }

    // Sort the marketplace
    public void sort(boolean price, boolean quantityAvailable) {
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    // Search the marketplace by name, storeId, or description
    public ArrayList<Product> search(String name, int storeId, String description) {
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    // A utility method for displaying the users cart; just calls display() from
    // the cart class
    @Override
    public void displayCart() {
        this.getUser().getCart().display();
    }

    @Override
    public Customer authentication() {
        // public T loginProcess() {
        // System.out.println("Enter your email: ");
        // String email = scanner.nextLine();
        // System.out.println("Enter your password: ");
        // String password = scanner.nextLine();
        // System.out.println("Would you like to create an account as a customer (y) or
        // seller (n)?");
        // String userType;
        // do {
        // usrType = scanner.nextLine();
        // if (Utils.validInput(userType) != Utils.ERROR) {
        // break;
        // }
        // } while (true);
        // switch (Utils.validInput(userType)) {
        // case Utils.YES:
        // int customerId = createAccount(email, password, "Customer");
        // user = Customer.getCustomerById(customerId);
        // break;
        // case Utils.NO:
        // int sellerId = createAccount(email, password, "Seller");
        // user = Seller.getSellerById(sellerId);
        // break;
        // default:
        // break;
        // }
        // }
    }
}
