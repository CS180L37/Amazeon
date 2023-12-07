import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.api.client.util.DateTime;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import io.grpc.netty.shaded.io.netty.util.Timer;
import models.Customer;
import models.Product;
import utils.Utils;
import utils.fields;

public class ProductTest extends TestUtils {
    public Product product0;
    public Product product1;
    public Product product2;
    public Product product3;
    public CollectionReference products;

    @BeforeEach
    @Override
    public void setUp() throws IOException {
        super.setUp();
        try {
            product0 = Product.getProductById(0);
            product1 = Product.getProductById(1);
            product2 = Product.getProductById(2);
            product3 = Product.getProductById(3);
            if (products == null) {
                products = db.collection("products");
            }
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

    // storeId for product0 should NOT be 100...

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
        assertEquals(products, Product.getProductsByIds(new ArrayList<Integer>(Arrays.asList(0, 1))));
        // assertThrows(IOException.class, () ->
        // Product.getProductsByIds(Arrays.asList()));
        assertEquals(List.of(), Product.getProductsByIds(new ArrayList<Integer>(List.of())));
    }

    @Test
    public void testSortProducts() throws IOException {
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product0);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        assertEquals(products, Product.sortProducts(fields.productId, Direction.ASCENDING));
    }

    @Test
    public void testCreateProduct() {
        try {
            Product product4 = Product.createProduct("New joycons because your old ones are drifting",
                    "Joycons", 60, 4,
                    100, 3, 2);
            assertEquals(product4, Product.getProductById(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteProduct() throws IOException {
        product0.deleteProduct();
        assertThrows(IOException.class, () -> Product.getProductById(0));
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
                Utils.retrieveData(products.whereEqualTo(fields.name,
                        "New Product Name").limit(1).get()).size());
    }

    @Test
    public void testSetQuantity() throws IOException {
        product0.setQuantity(100);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo(fields.quantity,
                        100).limit(1).get()).size());
    }

    @Test
    public void testSetDescription() throws IOException {
        product0.setDescription("New Description");
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo(fields.description,
                        "New Description").limit(1).get()).size());
    }

    @Test
    public void testSetPrice() throws IOException {
        product0.setPrice(20.05);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo(fields.price,
                        20.05).limit(1).get()).size());
    }

    @Test
    public void testSetSellerId() throws IOException {
        product0.setSellerId(100);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo(fields.sellerId,
                        100).limit(1).get()).size());
    }

    @Test
    public void testSetStoreId() throws IOException {
        product0.setStoreId(100);
        assertEquals(1,
                Utils.retrieveData(products.whereEqualTo(fields.storeId,
                        100).limit(1).get()).size());
    }
}