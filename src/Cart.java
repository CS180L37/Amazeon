import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;

    public Cart(int customerID, ArrayList<Product> cartProducts) {
        this.customerID = customerID;
        this.cartProducts = (cartProducts != null) ? cartProducts : new ArrayList<Product>();
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
    public boolean addToCart (Product product) {
        return cart Products.add(product);
     }

    // Removes the product from cart Products
    public boolean removeFromCart (Product product) {
        return cartProducts.remove (product);
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
        System.out.println("Customer ID: " + customerID);
        System.out.println("Cart Contents: ");

        for (Product product : cartProducts) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getName());
        }
    }

    @Override
    public String to String() {
        return "Cart {" +
                "customerID =" + customerID +
                ", cartProducts =" + cartProducts +
                '} ';
     }
}
