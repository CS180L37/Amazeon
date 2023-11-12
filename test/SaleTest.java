import org.junit.Test;

public class SaleTest extends TestUtils {
    @Test
    public void testCalculateCost() {
        assert (amazeon.getSaleById(0).calculateCost() == 100.00);
        assert (amazeon.getSaleById(1).calculateCost() == 200.00);
    }
}