import java.util.ArrayList;

public class SellerMarket extends Market<Seller> implements MarketInterface<Customer, Product> {
    private Dashboard<Customer, Product> dashboard;

    public SellerMarket(ArrayList<Store> stores, Seller seller, Dashboard<Customer, Product> dashboard) {
        super(stores, seller);
        this.dashboard = dashboard;
    }

    @Override
    public void displayMarketplace() {
        throw new UnsupportedOperationException("Unimplemented method 'marketplace'");
    }

    // For creating, editing, or deleting a product
    @Override
    public void displayProductPage(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'productPage'");
    }

    @Override
    public void displayDashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'dashboard'");
    }

    @Override
    public Dashboard<Customer, Product> getDashboard() {
        return dashboard;
    }

    @Override
    public void setDashboard(Dashboard<Customer, Product> dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void displayCart() {
        throw new UnsupportedOperationException("Unimplemented method 'displayCart'");
    }

    public static void main(String[] args) {
        System.out.println("""
                1.
                2.
                3.
                4.
                5.
                """);
    }
}
