
import static models.Sale.getSaleById;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import models.Product;
import models.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.cloud.firestore.CollectionReference;
import models.Sale;
import utils.Utils;
import utils.fields;

public class SaleTest extends TestUtils {

    public Sale sale0;
    public Sale sale1;
    public Sale sale2;
    public CollectionReference sales = db.collection("sales");

    @BeforeEach
    @Override
    public void setUp() throws IOException {
        super.setUp();
        try {
            sale0 = Sale.getSaleById(0);
            sale1 = Sale.getSaleById(1);
            if (sales == null) {
                sales = db.collection("sales");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteSale() throws IOException {
        sale0.deleteSale();
        assertThrows(IOException.class, () -> Seller.getSellerById(0));
    }

    @Test
    public void testGetSaleById() throws IOException {
        assertEquals(sale1, getSaleById(1));
        assertNull(Sale.getSaleById(100));
    }

    @Test
    public void testGetSalesbyIds() throws IOException {
        ArrayList<Sale> sale = new ArrayList<Sale>();
        sales.add(sale0);
        sales.add(sale1);
        assertEquals(sales, Sale.getSalesByIds((ArrayList<Integer>) Arrays.asList(0, 1)));
// assertThrows(IOException.class, () ->
// Seller.getSellersByIds(Arrays.asList()));
        assertEquals(List.of(), Sale.getSalesByIds(new ArrayList<Integer>(List.of())));
    }

    @Test
    public void testSetCustomerId() throws IOException {
        sale1.setCustomerId(50);
        assertEquals(1,
                Utils.retrieveData(sales.whereEqualTo("customerId",
                        "50").limit(1).get()).size());
    }

    @Test
    public void testSetCost() throws IOException {
        sale1.setCost(25.00);
        assertEquals(1,
                Utils.retrieveData(sales.whereEqualTo(fields.cost, 25.00).limit(1).get()).size());
    }

    @Test
    public void testSetProductId() throws IOException {
        sale1.setProductId(5);
        assertEquals(1,
                Utils.retrieveData(sales.whereEqualTo(fields.productId,
                        5).limit(1).get()).size());
    }

    @Test
    public void testSetNumPurchased() throws IOException {
        sale1.setNumPurchased(15);
        assertEquals(1,
                Utils.retrieveData(sales.whereEqualTo(fields.numPurchased,
                        15).limit(1).get()).size());
    }

    @Test
    public void testSetSaleId() throws IOException {
        sale1.setSaleId(2);
        assertEquals(sale1, getSaleById(2));
    }

    @Test
    public void testCalculateCost() throws IOException {
        assert (Objects.requireNonNull(getSaleById(0)).calculateTotal() == 100.00);
        assert (Objects.requireNonNull(getSaleById(1)).calculateTotal() == 200.00);
    }
}

