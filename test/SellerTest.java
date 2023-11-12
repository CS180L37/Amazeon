import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

// User parent class has no methods to test
public class SellerTest extends TestUtils {
    @Test
    public void testCreateProduct() {
        Seller seller = Amazeon.getSellerById(0);
        Product ninSwitch = new Product(1, "switch", 1,
                "Nintendo switches it up!", 300.00, 0, 0);
        seller.createProduct(ninSwitch);
        assertEquals(seller.getProducts(),
                new ArrayList<Product>(Arrays.asList(Amazeon.getProductById(0), ninSwitch)));
    }

    @Test
    public void testEditProduct() {
        Seller seller = Amazeon.getSellerById(0);
        Product ninSwitch = new Product(1, "switch", 1,
                "Nintendo switches it up!", 300.00, 0, 0);
        seller.editProduct(Amazeon.getProductById(0), ninSwitch);
        assertEquals(seller.getProducts().get(0), ninSwitch);
    }

    @Test
    public void testDeleteProduct() {
        Seller seller = Amazeon.getSellerById(0);
        seller.deleteProduct(Amazeon.getProductById(0));
        assertEquals(seller.getProducts().size(), 0);
    }

    @Test
    public void testEditAccount() {
        Seller seller = Amazeon.getSellerById(0);
        seller.editAccount("newEmail@gmail.com", "newPassword");
        assertEquals(seller.getEmail(), "newEmail@gmail.com");
        assertEquals(seller.getPassword(), "newPassword");
    }

    @Test
    public void testDeleteAccount() {
        Seller seller = Amazeon.getSellerById(0);
        seller.deleteAccount();
        assertEquals(seller, null);
    }

    // Export all product data to a csv file
    @Test
    public void testExportData() {
        // Product ninSwitch = new Product(0, 1, "Switch", new
        // ArrayList<Integer>(Arrays.asList(0)),
        // "Nintendo switches it up!", 300.00);
        // this.nintendo.createProduct(ninSwitch);
        // this.nintendo.exportData("nintendo.csv");
        // try {
        // BufferedReader br = new BufferedReader(new FileReader(new
        // File("nintendo.csv")));

        // try {
        // String line = "";
        // int productNum = 0;
        // while (true) {
        // line = br.readLine();
        // if (line == null) {
        // break;
        // }
        // if (productNum == 0) {
        // assertEquals(line, "0,2,Wii,[0],A Nintendo Game Console,100.00");
        // } else {
        // assertEquals(line, "0,1,Switch,[0],Nintendo switches it up!,300.00");
        // }
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // } catch (

        // FileNotFoundException e) {
        // e.printStackTrace();
        // }
    }

    @Test
    public void testImportData() {
        throw new UnsupportedOperationException("Unimplemented method 'testImportData'");
    }
}