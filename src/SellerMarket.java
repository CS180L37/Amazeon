import java.util.ArrayList;

public class SellerMarket extends Market<Seller> implements MarketInterface<Seller, Customer, Product> {
    private Dashboard<Customer, Product> dashboard;

    public SellerMarket(ArrayList<Store> stores, Seller seller, Dashboard<Customer, Product> dashboard) {
        super(stores, seller);
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

    @Override
    public Seller authentication() {
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
