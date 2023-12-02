import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import models.Customer;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import models.Cart;
import models.Product;

public class CartTest {
    public CartTest() throws IOException {
        Customer Customer1 = Customer.getCustomerByEmail("adityasemail@gmail.com");
        Customer Customer2 = Customer.getCustomerByEmail("xandersemail@gmail.com");
    }
    // Try adding a product to cart
    @Test
    public void testAddToCart() throws IOException {
        Cart cart = Cart.getCartById(1);
        System.out.println(Product.getProductById(0));
        cart.addToCart(Product.getProductById(0));
        assertEquals(cart.getCartProducts(), new ArrayList<Product>(Arrays.asList(Product.getProductById(0))));
    }

    // Try removing a product from a cart
    @Test
    public void testRemoveFromCart() throws IOException {
        Cart cart = Cart.getCartById(0);
        cart.removeFromCart(Product.getProductById(0));
        assertEquals(cart.getCartProducts().size(), 0);
    }

    // Try purchasing a cart
    //TODO fix this
    @Test
    public void testPurchaseCart() throws IOException {
        Cart cart = Cart.getCartById(0);
        cart.purchaseCart();
        assertEquals(cart.getCartProducts().size(), 0);
        assertEquals(Customer.getCustomerById(cart.getCustomerID()).getProducts(), Product.getProductById(0));
    }
}