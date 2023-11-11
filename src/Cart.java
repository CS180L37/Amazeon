import java.util.ArrayList;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;

    public Cart(int customerID, ArrayList<Product> cartProducts) {
        this.customerID = customerID;
        this.cartProducts = cartProducts;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCartProducts(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public ArrayList<Product> getCartProducts() {
        return this.cartProducts;
    }

    // Adds the product to cartProducts
    public boolean addToCart(Product product) {
        return cartProducts.add(product);
    }

    // Removes the product from cartProducts
    public boolean removeFromCart(Product product) {
        return cartProducts.remove(product);
    }

    // Purchases all the products in the cart for the specific customer
    public void purchaseCart() {
        for (Product p : cartProducts) {
            Amazeon.getCustomerById(customerID).purchaseProduct(p);
            cartProducts.remove(p);
        }
    }

    // Display the customers cart
    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

    // Contains a list of all products as a parameter
    public static ArrayList<Cart> readCarts(ArrayList<Product> products) {
        throw new UnsupportedOperationException("Unimplemented method 'readCarts'");
    }

    public static void writeCarts(ArrayList<Cart> carts) {
        throw new UnsupportedOperationException("Unimplemented method 'readCarts'");
    }
}
