public class CustomerDashboard extends Dashboard<Store> implements DashboardInterface<Store> {
    public CustomerDashboard(Store[] data) {
        super(data);
    }

    @Override
    public Store[] sort() {
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

}
