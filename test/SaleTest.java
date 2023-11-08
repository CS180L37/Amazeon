import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SaleTest extends TestUtils {
    @Test
    public void testCalculateCost() {
        assertEquals(this.wiiToGamer, 100.00);
        assertEquals(this.wiiToNintendoFanBoy, 200.00);
        assertEquals(this.xboxToGamer, 299.99);
    }
}