import java.util.ArrayList;

public class SellerMarket extends Market<Seller> implements MarketInterface<Seller, Customer, Product> {
    private Dashboard<Customer, Product> dashboard;

    public SellerMarket(Seller seller, ArrayList<Store> stores, ArrayList<Customer> customers,
            ArrayList<Product> products) {
        super(seller, stores); // Retrieve existing stores and create a new customer
        this.dashboard = new Dashboard<Customer, Product>(customers, products);
    }

    public SellerMarket(Seller seller, ArrayList<Store> stores, Dashboard<Customer, Product> dashboard) {
        super(seller, stores);
        this.dashboard = dashboard;
    }

    @Override
    public void displayMarketplace() {
        throw new UnsupportedOperationException("Unimplemented method 'displayMarketplace'");
    }

    // For creating, editing, or deleting a product
    @Override
    public void displayProductPage(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'displayProductPage'");
    }

    @Override
    public void displayDashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'displayDashboard'");
    }

    @Override
    public Dashboard<Customer, Product> getDashboard() {
        return dashboard;
    }

    @Override
    public void setDashboard(Dashboard<Customer, Product> dashboard) {
        this.dashboard = dashboard;
    }

    // Display all of the customers carts, along with their store and product
    // details
    @Override
    public void displayCart() {
        throw new UnsupportedOperationException("Unimplemented method 'displayCart'");
    }
}
