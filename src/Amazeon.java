public class Amazeon {
    public static void main(String[] args) {
        // Welcome the user
        if (hasAccount()) {
            // Login
            if (isCustomer()) {
                Customer customer = authentication("customer");
            } else {
                Seller seller = authentication("seller");
            }
        } else {
            // Create
            if (isCustomer()) {
                Customer customer = authentication("customer");
            } else {
                Seller seller = authentication("seller");
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
