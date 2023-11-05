public class Sale {
    private Customer customer;
    private Product product;
    private double cost;

    public Sale(Customer customer, Product product, double cost) {
        this.customer = customer;
        this.product = product;
        this.cost = calculateCost();
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

    public double calculateCost() {
        throw new UnsupportedOperationException("Unsupported Operation 'calculateCost");
    }

}
