import java.util.ArrayList;

public class CustomerDashboard extends Dashboard<Store, Store> implements DashboardInterface<Store, Store> {
    // Create a dashboard of existing Store data
    public CustomerDashboard(ArrayList<Store> data1, ArrayList<Store> data2) {
        super(data1, data2);
    }

    // 2 sort methods exist to account for seller having to sort two different data
    // types
    // Sort stores by products sold
    @Override
    public ArrayList<Store> sort1(User user) {
        ArrayList<Product> sortedProducts = new ArrayList<Product>();
        for (int i = 0; i < user.getProducts().size(); i++) {
            sortedProducts.add(user.getProducts().get(i));
        }

        for (int i = 0; i < user.getProducts().size(); i++) {
            int maxIndex = i;
            for (int j = 0; i < user.getProducts().size(); j++) {
                if (user.getProducts().get(i).getQuantity() > user.getProducts().get(maxIndex).getQuantity()) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                Product product = user.getProducts().get(maxIndex);
                user.getProducts().set(maxIndex, user.getProducts().get(i));
                user.getProducts().set(maxIndex, product);
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'sort'");

    }

    // Sort stores by products purchased by a customer
    @Override
    public ArrayList<Store> sort2(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'sort2'");
    }

    // Displays the two lists of stores
    @Override
    public void displayDashboard() {

        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}
