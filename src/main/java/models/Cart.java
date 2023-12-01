package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;
    private static CollectionReference cartsCollection = Utils.db.collection("customers");
    private DocumentReference documentReference;
    private String documentName;



    private Cart(int customerID, ArrayList<Product> cartProducts) throws IOException {
        this.customerID = customerID;
        this.cartProducts = (cartProducts != null) ? cartProducts : new ArrayList<Product>();
        this.documentReference = getCartDocument();

    }
    private Cart(QueryDocumentSnapshot document) throws IOException {
        this.customerID = Math.toIntExact(document.getLong("customerId"));
        this.cartProducts = Product.getProductsByIds((List<Integer>) document.getData().get("productIds"));
        this.documentReference = getCartDocument();
        this.documentName = document.getName
    }

    // TODO: alternative constructor
    public static Cart createCart(int customerId) {
        return null;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
        updateRemoteCart("customerId", getCustomerID());
    }

    private void updateRemoteCart(String remoteFieldName, Object value) {
        HashMap<String, Object> data2 = new HashMap<String, Object>();
        data2.put(remoteFieldName, value);
        this.documentReference.update(data2);
    }


    public void setCartProducts(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
        updateRemoteCart("productIds", getCartProductIds());
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public ArrayList<Product> getCartProducts() {
        return this.cartProducts;
    }

    // Adds the product to cartProducts
    public void addToCart(Product product) {
        cartProducts.add(product);
        updateRemoteCart("productIds", getCartProductIds());
    }

    // Removes the product from cartProducts
    public void removeFromCart(Product product) throws IOException {
        cartProducts.remove(product);
        updateRemoteCart("productIds", getCartProductIds());
    }

    public ArrayList<Integer> getCartProductIds() {
        return (ArrayList<Integer>) cartProducts.stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());
    }
    // Purchases all the products in the cart for the specific customer
    //since no sale happening thingy, only noticeable change is cart is now empty
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
    private DocumentReference getCartDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = cartsCollection
                .whereEqualTo("customerId", this.getCustomerID())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the customer document");
        }
        return documents.get(0).getReference();
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

    //shouldnt be used because carts are created w customers, synonymous w customers
    //id recommend using getnextcustomerid instead, too
    public static int getNextCartId() throws IOException {
        ApiFuture<QuerySnapshot> future = cartsCollection.orderBy("customerId", Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("customerId").intValue() + 1;
    }
}
