public interface MarketInterface<T, U> {
    public void displayMarketplace(); // Entry point for `SellerMarket` or `CustomerMarket`

    Dashboard<T, U> getDashboard(); // Create a Dashboard

    void setDashboard(Dashboard<T, U> dashboard); // Set a Dashboard

    public void displayDashboard(); // Display the dashboard

    public void displayProductPage(Product product); // Display the dashboard

    public void displayCart(); // Display the customer's cart or the seller page for viewing customers carts
}
