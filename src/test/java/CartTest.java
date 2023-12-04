// import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.cloud.firestore.CollectionReference;
import models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Cart;
import models.Cart;
import models.Product;

public class CartTest extends TestUtils {
    public Cart cart0;
    public Cart cart1;
    public CollectionReference carts;
    public CartTest() {
    }
    @BeforeEach
    @Override
    public void setUp() throws IOException {
        super.setUp();
        try {
            Customer customer0 = Customer.getCustomerByEmail("adityasemail@gmail.com");
            Customer customer1 = Customer.getCustomerByEmail("shloksemail@gmail.com");
            cart0 = Cart.getCartById(customer0.getCustomerId());
            cart1 = Cart.getCartById(customer1.getCustomerId());
            if (carts == null) {
                carts = db.collection("Carts");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Try adding a product to cart

    @Test
    public void testAddToCart() throws IOException {
        Cart cart = Cart.getCartById(1);
        cart.purchaseCart();
        Product newProd = Product.createProduct("drink", "Prime", 799, 1, 2, 3, 4);
        cart.addToCart(newProd);
        assertEquals(cart.getCartProducts(), new ArrayList<Product>(Arrays.asList(newProd)));
    }

    // Try removing a product from a cart
    @Test
    public void testRemoveFromCart() throws IOException {
        Cart cart = Cart.getCartById(0);
        cart.removeFromCart(Product.getProductById(0));
        assertEquals(cart.getCartProducts().size(), 0);
    }

    // Try purchasing a cart
    @Test
    public void testPurchaseCart() throws IOException {
        Cart cart = Cart.getCartById(0);
        cart.purchaseCart();
        assertEquals(cart.getCartProducts().size(), 0);
        assertEquals(cart.getCartProducts(), new ArrayList<Product>());
    }

    @Test
    public void testSetCartProducts() throws IOException {
        Cart cart = Cart.getCartById(0);
        ArrayList<Product> newProducts = new ArrayList<>();
        newProducts.add(Product.createProduct("drink", "Prime", 799, 1, 2, 3, 4));
        cart.setCartProducts(newProducts);
        assertEquals(newProducts, cart.getCartProducts());
    }

    @Test
    public void testGetCartProductIds() {
        Cart cart = Cart.getCartById(0);
        ArrayList<Integer> checkProductIds = new ArrayList<>();
        System.out.println(cart.getCartProductIds());
    }
}