package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query.Direction;

import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Cart {
//    private int customerID;
//    private ArrayList<Product> cartProducts;
//    public static CollectionReference cartsCollection;
//    private DocumentReference documentReference;
//
//    public static void main(String[] args) {
//        System.out.println(cartsCollection.toString());
//    }
//
//    Cart(int customerID, ArrayList<Product> cartProducts) throws IOException {
//        this.customerID = customerID;
//        this.cartProducts = (cartProducts != null) ? cartProducts : new ArrayList<Product>();
//        this.documentReference = getCartDocument();
//    }
//
//    private Cart(QueryDocumentSnapshot document) throws IOException {
//        this.customerID = document.getLong("customerId").intValue();
//        ArrayList<Integer> productIds = Utils.firestoreDocToIDArray(document.getData(), "productIds");
//        this.cartProducts = Product.getProductsByIds((productIds != null) ? productIds : new ArrayList<Integer>());
//        this.documentReference = getCartDocument();
//    }
//
//    public static Cart createCart(int customerId) throws IOException {
//        Cart newCart = new Cart(customerId, new ArrayList<Product>());
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("customerId", customerId);
//        data.put("productIds", Arrays.asList());
//        // cartsCollection.document(currCustomer.getEmail()).set(data);
//        // newCart.documentReference =
//        // cartsCollection.document(currCustomer.getEmail());
//        return newCart;
//    }
//
//    public void setCustomerID(int customerID) {
//        this.customerID = customerID;
//        updateRemoteCart("customerId", getCustomerID());
//    }
//
//    private void updateRemoteCart(String remoteFieldName, Object value) {
//        HashMap<String, Object> data2 = new HashMap<String, Object>();
//        data2.put(remoteFieldName, value);
//        try {
//            this.documentReference.update(data2).get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setCartProducts(ArrayList<Product> cartProducts) {
//        this.cartProducts = cartProducts;
//        try {
//            updateRemoteCart("productIds", getCartProductIds());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public int getCustomerID() {
//        return this.customerID;
//    }
//
//    public ArrayList<Product> getCartProducts() {
//        return this.cartProducts;
//    }
//
//    // Adds the product to cartProducts
//    public void addToCart(Product product) {
//        cartProducts.add(product);
//        try {
//            updateRemoteCart("productIds", getCartProductIds());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Removes the product from cartProducts
//    public void removeFromCart(Product product) throws IOException {
//        cartProducts.remove(product);
//        updateRemoteCart("productIds", getCartProductIds());
//    }
//
//    public ArrayList<Integer> getCartProductIds() throws IOException {
//        return (ArrayList<Integer>) cartProducts.stream()
//                .map(Product::getProductId)
//                .collect(Collectors.toList());
//    }
//
//    // Purchases all the products in the cart for the specific customer
//    // since no sale happening thingy, only noticeable change is cart is now empty
//    public void purchaseCart() {
//        setCartProducts(new ArrayList<Product>());
//    }
//
//    private DocumentReference getCartDocument() throws IOException {
//        ApiFuture<QuerySnapshot> future = cartsCollection
//                .whereEqualTo("customerId", this.getCustomerID())
//                .limit(1)
//                .get();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        // Throws an exception if customerDocument is null for some reason
//        if (documents == null) {
//            throw new IOException("Could not retrieve the cart document");
//        }
//        return documents.get(0).getReference();
//    }
//
//    public static Cart getCartById(int givenCustomerId) throws IOException {
//        ApiFuture<QuerySnapshot> future = cartsCollection
//                .where(Filter.equalTo("customerId", givenCustomerId)).limit(1).get();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        return (documents == null) ? null : new Cart(documents.get(0));
//    }
//
//    public static ArrayList<Cart> getCartsByIds(ArrayList<Integer> cartIds) throws IOException {
//        ArrayList<Cart> cartList = new ArrayList<Cart>();
//        for (int cartID : cartIds) {
//            Cart cart = getCartById(cartID);
//            if (cart != null) {
//                cartList.add(cart);
//            }
//        }
//        return cartList;
//    }
//
//    public static ArrayList<Cart> sortCarts(String field, Direction direction) throws IOException {
//        ApiFuture<QuerySnapshot> future = cartsCollection.orderBy(field, direction).get();
//        ArrayList<Cart> carts = new ArrayList<Cart>();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        if (documents == null) {
//            return null;
//        }
//        for (QueryDocumentSnapshot doc : documents) {
//            carts.add(new Cart(doc));
//        }
//        return carts;
//    }
//
//    // Shouldn't be used because carts are created w customers, synonymous w
//    // customers
//    // I'd recommend using getnextcustomerid instead, too
//    public static int getNextCartId() throws IOException {
//        ApiFuture<QuerySnapshot> future = cartsCollection.orderBy("customerId", Query.Direction.DESCENDING)
//                .limit(1).get();
//        List<QueryDocumentSnapshot> documents = Utils.retrieveData(future);
//        return documents.get(0).getLong("customerId").intValue() + 1;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("""
//                {
//                    customerId: %d
//                    cartProducts: %s
//                }""", this.getCustomerID(), this.getCartProducts().toString());
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof Cart) {
//            Cart cart = (Cart) obj;
//            if (cart.getCustomerID() == this.getCustomerID() && cart.getCartProducts().equals(this.getCartProducts())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
