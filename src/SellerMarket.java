public class SellerMarket extends Market<Seller> implements MarketInterface {
    public SellerMarket(Store[] stores, Seller seller, Dashboard<Seller> dashboard) {
        super(stores, seller, dashboard);
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'marketplace'");
    }

    // Use the customers field to determine
    public void productPage() {
        throw new UnsupportedOperationException("Unimplemented method 'productPage'");
    }

    @Override
    public void dashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'dashboard'");
    }
}
