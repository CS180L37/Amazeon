public class CustomerMarket extends Market<Customer> implements MarketInterface {

    public CustomerMarket(ArrayList<Store> stores, Customer user, Dashboard<Customer> dashboard) {
        super(stores, user, dashboard);
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

    // When clicking on an individual product
    @Override
    public void productPage(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'productPage'");
    }

    @Override
    public void dashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'dashboard'");
    }

}
