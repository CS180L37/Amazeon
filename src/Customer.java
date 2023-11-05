import java.util.ArrayList;

public class Customer extends User implements UserInterface {
    private Cart cart;

    public Customer(int id, ArrayList<Product> products, Cart cart) {
        super(id, products);
        this.cart = cart;
    }

    // Exports customer purchase history
    @Override
    public void exportData() {
        throw new UnsupportedOperationException("Unsupported operation 'exportPurchaseHistory");
    }

    // unnecessary method
    @Override
    public void importData() {
        return;
    }

    // Purchases a product
    public void purchaseProduct(Product product) {
        throw new UnsupportedOperationException("Unsupported operation 'purchaseProduct");
    }

    // Retrieves customer by id
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
