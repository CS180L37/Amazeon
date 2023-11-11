import javax.management.Notification;
import java.io.*;
import java.util.ArrayList;

public class Sale {
    private int saleId;
    private Customer customer;
    private Product product;
    private double cost;
    private int numPurchased;

    public Sale(int saleId, Customer customer, Product product, int numPurchased) {
        this.saleId = saleId;
        this.customer = customer;
        this.product = product;
        this.numPurchased = numPurchased;
        this.cost = calculateCost();
        System.out.printf("%s purchased %s at a total cost of %2f.\n", customer.getId(), product.getName(), cost);
        File salesFile = new File("sales.txt");
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw;
        try {
            fw = new FileWriter(salesFile);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(customer.getId() + "," + product.getName() + "," + numPurchased + "," + cost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getNumPurchased() {
        return numPurchased;
    }

    public void setNumPurchased(int numPurchased) {
        this.numPurchased = numPurchased;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    // Calculate the total cost of a sale
    public double calculateCost() {
        return getNumPurchased() * getProduct().getPrice();
    }

    // Contains a list of customers as the parameter
    public static ArrayList<Sale> readSales(ArrayList<Customer> customers) {
        throw new UnsupportedOperationException("Unimplemented method 'readSales'");
    }

    public static void writeSales(ArrayList<Sale> sales) {
        throw new UnsupportedOperationException("Unimplemented method 'readSales'");
    }
}
