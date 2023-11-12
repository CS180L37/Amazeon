import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CartTest extends TestUtils {
    // Try adding a product to cart
    @Test
    public void testAddToCart() {
        Cart cart = amazeon.getCartById(1);
        cart.addToCart(amazeon.getProductById(0));
        assertEquals(cart.getCartProducts(), amazeon.getProductById(0));
    }

    // Try removing a product from a cart
    @Test
    public void testRemoveFromCart() {
        Cart cart = amazeon.getCartById(0);
        cart.removeFromCart(amazeon.getProductById(0));
        assertEquals(cart.getCartProducts().size(), 0);
    }

    // Try purchasing a cart
    @Test
    public void testPurchaseCart() {
        Cart cart = amazeon.getCartById(0);
        cart.purchaseCart();
        assertEquals(cart.getCartProducts().size(), 0);
        assertEquals(amazeon.getCustomerById(cart.getCustomerID()).getProducts(), amazeon.getProductById(0));
    }

    // Try displaying
    @Test
    public void testDisplay() {
        throw new UnsupportedOperationException("Unimplemented method 'testDisplay'");
    }
}