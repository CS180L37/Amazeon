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

    public void addToCart(Product product) {
        try {
            cartProducts.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void purchaseCart() {
        for (Product p : cartProducts) {
            Customer.getCustomerById(customerID).purchaseProduct(p);
            cartProducts.remove(p);
        }
    }
}
