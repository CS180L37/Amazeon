import java.util.ArrayList;

public class SellerMarket extends Market<Seller> implements MarketInterface {
    public SellerMarket(ArrayList<Store> stores, Seller seller, Dashboard<Seller> dashboard) {
        super(stores, seller, dashboard);
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'marketplace'");
    }

    // For creating, editing, or deleting a product
    @Override
    public void productPage(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'productPage'");
    }

    @Override
    public void dashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'dashboard'");
    }
}
