import java.util.ArrayList;

public class Seller extends User implements UserInterface {
    private ArrayList<Sale> sales;

    public Seller(int id, ArrayList<Product> products, ArrayList<Sale> sales) {
        super(id, products);
        this.sales = sales;
    }

    public void createProduct() {
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    public void editProduct(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'editProduct'");
    }

    public void deleteProduct(Product product) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public void exportData() {
        throw new UnsupportedOperationException("Unimplemented method 'exportData'");
    }

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
