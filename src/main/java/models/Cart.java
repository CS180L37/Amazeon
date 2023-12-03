package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.checkerframework.checker.units.qual.C;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

public class Cart {
    private int customerID;
    private ArrayList<Product> cartProducts;
    public static CollectionReference cartsCollection;
    private DocumentReference documentReference;

    public static void main(String[] args) {
        System.out.println(cartsCollection.toString());
    }

    Cart(int customerID, ArrayList<Product> cartProducts) throws IOException {
        cartsCollection = Utils.db.collection("carts");
        this.customerID = customerID;
        this.cartProducts = (cartProducts != null) ? cartProducts : new ArrayList<Product>();
        this.documentReference = getCartDocument();
    }

    private Cart(QueryDocumentSnapshot document) throws IOException {
        cartsCollection = Utils.db.collection("carts");
        this.customerID = Math.toIntExact(document.getLong("customerId"));
        this.cartProducts = Product.getProductsByIds((List<Integer>) document.getData().get("productIds"));
        this.documentReference = getCartDocument();
    }

    public static Cart createCart(int customerId) throws IOException {
        Customer currCustomer = Customer.getCustomerById(customerId);
        Cart newCart = new Cart(currCustomer.getCustomerId(), new ArrayList<>());
        HashMap<String, Object> data = new HashMap<>();
        data.put("customerId", currCustomer.getCustomerId());
        data.put("productIds", "");
        cartsCollection.document(currCustomer.getEmail()).set(data);
        newCart.documentReference = cartsCollection.document(currCustomer.getEmail());
        return newCart;
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
    // since no sale happening thingy, only noticeable change is cart is now empty
    public void purchaseCart() {
        setCartProducts(new ArrayList<Product>());
    }

    private DocumentReference getCartDocument() throws IOException {
        ApiFuture<QuerySnapshot> future = cartsCollection
                .whereEqualTo("customerId", this.getCustomerID())
                .limit(1)
                .get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        // Throws an exception if customerDocument is null for some reason
        if (documents == null) {
            throw new IOException("Could not retrieve the cart document");
        }
        return documents.get(0).getReference();
    }

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

    // Shouldn't be used because carts are created w customers, synonymous w
    // customers
    // I'd recommend using getnextcustomerid instead, too
    public static int getNextCartId() throws IOException {
        ApiFuture<QuerySnapshot> future = cartsCollection.orderBy("customerId", Query.Direction.DESCENDING)
                .limit(1).get();
        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
        return documents.get(0).getLong("customerId").intValue() + 1;
    }

    // @Override
    // public String toString() {
    // return "Cart{" +
    // "customerID=" + this.customerID +
    // ", cartProducts=" + this.cartProducts +
    // ", documentReference=" + this.documentReference +
    // ", documentName='" + this.documentName + '\'' +
    // '}';
    // }
}
