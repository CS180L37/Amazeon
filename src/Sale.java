public class Sale {
    private Customer customer;
    private Product product;
    private int amountPurchased;

    public Sale(Customer customer, Product product, int amountPurchased){
        this.customer = customer;
        this.product = product;
        this.amountPurchased = amountPurchased;
    }

    public Customer getCustomer(Customer customer){
        return this.customer;
    }

    public Product getProduct(){
        return this.product;
    }

    public int getAmountPurchased(){
        return this.amountPurchased;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public void setAmountPurchased(int amountPurchased){
        this.amountPurchased = amountPurchased;
    }

    public double getRevenue(){
        return this.customer.getPrice();
    }
}
