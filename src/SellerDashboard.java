import java.util.ArrayList;

public class SellerDashboard extends Dashboard<Customer, Product> implements DashboardInterface<Customer, Product> {

    public SellerDashboard() {
        super();
    }

    public SellerDashboard(ArrayList<Customer> data1, ArrayList<Product> data2) {
        super(data1, data2);
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

    // Display list of customers and products
    @Override
    public void displayDashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}
