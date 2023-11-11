import java.util.ArrayList;

public class CustomerMarket extends Market<Customer> implements MarketInterface<Customer, Store, Store> {
    private Dashboard<Store, Store> dashboard;

    public CustomerMarket(ArrayList<Store> stores, Customer user, Dashboard<Store, Store> dashboard) {
        super(stores, user);
        this.dashboard = dashboard;
    }

    public static void main(String[] args) {
        welcomeProcess();
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
        throw new UnsupportedOperationException("Unimplemented method 'authentication'");
    }
}
