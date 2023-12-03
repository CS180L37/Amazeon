
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.cloud.firestore.CollectionReference;

import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import utils.Utils;

// User parent class has no methods to test
public class SellerTest extends TestUtils {
    public Seller seller0;
    public Seller seller2;
    public CollectionReference sellers;

    @BeforeEach
    @Override
    public void setUp() throws IOException {
        super.setUp();
        try {
            seller0 = Seller.getSellerByEmail("jkrowling@gmail.com");
            seller2 = Seller.getSellerByEmail("nintendo@nintendo.com");
            if (sellers == null) {
                sellers = db.collection("sellers");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Private
    // @Test
    // public void testGetSellerDocument() {

    // }

    // Private
    // @Test
    // public void testGetNextSellerId() {

    // }

    @Test
    public void testGetSellerById() throws IOException {
        assertEquals(seller0, Seller.getSellerById(0));
        assertNull(Seller.getSellerById(100));
    }

    @Test
    public void testGetSellersByIds() throws IOException {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        sellers.add(seller0);
        sellers.add(seller2);
        assertEquals(sellers, Seller.getSellersByIds(Arrays.asList(0, 1)));
        // assertThrows(IOException.class, () ->
        // Seller.getSellersByIds(Arrays.asList()));
        assertEquals(Arrays.asList(), Seller.getSellersByIds(Arrays.asList()));
    }

    @Test
    public void testGetSellerByEmail() throws IOException {
        assertEquals(seller0, Seller.getSellerByEmail("jkrowling@gmail.com"));
        assertNull(Seller.getSellerByEmail("fakeseller@gmail.com"));
    }

    @Test
    public void testCreateSeller() {
        try {
            Seller seller3 = Seller.createSeller("microsoft@outlook.com", "password",
                    "Microsoft");
            assertEquals(seller3, Customer.getCustomerByEmail("microsoft@outlook.com"));
            // Ensure that trying to create an already existing seller throws an error;
            // handled in Amazeon
            // assertThrows(IOException.class, () ->
            // Seller.createSeller("nintendo@nintendo.com", "password", "Nintendo"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteSeller() throws IOException {
        seller0.deleteSeller();
        assertThrows(IOException.class, () -> Seller.getSellerById(0));
    }

    // @Test
    // public void testSetCartId() throws IOException {
    // Cart newCart = Seller0.getCart();
    // newCart.setSellerID(3);
    // Seller0.setCartId(newCart);
    // assertEquals(Seller0,
    // Utils.retrieveData(Sellers.whereEqualTo("cartId",
    // 3).limit(1).get()).get(0));
    // }

    @Test
    public void testSetSellerId() throws IOException {
        seller0.setSellerId(100);
        assertEquals(seller0, Seller.getSellerById(100));
    }

    @Test
    public void testSetName() throws IOException {
        seller0.setName("New Seller Name");
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo("name",
                        "New Seller Name").limit(1).get()).size());
    }

    @Test
    public void testSetEmail() throws IOException {
        seller0.setEmail("jkrowlingsnewemail@gmail.com");
        assertEquals(seller0,
                Seller.getSellerByEmail("jkrowlingsnewemail@gmail.com"));
        assertThrows(IOException.class, () -> seller0.setEmail("invalidEmail:)"));
    }

    @Test
    public void testSetPassword() throws IOException {
        seller0.setPassword("jkrowlingsnewpassword");
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo("password",
                        "jkrowlingsnewpassword").limit(1).get()).size());
        assertThrows(IOException.class, () -> seller0.setEmail("invalidPassword:)"));
    }

    @Test
    public void testSetProducts() throws IOException {
        seller0.setProducts(new ArrayList<Product>(Arrays.asList(Product.getProductById(3))));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo("productIds",
                        Arrays.asList(3)).limit(1).get()).size());
    }

    @Test
    public void testSetSales() throws IOException {
        seller0.setSales(new ArrayList<Sale>(Arrays.asList(Sale.getSaleById(0))));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo("saleIds",
                        Arrays.asList(3)).limit(1).get()).size());
    }

    @Test
    public void testAddProduct() throws IOException {
        seller2.addProduct(Product.getProductById(2));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo("productIds", Arrays.asList(3,
                        2)).limit(1).get()).size());
    }

    @Test
    public void testRemoveProduct() throws IOException {
        seller2.removeProduct(Product.getProductById(3));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo("productIds",
                        Arrays.asList()).limit(1).get()).size());
    }

    @Test
    public void testAddSale() throws IOException {
        seller0.addSale(Sale.getSaleById(1));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo("saleIds",
                        Arrays.asList(3)).limit(1).get()).size());
    }
}
