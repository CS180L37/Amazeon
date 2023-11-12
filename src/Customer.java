import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Customer extends User implements UserInterface<Customer> {
    private Cart cart;
    // Contains id, email, password, and a list of purchased products

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

        // takes you to product page --> method has to call displayProduct()
        throw new UnsupportedOperationException("Unsupported operation 'purchaseProduct");
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void editAccount(String email, String password) {
        if (Utils.validateEmail(email)) {
            this.setEmail(email);
        } else {
            System.out.println("Make sure to enter a valid email if you want to change your email!");
        }
        if (Utils.validatePassword(password)) {
            this.setPassword(password);
        } else {
            System.out.println("Make sure to enter a valid email if you want to change your email!");
        }
    }

    @Override
    public void deleteAccount() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    // Contains lists of all products and carts as parameters
    public static ArrayList<Customer> readCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            BufferedReader br = Utils.createReader(Utils.DATA_DIR + Utils.CUSTOMER_FILE);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                customers.add(new Customer(Integer.parseInt(data[0]), data[1], data[2],
                        (!data[3].equals(Utils.NA)) ? Amazeon.getProductByIds(Utils.splitIdsByPipe(data[3]))
                                : new ArrayList<Product>(),
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
                bw.write(Integer.toString(Integer.parseInt(customer.getId() + "," + customer.getEmail()
                        + "," + customer.getPassword() + ","
                        + Utils.convertToIdString(Amazeon.getProductIds(customer.getProducts()).toString()))));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
