import java.util.ArrayList;

public class CustomerDashboard extends Dashboard<Store, Store> implements DashboardInterface<Store, Store> {
    public CustomerDashboard(ArrayList<Store> data1, ArrayList<Store> data2) {
        super(data1, data2);
    }

    // Sort stores either by products sold or products purchased by a customer
    @Override
    public ArrayList<Store> sort1() {
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    @Override
    public ArrayList<Store> sort2() {
        throw new UnsupportedOperationException("Unimplemented method 'sort2'");
    }

    // Displays the two lists of stores
    @Override
    public void displayDashboard() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}
