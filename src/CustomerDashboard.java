import java.util.ArrayList;

public class CustomerDashboard extends Dashboard<Store> implements DashboardInterface<Store> {
    public CustomerDashboard(ArrayList<Store> data) {
        super(data);
    }

    @Override
    public ArrayList<Store> sort() {
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

}
