import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CartTest extends TestUtils {
    // Try adding a product to cart
    @Test
    public void testAddToCart() {

    }

    // Try removing a product from a cart
    @Test
    public void testRemoveFromCart() {
        this.gamer.getCart().removeFromCart(this.playstation);
        assertEquals(this.gamer.getCart().getCartProducts().size(),
                0);
        assertEquals(this.nintendoFanBoy.getCart().removeFromCart(this.wii), false);
    }

    // Try purchasing a cart
    @Test
    public void testPurchaseCart() {
        this.gamer.getCart().addToCart(this.xbox);
        this.gamer.getCart().purchaseCart(); // Purchase the cart
        // Assert that the cart is empty and products have been purchased
        assertEquals(this.gamer.getCart().getCartProducts().size(), 0);
    }

    // Try displaying
    @Test
    public void testDisplay() {
        throw new UnsupportedOperationException("Unimplemented method 'testDisplay'");
    }
}