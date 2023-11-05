import java.util.ArrayList;

public class Seller extends User implements UserInterface {
    private ArrayList<Sale> sales;

    public Seller(int id, ArrayList<Product> products, ArrayList<Sale> sales) {
        super(id, products);
        this.sales = sales;
    }

    // Add a product to the sellers products list
    public void createProduct() {
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    // Edit a product from the sellers products list
    public void editProduct(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'editProduct'");
    }

    // Delete a product to the sellers products list
    public void deleteProduct(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    // Export products to a csv file
    @Override
    public void exportData() {
        throw new UnsupportedOperationException("Unimplemented method 'exportData'");
    }

    // Import products from a csv file
    @Override
    public void importData() {
        throw new UnsupportedOperationException("Unimplemented method 'importData'");
    }

    public static Seller getSellerById(int sellerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

}
