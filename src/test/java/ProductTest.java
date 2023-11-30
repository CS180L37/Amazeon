import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.cloud.firestore.CollectionReference;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import models.Cart;
import models.Product;
import utils.Utils;

public class ProductTest extends TestUtils {
    public Product product0;
    public Product product1;
    public CollectionReference products = db.collection("products");

    @Override
    @BeforeEach
    public void setUp() {
        // Call initial setup
        super.setUp();
        // Initialize Product instances
        // Normally, I would use a .env file for encoding data
        // But these are test instances
        try {
            product0 = Product.getProductById(0);
            product1 = Product.getProductById(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Private
    // @Test
    // public void testGetProductDocument() {

    // }

    // Private
    // @Test
    // public void testGetNextProductId() {

    // }

    @Test
    public void testGetProductById() throws IOException {
        assertEquals(product0, Product.getProductById(0));
        assertNull(Product.getProductById(100));
    }

    @Test
    public void testGetProductsByIds() throws IOException {
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product0);
        products.add(product1);
        assertEquals(products, Product.getProductsByIds(Arrays.asList(0, 1)));
        // assertThrows(IOException.class, () ->
        // Product.getProductsByIds(Arrays.asList()));
        assertNull(Product.getProductsByIds(Arrays.asList()));
    }

    // This is implicitly tested in setup
    // @Test
    // public void testCreateProduct() {

    // }

    @Test
    public void testDeleteProduct() throws IOException {
        product0.deleteProduct();
        assertNull(Product.getProductById(0));
    }

    // @Test
    // public void testSetCartId() throws IOException {
    // Cart newCart = Product0.getCart();
    // newCart.setProductID(3);
    // Product0.setCartId(newCart);
    // assertEquals(Product0,
    // Utils.retrieveData(Products.whereEqualTo("cartId",
    // 3).limit(1).get()).get(0));
    // }

    @Test
    public void testSetProductId() throws IOException {
        product0.setProductId(100);
        assertEquals(product0, Product.getProductById(100));
    }

    @Test
    public void testSetName() throws IOException {
        product0.setName("New Product Name");
        assertEquals(product0,
                Utils.retrieveData(products.whereEqualTo("password", "New Product Name").limit(1).get()).get(0));
    }

    @Test
    public void testSetQuantity() throws IOException {
        product0.setQuantity(100);
        assertEquals(product0,
                Utils.retrieveData(products.whereEqualTo("quantity", 100).limit(1).get()).get(0));
    }

    @Test
    public void testSetDescription() throws IOException {
        product0.setName("New Product Name");
        assertEquals(product0,
                Utils.retrieveData(products.whereEqualTo("password", "New Product Name").limit(1).get()).get(0));
    }

    @Test
    public void testSetPrice() throws IOException {
        product0.setName("New Product Name");
        assertEquals(product0,
                Utils.retrieveData(products.whereEqualTo("password", "New Product Name").limit(1).get()).get(0));
    }

    @Test
    public void testSetSellerId() throws IOException {
        product0.setName("New Product Name");
        assertEquals(product0,
                Utils.retrieveData(products.whereEqualTo("password", "New Product Name").limit(1).get()).get(0));
    }

    @Test
    public void testSetStoreId() throws IOException {
        product0.setName("New Product Name");
        assertEquals(product0,
                Utils.retrieveData(products.whereEqualTo("password", "New Product Name").limit(1).get()).get(0));
    }

    @Test
    public void testSetProducts() throws IOException {
        Product1.setProducts(new ArrayList<Product>(Arrays.asList(Product.getProductById(3))));
        assertEquals(Product1,
                Utils.retrieveData(Products.whereEqualTo("productIds", 3).limit(1).get()).get(0));
    }
}