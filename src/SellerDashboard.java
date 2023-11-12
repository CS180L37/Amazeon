import java.util.ArrayList;

public class SellerDashboard extends Dashboard<Customer, Product> implements DashboardInterface<Customer, Product> {

    public SellerDashboard() {
        super();
    }

    public SellerDashboard(ArrayList<Customer> customers, ArrayList<Product> products) {
        super(customers, products);
    }

    // Sort customers by number of items purchased
    @Override
    public ArrayList<Customer> sort1(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'sort1'");
    }

    @Override
    public ArrayList<Product> sort2(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'sort2'");
    }

    @Override
    public void displayDashboard(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'displayDashboard'");
    }
}
