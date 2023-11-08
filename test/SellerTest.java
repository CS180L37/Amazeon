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
        Product ninSwitch = new Product(0, 1, "switch", new ArrayList<Integer>(Arrays.asList(0)),
                "Nintendo switches it up!", 300.00);
        this.nintendo.createProduct(ninSwitch);
        assertEquals(this.nintendo.getProducts(), new ArrayList<Product>(Arrays.asList(this.wii, ninSwitch)));
    }

    @Test
    public void testEditProduct() {
        Product ninSwitch = new Product(0, 1, "switch", new ArrayList<Integer>(Arrays.asList(0)),
                "Nintendo switches it up!", 300.00);
        this.nintendo.editProduct(this.wii, ninSwitch);
        assertEquals(this.nintendo.getProducts(), new ArrayList<Product>(Arrays.asList(ninSwitch)));
    }

    @Test
    public void testDeleteProduct() {
        this.nintendo.deleteProduct(this.wii);
        assertEquals(this.nintendo.getProducts().size(), 0);
    }

    // Export all product data to a csv file
    @Test
    public void testExportData() {
        Product ninSwitch = new Product(0, 1, "Switch", new ArrayList<Integer>(Arrays.asList(0)),
                "Nintendo switches it up!", 300.00);
        this.nintendo.createProduct(ninSwitch);
        this.nintendo.exportData("nintendo.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("nintendo.csv")));

            try {
                String line = "";
                int productNum = 0;
                while (true) {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    if (productNum == 0) {
                        assertEquals(line, "0,2,Wii,[0],A Nintendo Game Console,100.00");
                    } else {
                        assertEquals(line, "0,1,Switch,[0],Nintendo switches it up!,300.00");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (

        FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // TODO: waiting on this test case until export data is implemented and we
    // figure out file writing
    @Test
    public void testImportData() {
        throw new UnsupportedOperationException("Unimplemented method 'testImportData'");
    }

    @Test
    public void testGetSellerById() {
        assertEquals(Seller.getSellerById(0), this.nintendo);
        assertEquals(Seller.getSellerById(1), this.microsoft);
        assertEquals(Seller.getSellerById(2), this.sony);
    }
}