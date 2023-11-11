public interface MarketInterface<T, U, V> {
    public void displayMarketplace(); // Entry point for `SellerMarket` or `CustomerMarket`

    Dashboard<U, V> getDashboard(); // Create a Dashboard

    void setDashboard(Dashboard<U, V> dashboard); // Set a Dashboard

    public void displayDashboard(); // Display the dashboard

    public void displayProductPage(Product product); // Display the dashboard

    public void displayCart(); // Display the customer's cart or the seller page for viewing customers carts

    public T authentication(AuthenticationType authType); // Prompts user, calls login or create account, returns a user

    public int login(String email, String password); // Returns a User of type T; reads data from .user.json

    public int createAccount(String email, String password); // Returns a User of type T; creates a default user,
                                                             // writes
    // to .user.json
}
