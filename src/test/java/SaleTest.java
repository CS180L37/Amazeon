/*
 * import static models.Sale.getSaleById;
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * import java.io.IOException;
 * import java.util.ArrayList;
 * import java.util.Arrays;
 * 
 * import models.Seller;
 * import org.junit.jupiter.api.Test;
 * 
 * import com.google.cloud.firestore.CollectionReference;
 * import models.Sale;
 * import utils.Utils;
 * 
 * public class SaleTest extends TestUtils {
 * 
 * public Sale sale0;
 * public Sale sale1;
 * public CollectionReference sales = db.collection("sales");
 * 
 * public SaleTest() {
 * try {
 * CollectionReference sales = db.collection("sales");
 * sale0 = getSaleById(0);
 * sale1 = getSaleById(1);
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * 
 * @Test
 * public void testDeleteSale() throws IOException {
 * sale0.deleteSale();
 * assertNull(getSaleById(0));
 * }
 * 
 * @Test
 * public void testGetSaleById() throws IOException {
 * assertEquals(sale0, getSaleById(0));
 * assertNull(Sale.getSaleById(100));
 * }
 * 
 * @Test
 * public void testGetSalesbyIds() throws IOException {
 * ArrayList<Sale> sale = new ArrayList<Sale>();
 * sales.add(sale0);
 * sales.add(sale1);
 * assertEquals(sales, Sale.getSalesByIds(Arrays.asList(0, 1)));
 * // assertThrows(IOException.class, () ->
 * // Seller.getSellersByIds(Arrays.asList()));
 * assertNull(Sale.getSalesByIds(Arrays.<Integer>asList()));
 * }
 * 
 * @Test
 * public void testSetCustomerId() throws IOException {
 * sale1.setCustomerId(50);
 * assertEquals(1,
 * Utils.retrieveData(sales.whereEqualTo("customerId",
 * "50").limit(1).get()).size());
 * }
 * 
 * @Test
 * public void testSetCost() throws IOException {
 * sale1.setCost(25.00);
 * assertEquals(1,
 * Utils.retrieveData(sales.whereEqualTo("cost", 25.00).limit(1).get()).size());
 * }
 * 
 * @Test
 * public void testSetProductId() throws IOException {
 * sale1.setProductId(5);
 * assertEquals(1,
 * Utils.retrieveData(sales.whereEqualTo("productId",
 * 5).limit(1).get()).size());
 * }
 * 
 * @Test
 * public void testSetNumPurchased() throws IOException {
 * sale1.setNumPurchased(15);
 * assertEquals(1,
 * Utils.retrieveData(sales.whereEqualTo("numPurchased",
 * 15).limit(1).get()).size());
 * }
 * 
 * @Test
 * public void testSetSaleId() throws IOException {
 * sale1.setSaleId(2);
 * assertEquals(sale1, getSaleById(2));
 * }
 * 
 * @Test
 * public void testCalculateCost() throws IOException {
 * assert (Sale.getSaleById(0).calculateTotal() == 100.00);
 * assert (Sale.getSaleById(1).calculateTotal() == 200.00);
 * }
 * }
 */