import java.util.ArrayList;

public class Customer extends User implements UserInterface<Customer> {
    private Cart cart;
    // Contains id, email, password, and a list of purchased products


    public Customer(String email, String password) {
        super(Amazeon.getNextCustomerId(), new ArrayList<Product>(), email, password);
        this.cart = new Cart(this.getId(), this.getProducts());
    }

    public Customer(int id, ArrayList<Product> products, String email, String password, Cart cart) {
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

        //takes you to product page --> method has to call displayProduct()
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
        throw new UnsupportedOperationException("Unimplemented method 'readCustomers'");
    }

    public static void writeCustomers(ArrayList<Customer> customers) {
        throw new UnsupportedOperationException("Unimplemented method 'readCustomers'");
    }
}
