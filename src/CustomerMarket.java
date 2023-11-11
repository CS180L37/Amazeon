import java.util.ArrayList;
import org.json.*; // Work with json: https://github.com/stleary/JSON-java

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
        String email = Utils.inputPrompt("Enter your email: ", input -> Utils.validateEmail(input),
                "Enter a valid email: ");
        String password = Utils.inputPrompt("Enter your password: ", input -> (input.length() > 7),
                "Enter a password with at least 8 characters: ");
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
