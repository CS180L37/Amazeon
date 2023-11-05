import java.util.ArrayList;

public class Customer extends User implements UserInterface {
    private Cart cart;

    public Customer(int id, ArrayList<Product> products, Cart cart) {
        super(id, products);
        this.cart = cart;
    }

    @Override
    public void exportData() {
        throw new UnsupportedOperationException("Unsupported operation 'exportPurchaseHistory");
    }

    @Override
    public void importData() {
        throw new UnsupportedOperationException("Unimplemented method 'importData'");
    }

    public void purchaseProduct(Product product) {
        throw new UnsupportedOperationException("Unsupported operation 'purchaseProduct");
    }

    public static Customer getCustomerById(int customerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
