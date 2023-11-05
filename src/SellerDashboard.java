public class SellerDashboard extends Dashboard<Customer> implements DashboardInterface<Customer> {

    public SellerDashboard(Customer[] data) {
        super(data);
    }

    @Override
    public Customer[] sort() {
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

}
