package screens;

public class Sale {
    private int saleId;
    private int customerId;
    private int productId;
    private double cost;
    private int numPurchased;

    public Sale(double cost, int customerId, int numPurchased, int productId, int saleId) {
        if (customerId == 0 || productId == 0) {
            return;
        }
        this.cost = cost;
        this.customerId = customerId;
        this.numPurchased = numPurchased;
        this.productId = productId;
        this.saleId = saleId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
}
