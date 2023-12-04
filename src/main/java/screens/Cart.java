package screens;

import models.Product;

import java.io.IOException;
import java.util.ArrayList;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;

    public Cart(int customerID, ArrayList<Product> cartProducts) throws IOException {
        this.customerID = customerID;
        this.cartProducts = (cartProducts != null) ? cartProducts : new ArrayList<Product>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
