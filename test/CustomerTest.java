import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

// User parent class has no methods to test
public class CustomerTest extends TestUtils {
    // Check that the proper csv file is exported
    // TODO: need to discuss implementation for this as well

    @Test
    public void testExportData() {
        // this.nintendoFanBoy.exportData("");
        throw new UnsupportedOperationException("Unimplemented method 'testExportData'");
    }

    @Test
    public void testPurchaseProduct() {
        this.gamer.purchaseProduct(this.playstation);
        assertEquals(this.gamer.getProducts(),
                new ArrayList<Product>(Arrays.asList(this.wii, this.xbox, this.playstation)));
        this.nintendoFanBoy.purchaseProduct(this.wii);
        assertEquals(this.nintendoFanBoy.getProducts(),
                new ArrayList<Product>(Arrays.asList(this.wii, this.wii, this.wii)));
    }

    @Test
    public void testGetCustomerById() {
        assertEquals(Customer.getCustomerById(0), this.nintendoFanBoy);
        assertEquals(Customer.getCustomerById(1), this.gamer);
    }
}