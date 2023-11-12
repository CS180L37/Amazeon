import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

// User parent class has no methods to test
public class CustomerTest extends TestUtils {
    @Test
    public void testExportData() {
        // this.nintendoFanBoy.exportData("");
        throw new UnsupportedOperationException("Unimplemented method 'testExportData'");
    }

    @Test
    public void testEditAccount() {
        Customer customer = amazeon.getCustomerById(0);
        customer.editAccount("newEmail@gmail.com", "newPassword");
        assertEquals(customer.getEmail(), "newEmail@gmail.com");
        assertEquals(customer.getPassword(), "newPassword");
    }

    @Test
    public void testDeleteAccount() {
        Customer customer = amazeon.getCustomerById(0);
        customer.deleteAccount();
        assertEquals(customer, null);
    }

    @Test
    public void testPurchaseProduct() {
        Customer customer = amazeon.getCustomerById(0);
        customer.purchaseProduct(amazeon.getProductById(0));
        assertEquals(customer.getProducts().size(),
                new ArrayList<Product>(Arrays.asList(amazeon.getProductById(0), amazeon.getProductById(0),
                        amazeon.getProductById(0))));
    }
}