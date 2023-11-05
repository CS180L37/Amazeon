import java.util.ArrayList;

public class SellerDashboard extends Dashboard<Customer> implements DashboardInterface<Customer> {

    public SellerDashboard(ArrayList<Customer> data) {
        super(data);
    }

    @Override
    public ArrayList<Customer> sort() {
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

}
