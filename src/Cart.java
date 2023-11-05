import java.util.ArrayList;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;

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
    public void addToCart(Product product) {
        try {
            cartProducts.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Purchases all the products in the cart for the specific customer
    public void purchaseCart() {
        for (Product p : cartProducts) {
            Customer.getCustomerById(customerID).purchaseProduct(p);
            cartProducts.remove(p);
        }
    }

    public void display() {
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}
