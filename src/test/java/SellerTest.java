<<<<<<< HEAD
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.google.cloud.firestore.CollectionReference;
//import com.google.cloud.firestore.Query.Direction;
//
//import models.Customer;
//import models.Product;
//import models.Sale;
//import models.Seller;
//import utils.Utils;
//
//// User parent class has no methods to test
//public class SellerTest extends TestUtils {
//    public Seller seller0;
//    public Seller seller1;
//    public Seller seller2;
//    public Seller seller3;
//    public CollectionReference sellers;
//
//    @BeforeEach
//    @Override
//    public void setUp() throws IOException {
//        super.setUp();
//        try {
//            seller0 = Seller.getSellerById(0);
//            seller1 = Seller.getSellerByEmail("jkrowling@gmail.com");
//            seller2 = Seller.getSellerById(2);
//            seller3 = Seller.getSellerByEmail("nintendo@nintendo.com");
//            if (sellers == null) {
//                sellers = db.collection("sellers");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Private
//    // @Test
//    // public void testGetSellerDocument() {
//
//    // }
//
//    // Private
//    // @Test
//    // public void testGetNextSellerId() {
//
//    // }
//
//    @Test
//    public void testGetSellerById() throws IOException {
//        assertEquals(seller1, Seller.getSellerById(1));
//        assertNull(Seller.getSellerById(100));
//    }
//
//    @Test
//    public void testGetSellersByIds() throws IOException {
//        ArrayList<Seller> sellers = new ArrayList<Seller>();
//        sellers.add(seller1);
//        sellers.add(seller3);
//        assertEquals(sellers, Seller.getSellersByIds(new ArrayList<Integer>(Arrays.asList(1, 3))));
//        // assertThrows(IOException.class, () ->
//        // Seller.getSellersByIds(Arrays.asList()));
//        assertEquals(Arrays.asList(), Seller.getSellersByIds(new ArrayList<Integer>(Arrays.asList())));
//    }
//
//    @Test
//    public void testGetSellerByEmail() throws IOException {
//        assertEquals(seller1, Seller.getSellerByEmail("jkrowling@gmail.com"));
//        assertNull(Seller.getSellerByEmail("fakeseller@gmail.com"));
//    }
//
//    @Test
//    public void testCreateSeller() {
//        try {
//            Seller seller3 = Seller.createSeller("microsoft@outlook.com", "password",
//                    "Microsoft");
//            assertEquals(seller3, Customer.getCustomerByEmail("microsoft@outlook.com"));
//            // Ensure that trying to create an already existing seller throws an error;
//            // handled in Amazeon
//            // assertThrows(IOException.class, () ->
//            // Seller.createSeller("nintendo@nintendo.com", "password", "Nintendo"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testDeleteSeller() throws IOException {
//        seller1.deleteSeller();
//        assertThrows(IOException.class, () -> Seller.getSellerById(1));
//    }
//
//    @Test
//    public void testSortSellers() throws IOException {
//        ArrayList<Seller> sellers = new ArrayList<Seller>();
//        sellers.add(seller0);
//        sellers.add(seller1);
//        sellers.add(seller2);
//        sellers.add(seller3);
//        assertEquals(sellers, Seller.sortSellers("sellerId", Direction.ASCENDING));
//    }
//
//    // @Test
//    // public void testSetCartId() throws IOException {
//    // Cart newCart = Seller0.getCart();
//    // newCart.setSellerID(3);
//    // Seller0.setCartId(newCart);
//    // assertEquals(Seller0,
//    // Utils.retrieveData(Sellers.whereEqualTo("cartId",
//    // 3).limit(1).get()).get(0));
//    // }
//
//    @Test
//    public void testSetSellerId() throws IOException {
//        seller1.setSellerId(100);
//        assertEquals(seller1, Seller.getSellerById(100));
//    }
//
//    @Test
//    public void testSetName() throws IOException {
//        seller1.setName("New Seller Name");
//        assertEquals(1,
//                Utils.retrieveData(sellers.whereEqualTo("name",
//                        "New Seller Name").limit(1).get()).size());
//    }
//
//    @Test
//    public void testSetEmail() throws IOException {
//        seller1.setEmail("jkrowlingsnewemail@gmail.com");
//        assertEquals(seller1,
//                Seller.getSellerByEmail("jkrowlingsnewemail@gmail.com"));
//        assertThrows(IOException.class, () -> seller1.setEmail("invalidEmail:)"));
//    }
//
//    @Test
//    public void testSetPassword() throws IOException {
//        seller1.setPassword("jkrowlingsnewpassword");
//        assertEquals(1,
//                Utils.retrieveData(sellers.whereEqualTo("password",
//                        "jkrowlingsnewpassword").limit(1).get()).size());
//        assertThrows(IOException.class, () -> seller1.setEmail("invalidPassword:)"));
//    }
//
//    @Test
//    public void testSetProducts() throws IOException {
//        seller1.setProducts(new ArrayList<Product>(Arrays.asList(Product.getProductById(3))));
//        assertEquals(1,
//                Utils.retrieveData(sellers.whereEqualTo("productIds",
//                        Arrays.asList(3)).limit(1).get()).size());
//    }
//
//    @Test
//    public void testSetSales() throws IOException {
//        seller1.setSales(new ArrayList<Sale>(Arrays.asList(Sale.getSaleById(1))));
//        assertEquals(1,
//                Utils.retrieveData(sellers.whereEqualTo("saleIds",
//                        Arrays.asList(1)).limit(1).get()).size());
//    }
//
//    @Test
//    public void testAddProduct() throws IOException {
//        seller3.addProduct(Product.getProductById(2));
//        assertEquals(1,
//                Utils.retrieveData(sellers.whereEqualTo("productIds", Arrays.asList(3,
//                        2)).limit(1).get()).size());
//    }
//
//    @Test
//    public void testRemoveProduct() throws IOException {
//        seller3.removeProduct(Product.getProductById(3));
//        assertEquals(1,
//                Utils.retrieveData(sellers.whereEqualTo("productIds",
//                        Arrays.asList()).limit(1).get()).size());
//    }
//
//    @Test
//    public void testAddSale() throws IOException {
//        seller1.addSale(Sale.getSaleById(1));
//        assertEquals(1,
//                Utils.retrieveData(sellers.whereEqualTo("saleIds",
//                        Arrays.asList(1)).limit(1).get()).size());
//    }
//}
=======
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query.Direction;

