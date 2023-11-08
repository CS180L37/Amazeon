import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MarketTest extends TestUtils {
    // Test that a market can set the current user by id
    @Test
    public void testSetById() {
        this.gamerMarket.setUserById(0);
        assertEquals(this.gamerMarket.getUser(), this.nintendoFanBoy);
        this.nintendoFanBoyMarket.setUserById(1);
        assertEquals(this.nintendoFanBoyMarket.getUser(), this.gamer);
    }

    // TODO: Test that a market can read data from a file
    // Implementation has yet to be decided on
    @Test
    public void testReadData() {
        throw new UnsupportedOperationException("Unimplemented method 'testReadData'");
    }

    // TODO: Test that a market can write data to a file
    // Implementation has yet to be decided on
    @Test
    public void testWriteData() {
        throw new UnsupportedOperationException("Unimplemented method 'testWriteData'");
    }
}