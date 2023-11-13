import java.util.ArrayList;

public class SellerMarket extends Market<Seller> implements MarketInterface<Seller, Customer, Product> {
    private SellerDashboard dashboard;

    public SellerMarket(Seller seller, ArrayList<Store> stores, ArrayList<Customer> customers,
            ArrayList<Product> products) {
        super(seller, stores); // Retrieve existing stores and create a new customer
        this.dashboard = new SellerDashboard(customers, products);
    }

    public SellerMarket(Seller seller, ArrayList<Store> stores, SellerDashboard dashboard) {
        super(seller, stores);
        this.dashboard = dashboard;
    }

    @Override
    public void displayMarketplace() {
        this.getUser().displayProducts();
    }

    @Override
    public void displayDashboard() {
        this.getUser().displayDashboard();
    }

    public SellerDashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(SellerDashboard dashboard) {
        this.dashboard = dashboard;
    }

    // Display all of the customers carts, along with their store and product
    // details
    @Override
    public void displayCart() {
        for (Customer customer : Amazeon.customers) {
            for (Product product : customer.getCart().getCartProducts()) {
                if (Product.getSellerId() == this.getUser().getId()) {
                    System.out.println(Product.toString());
                }
            }
        }
    }

    // Never called
    @Override
    public void displayProductPage(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'displayProductPage'");
    }
}
