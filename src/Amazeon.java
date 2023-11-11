public class Amazeon {
    public static void main(String[] args) {
        CustomerMarket customerMarket;
        SellerMarket sellerMarket;
        Customer customer;
        Seller seller;

        // Welcome the user
        if (hasAccount()) {
            // Login
            if (isCustomer()) {
                // Create an initial market
                customerMarket = new CustomerMarket(null, null, null);
                customer = customerMarket.authentication();
            } else {
                sellerMarket = new SellerMarket(null, null, null);
                seller = sellerMarket.authentication();
            }
        } else {
            // Create
            if (isCustomer()) {
                customerMarket = new CustomerMarket(null, null, null);
                customer = customerMarket.authentication();
            } else {
                sellerMarket = new SellerMarket(null, null, null);
                seller = sellerMarket.authentication();
            }
        }
        // Create Customer or Seller market

    }

    // Entry point for the program
    public static boolean hasAccount() {
        int hasAccount = Utils.inputPrompt("Welcome to Amazeon! Do you have an account? (y/n)");
        switch (hasAccount) {
            case Utils.YES:
                return true;
            case Utils.NO:
                return false;
            default:
                return false; // Never calls
        }
    }

    public static boolean isCustomer() {
        int userType = Utils.inputPrompt("Are you a customer (y) or a seller (n)?");
        switch (userType) {
            case Utils.YES:
                return true;
            case Utils.NO:
                return false;
            default:
                return false; // Never calls
        }
    }
}
