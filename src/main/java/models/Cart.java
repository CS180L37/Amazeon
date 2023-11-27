package models;

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
        System.out.println("Customer ID: " + customerID);
        System.out.println("Cart Contents: ");

        for (Product product : cartProducts) {
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Product Name: " + product.getName());
        }
    }

    // TODO: adapt these for backend
    // TODO: adapt the "to string" methods along with the constructors
    public static Cart getCartById(int cartId) {
        for (Cart cart : Amazeon.carts) {
            if (cart.getCustomerID() == cartId) {
                return cart;
            }
        }
        return new Cart(-1, new ArrayList<Product>());
    }

    public static ArrayList<Cart> getCartsByIds(ArrayList<Integer> cartIds) {
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        for (int cartID : cartIds) {
            cartList.add(getCartById(cartID));
        }
        return cartList;
    }

    public static int getNextCartId() {
        // ArrayList<Integer> cartIDs = new ArrayList<Integer>();
        // for (Cart cart : cartList) {
        // cartIDs.add(cart.getCustomerID());
        // }
        // return cartIDs;
        return 0;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "customerID=" + customerID +
                ", cartProducts=" + cartProducts +
                '}';
    }
}
