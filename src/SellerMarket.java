import java.util.ArrayList;

public class SellerMarket extends Market<Seller> implements MarketInterface<Seller, Customer, Product> {
    private Dashboard<Customer, Product> dashboard;
    private ArrayList<Store> sellerStores = new ArrayList<>();

    public ArrayList<Store> getSellerStores() {
        return sellerStores;
    }

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
    public Seller authentication(AuthenticationType authType) {
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
        int sellerId;
        switch (authType) {
            case LOGIN:
                sellerId = login(email, password);
                return Seller.getSellerById(sellerId);
            case CREATE:
                sellerId = createAccount(email, password);
                return Seller.getSellerById(sellerId);
            default:
                return Seller.getSellerById(0); // Never
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