import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import utils.Utils;
import utils.fields;

// User parent class has no methods to test
public class SellerTest extends TestUtils {
    public Seller seller0;
    public Seller seller1;
    public Seller seller2;
    public Seller seller3;
    public CollectionReference sellers;

    @BeforeEach
    @Override
    public void setUp() throws IOException {
        super.setUp();
        try {
            seller0 = Seller.getSellerById(0);
            seller1 = Seller.getSellerByEmail("jkrowling@gmail.com");
            seller2 = Seller.getSellerById(2);
            seller3 = Seller.getSellerByEmail("nintendo@nintendo.com");
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
        assertEquals(seller1, Seller.getSellerById(1));
        assertNull(Seller.getSellerById(100));
    }

    @Test
    public void testGetSellersByIds() throws IOException {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        sellers.add(seller1);
        sellers.add(seller3);
        assertEquals(sellers, Seller.getSellersByIds(new ArrayList<Integer>(Arrays.asList(1, 3))));
        // assertThrows(IOException.class, () ->
        // Seller.getSellersByIds(Arrays.asList()));
        assertEquals(Arrays.asList(), Seller.getSellersByIds(new ArrayList<Integer>(Arrays.asList())));
    }

    @Test
    public void testGetSellerByEmail() throws IOException {
        assertEquals(seller1, Seller.getSellerByEmail("jkrowling@gmail.com"));
        assertNull(Seller.getSellerByEmail("fakeseller@gmail.com"));
    }

    @Test
    public void testCreateSeller() {
        try {
            Seller seller3 = Seller.createSeller("microsoft@outlook.com", fields.password,
                    "Microsoft");
            assertEquals(seller3, Customer.getCustomerByEmail("microsoft@outlook.com"));
            // Ensure that trying to create an already existing seller throws an error;
            // handled in Amazeon
            // assertThrows(IOException.class, () ->
            // Seller.createSeller("nintendo@nintendo.com", fields.password, "Nintendo"));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testDeleteSeller() throws IOException {
        seller1.setDeleted(true);
        assertNull(Seller.getNonDeletedSellerById(1));
    }

    @Test
    public void testSortSellers() throws IOException {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        sellers.add(seller0);
        sellers.add(seller1);
        sellers.add(seller2);
        sellers.add(seller3);
        assertEquals(sellers, Seller.sortSellers(fields.sellerId, Direction.ASCENDING));
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
        seller1.setSellerId(100);
        assertEquals(seller1, Seller.getSellerById(100));
    }

    @Test
    public void testSetName() throws IOException {
        seller1.setName("New Seller Name");
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo(fields.name,
                        "New Seller Name").limit(1).get()).size());
    }

    @Test
    public void testSetEmail() throws IOException {
        seller1.setEmail("jkrowlingsnewemail@gmail.com");
        assertEquals(seller1,
                Seller.getSellerByEmail("jkrowlingsnewemail@gmail.com"));
        assertThrows(IOException.class, () -> seller1.setEmail("invalidEmail:)"));
    }

    @Test
    public void testSetPassword() throws IOException {
        seller1.setPassword("jkrowlingsnewpassword");
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo(fields.password,
                        "jkrowlingsnewpassword").limit(1).get()).size());
        assertThrows(IOException.class, () -> seller1.setEmail("invalidPassword:)"));
    }

    @Test
    public void testSetProducts() throws IOException {
        seller1.setProducts(new ArrayList<Product>(Arrays.asList(Product.getProductById(3))));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo(fields.productIds,
                        Arrays.asList(3)).limit(1).get()).size());
    }

    @Test
    public void testSetSales() throws IOException {
        seller1.setSales(new ArrayList<Sale>(Arrays.asList(Sale.getSaleById(1))));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo(fields.saleIds,
                        Arrays.asList(1)).limit(1).get()).size());
    }

    @Test
    public void testAddProduct() throws IOException {
        seller3.addProduct(Product.getProductById(2));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo(fields.productIds, Arrays.asList(3,
                        2)).limit(1).get()).size());
    }

    @Test
    public void testRemoveProduct() throws IOException {
        seller3.removeProduct(Product.getProductById(3));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo(fields.productIds,
                        Arrays.asList()).limit(1).get()).size());
    }

    @Test
    public void testAddSale() throws IOException {
        seller1.addSale(Sale.getSaleById(1));
        assertEquals(1,
                Utils.retrieveData(sellers.whereEqualTo(fields.saleIds,
                        Arrays.asList(1)).limit(1).get()).size());
    }
}
>>>>>>> 99c0b56421d1cc589ec4ecc799e336a9f73e92e9
