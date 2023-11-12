import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CartTest extends TestUtils {
    // Try adding a product to cart
    @Test
    public void testAddToCart() {
        Cart cart = Amazeon.getCartById(1);
        cart.addToCart(Amazeon.getProductById(0));
        assertEquals(cart.getCartProducts(), Amazeon.getProductById(0));
    }

    // Try removing a product from a cart
    @Test
    public void testRemoveFromCart() {
        Cart cart = Amazeon.getCartById(0);
        cart.removeFromCart(Amazeon.getProductById(0));
        assertEquals(cart.getCartProducts().size(), 0);
    }

    // Try purchasing a cart
    @Test
    public void testPurchaseCart() {
        Cart cart = Amazeon.getCartById(0);
        cart.purchaseCart();
        assertEquals(cart.getCartProducts().size(), 0);
        assertEquals(Amazeon.getCustomerById(cart.getCustomerID()).getProducts(), Amazeon.getProductById(0));
    }

    // Try displaying
    @Test
    public void testDisplay() {
        throw new UnsupportedOperationException("Unimplemented method 'testDisplay'");
    }
}