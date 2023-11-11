import java.util.ArrayList;

public class CustomerMarket extends Market<Customer> implements MarketInterface<Customer, Store, Store> {
    private Dashboard<Store, Store> dashboard;

    public CustomerMarket(ArrayList<Store> stores, Customer user, Dashboard<Store, Store> dashboard) {
        super(stores, user);
        this.dashboard = dashboard;
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
    public Customer authentication(AuthenticationType authType) {
        System.out.println("Enter your email: ");
        String email;
        do {
            email = Utils.SCANNER.nextLine();
            if (Utils.validateEmail(email)) {
                break;
            }
            System.out.println("Enter a valid email: ");
        } while (true);
        System.out.println("Enter your password: ");
        String password;
        do {
            password = Utils.SCANNER.nextLine();
            if (password.length() > 8) {
                break;
            }
            System.out.println("Enter a password greater than 8 characters: ");
        } while (true);
        int customerId;
        switch (authType) {
            case LOGIN:
                customerId = login(email, password);
                return Customer.getCustomerById(customerId);
            case CREATE:
                customerId = createAccount(email, password);
                return Customer.getCustomerById(customerId);
            default:
                return Customer.getCustomerById(0); // Never
        }
    }

    @Override
    public int login(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public int createAccount(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }
}
