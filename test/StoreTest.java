import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StoreTest extends TestUtils {
    @Test
    public void testGetStoreById() {
        assertEquals(Store.getStoreById(0), this.gamestop);
        assertEquals(Store.getStoreById(1), this.bestBuy);
    }
}