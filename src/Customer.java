import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Customer extends User implements UserInterface<Customer> {
    private Cart cart;

    public Customer(String email, String password) {
        super(Amazeon.getNextCustomerId(), new ArrayList<Product>(), email, password);
        this.cart = new Cart(this.getId(), this.getProducts());
    }

    public Customer(int id, String email, String password, ArrayList<Product> products, Cart cart) {
        super(id, products, email, password);
        this.cart = cart;
    }

    // Exports customer purchase history
    @Override
    public void exportData(String filepath) {
        throw new UnsupportedOperationException("Unsupported operation 'exportPurchaseHistory");
    }

    // unnecessary method
    @Override
    public void importData(String filepath) {
        return;
    }

    // Purchases a product
    public void purchaseProduct(Product product) {
        throw new UnsupportedOperationException("Unsupported operation 'purchaseProduct");
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public Customer createAccount() {
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

    @Override
    public Customer editAccount() {
        throw new UnsupportedOperationException("Unimplemented method 'editAccount'");
    }

    @Override
    public void deleteAccount() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    // Contains lists of all products and carts as parameters
    public static ArrayList<Customer> readCustomers(ArrayList<Product> products, ArrayList<Cart> carts) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            BufferedReader bfr = Utils.createReader(Utils.DATA_DIR + Utils.CUSTOMER_FILE);
            String line;
            while (true) {
                line = bfr.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                customers.add(new Customer(Integer.parseInt(data[0]), data[1], data[2],
                        Amazeon.getProductByIds(Utils.splitIdsByPipe(data[3])),
                        Amazeon.getCartById(Integer.parseInt(data[0]))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<Customer>();
    }

    public static void writeCustomers(ArrayList<Customer> customers) {
        try {
            BufferedWriter bw = Utils.createWriter(Utils.DATA_DIR + Utils.CUSTOMER_FILE);
            for (Customer customer : customers) {
                bw.write(String.format(Integer.toString(Integer.parseInt(customer.getId() + "," + Amazeon.getEmail()
                        + "," + Amazeon.getPassword() + "," + Amazeon.getProductById(customer.getId()) + "," +
                        Amazeon.getCartById(customer.getId())))));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }    }
}
