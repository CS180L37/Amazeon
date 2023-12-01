import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.google.cloud.firestore.CollectionReference;

import models.Product;
import utils.Utils;

public class ProductTest extends TestUtils {
    public Product product0;
    public Product product1;
    public CollectionReference products = db.collection("products");

    public ProductTest() {
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
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo("name", "New Product Name").limit(1).get()).size());
    }

    @Test
    public void testSetQuantity() throws IOException {
        product0.setQuantity(100);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo("quantity", 100).limit(1).get()).size());
    }

    @Test
    public void testSetDescription() throws IOException {
        product0.setDescription("New Description");
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo("description", "New Description").limit(1).get()).size());
    }

    @Test
    public void testSetPrice() throws IOException {
        product0.setPrice(20.05);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo("price", 20.05).limit(1).get()).size());
    }

    @Test
    public void testSetSellerId() throws IOException {
        product0.setSellerId(100);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo("sellerId", 100).limit(1).get()).size());
    }

    @Test
    public void testSetStoreId() throws IOException {
        product0.setStoreId(100);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo("storeId", 100).limit(1).get()).size());
    }
}