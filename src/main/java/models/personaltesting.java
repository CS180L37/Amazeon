package models;

import java.io.IOException;
import java.util.ArrayList;

public class personaltesting {
    public void main(String[] args) throws IOException {
        Cart cart = Cart.getCartById(0);
        Cart.createCart(0);
        ArrayList<Integer> checkProductIds = new ArrayList<>();
        System.out.println(cart.getCartProductIds());
    }
}