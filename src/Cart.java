import models.Product;

import java.beans.Customizer;
import java.util.ArrayList;


public class Cart {
    private static int customerID;
    private static ArrayList<Product> cartProducts;
    private static ArrayList<Product> purchaseProducts = new ArrayList<>();
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

    public static ArrayList<Product> getPurchaseProducts() {
        return purchaseProducts;
    }

    public void purchaseCart() {
        for (Product p : cartProducts) {
            getPurchaseProducts().add(p);
        }
    }
}
