package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Filter;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;
    private static CollectionReference cartsCollection = Utils.db.collection("customers");


    private Cart(int customerID, ArrayList<Product> cartProducts) {
        this.customerID = customerID;
        this.cartProducts = (cartProducts != null) ? cartProducts : new ArrayList<Product>();

    }
    private Cart(QueryDocumentSnapshot document) throws IOException {
        this.customerID = Math.toIntExact(document.getLong("customerId"));
        this.cartProducts = Product.getProductsByIds((List<Integer>) document.getData().get("productIds"));
    }

    // TODO: alternative constructor
    public static Cart createCart(int customerId) {
        return null;
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
        // for (Product p : cartProducts) {
        // Amazeon.getCustomerById(customerID).purchaseProduct(p);
        // cartProducts.remove(p);
        // }
        setCartProducts(new ArrayList<Product>());
    }

    // Display the customers cart
    //redundant, just call getcartproducts
    public void display() {
        // System.out.println("Customer ID: " + customerID);
        // System.out.println("Cart Contents: ");

        // for (Product product : cartProducts) {
        // System.out.println("Product ID: " + product.getProductId());
        // System.out.println("Product Name: " + product.getName());
        // }
    }

    // TODO: adapt these for backend
    // TODO: adapt the "to string" methods along with the constructors
    public static Cart getCartById(int givenCustomerId) throws IOException {
        ApiFuture<QuerySnapshot> future = cartsCollection.select("customerId")
                .where(Filter.equalTo("customerId", givenCustomerId)).limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return new Cart(documents.get(0));
    }

    public static ArrayList<Cart> getCartsByIds(List<Integer> cartIds) throws IOException {
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
}
