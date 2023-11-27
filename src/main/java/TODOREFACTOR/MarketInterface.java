package TODOREFACTOR;

public interface MarketInterface<T, U, V> {
    public void displayMarketplace(); // Entry point for `SellerMarket` or `CustomerMarket`

    public void displayDashboard(); // Display the dashboard

    public void displayProductPage(Product product); // Display the dashboard

    public void displayCart(); // Display the customer's cart or the seller page for viewing customers carts
}
