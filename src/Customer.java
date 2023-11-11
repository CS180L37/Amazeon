public class Customer extends User implements UserInterface {
    private Cart cart;

    public Customer(int id, ArrayList<Product> products, Cart cart) {
        super(id, products);
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
>>>>>>> 58bfc70bedd02e125b07541c3f3eeb996374742a
}
