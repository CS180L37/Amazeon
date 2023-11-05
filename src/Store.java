package src;

public class Store {
    private String name;
    private int id;
    private Product[] products;
    private Customer[] customers;

    public Store(String name, int id, Product[] products, Customer[] customers) {
        this.name = name;
        this.products = products;
        this.id = id;
        this.customers = customers;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
    public Product[] getProducts() {
        return this.products;
    }

    public Customer[] getCustomers() {
        return this.customers;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setProducts(Product[] products){
        this.products = products;
    }

    public void setCustomers(Customer[] customers){
        this.customers = customers;
    }

}
