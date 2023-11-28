package models;

import java.io.*;
import java.util.*;

import Amazeon;
import TODOREFACTOR.User;
import utils.Utils;

public class Seller implements UserInterface<Seller> {
    private int sellerId;
    private String name;
    private String email;
    private String password;

    // TODO: keep retrieval by id methods
    private ArrayList<Product> products;
    private ArrayList<Sale> sales;

    private Seller(int id, ArrayList<Product> products, String email, String password, ArrayList<Sale> sales) {
        super(id, products, email, password);
        this.sales = sales;
    }

    // TODO: alternative constructor
    public static Seller createSeller() {
        return null;
    }

    public void displayProducts() {
        for (int i = 0; i < getProducts().size(); i++) {
            System.out.println("Product Name: " + getProducts().get(i).getName()
                    + "\nProduct Stock: " + getProducts().get(i).getQuantity()
                    + "\nStore Name: " + Amazeon.getStoreById(getProducts().get(i).getStoreId()).getName() + "\n\n");
        }
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void viewSales() {
        String info = "";
        for (int i = 0; i < sales.size(); i++) {
            String custEmail = sales.get(i).getCustomer().getEmail();
            String custId = String.valueOf(sales.get(i).getCustomer().getId());
            double revenue = sales.get(i).calculateCost();
            Store store = Amazeon.getStoreById(sales.get(i).getProduct().getStoreId());
            info += "Customer Email: " + custEmail + "\nCustomer Id: "
                    + custId + "\nRevenue: "
                    + revenue + "Store: " + store + "\n\n";
        }
    }

    public ArrayList<Store> getStores() {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (Product product : this.getProducts()) {
            stores.add(Amazeon.getStoreById(product.getStoreId()));
        }
        return stores;
    }

    public void displayDashboard() {
        // Add a product to the sellers products list
        System.out.println(
                "How do you want to sort?\n1. Number of products purchased by a customer\n2. Products by number of sales\n");
        int choice; // default
        while (true) {
            try {
                choice = Utils.SCANNER.nextInt();
                Utils.SCANNER.nextLine();
                if (choice != 1 && choice != 2 && choice != 3) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid option number.");
            }
        }

        // add sorting code
        if (choice == 1) {
            sortCustomersByPurchases();
            sortProductsBySales();
        } else if (choice == 2) {
            sortProductsBySales();
            sortCustomersByPurchases();
        }

        // list of customers with number of items that they have purchased

        // list of products with the number of sales.

        // code for sorting by items per customer:
    }

    private void sortCustomersByPurchases() {
        Map<Customer, Integer> customerIntegerMap = new HashMap<>();
        for (Sale sale : getSales()) {
            if (customerIntegerMap.containsKey(sale.getCustomer())) {
                customerIntegerMap.put(sale.getCustomer(), customerIntegerMap.get(sale.getCustomer()) + 1);
            } else {
                customerIntegerMap.put(sale.getCustomer(), 1);
            }
        }
        // for (Map.Entry<Customer, Integer> entry : customerIntegerMap.entrySet())
        // System.out.println("Key = " + entry.getKey() +
        // ", Value = " + entry.getValue());
        // ArrayList<Map.Entry<Customer, Integer>> keyList = new
        // ArrayList<>(customerIntegerMap.entrySet());
        // keyList.sort(Comparator.comparing(Map.Entry::getValue));
        // Map<Customer, Integer> sortedCustomerIntegerMap = new LinkedHashMap<>();
        //
        // for (Map.Entry<Customer, Integer> key : keyList) {
        // sortedCustomerIntegerMap.put(key.getKey(), key.getValue());
        // }

        // another way
        Customer[] indicesCIM = new Customer[customerIntegerMap.size()];
        for (int i = 0; i < customerIntegerMap.size() - 1; i++) {
            for (int j = 0; j < customerIntegerMap.size() - 1 - i; j++) {
                if (customerIntegerMap.get(indicesCIM[j]) > customerIntegerMap.get(indicesCIM[j + 1])) {
                    Customer temp = indicesCIM[j];
                    indicesCIM[j] = indicesCIM[j + 1];
                    indicesCIM[j + 1] = temp;
                }
            }
        }
    }

    private void sortProductsBySales() {
        int numberSales = 0;
        for (int i = 0; i < getProducts().size(); i++) {
            Product product = getProducts().get(i);
            for (Sale sale : sales) {
                if (sale.getProduct().equals(product)) {
                    numberSales++;
                }
            }

            System.out.println(i + ". " + getProducts().get(i) + " - " + numberSales + " Sales");
            numberSales = 0;
        }
    }

    public void deleteProduct(Product product) {
        getProducts().remove(product);
        // updateProductsFile();
    }

    @Override
    public void importData(String filename) {
        if (!filename.substring(filename.length() - 3, filename.length()).equals("csv")) {
            System.out.println("File must be a valid csv file to import");
            return;
        }
        try {
            BufferedReader br = Utils.createReader(filename);
            ArrayList<Product> productsToAdd = new ArrayList<Product>();
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                try {
                    productsToAdd.add(Utils.convertFromProductString(line.split(",")));
                } catch (Exception e) {
                    System.out.println("Could not create those products from that csv; are you sure it's valid?");
                    return;
                }
            }
            for (Product product : productsToAdd) {
                this.getProducts().add(product);
            }
        } catch (Exception e) {
            System.out.println("Could not get the product file at that filepath!");
            return;
        }
    }

    @Override
    public void exportData(String filepath) {
        try {
            BufferedWriter bw = Utils.createWriter(filepath);
            for (Product product : this.getProducts()) {
                bw.write(Utils.convertToProductString(product));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createProduct() {
        System.out.println("Enter the name of the product: ");
        String name = Utils.SCANNER.nextLine();
        System.out.println("Enter the description of the product: ");
        String description = Utils.SCANNER.nextLine();
        System.out.println("Enter the price of the product: ");
        double price = Double.parseDouble(Utils.SCANNER.nextLine());
        System.out.println("Enter the stock (quantity) of the product: ");
        int quantity = Integer.parseInt(Utils.SCANNER.nextLine());
        System.out.println("Enter product id: ");
        int productId = Integer.parseInt(Utils.SCANNER.nextLine());
        // System.out.println("Enter your seller id: ");
        int sellerId = this.getId();
        System.out.println("Enter your store's id: ");
        int storeId = Integer.parseInt(Utils.SCANNER.nextLine());

        Product product = new Product(productId, name, quantity, description, price, sellerId, storeId);
        Amazeon.products.add(product);
        getProducts().add(product);
    }

    public void updateProduct(int prodID) {
        System.out.println("Enter new name:");
        String newName = Utils.SCANNER.nextLine();
        // TODO
        System.out.println("Enter stores?");
        int newStoreID = Utils.SCANNER.nextInt();
        Utils.SCANNER.nextLine();
        // something waiting for edstem
        System.out.println("Enter new description:");
        String newDesc = Utils.SCANNER.next();
        System.out.println("Enter new quantity:");
        int newQuan = Integer.parseInt(Utils.SCANNER.nextLine());
        System.out.println("Enter new price:");
        int newPrice = Integer.parseInt(Utils.SCANNER.nextLine());
        for (Product product : Amazeon.products) {
            if (product.getProductId() == prodID) {
                product.setName(newName);
                product.setStoreId(newStoreID);
                product.setDescription(newDesc);
                product.setQuantity(newQuan);
                product.setProductId(newPrice);
            }
        }
        // updateProductsFile();
    }

    // public void updateProductsFile() {
    // FileWriter fw;
    // BufferedWriter bw;
    // PrintWriter pw;
    // try {
    // String[] i = getEmail().split("@");
    // fw = new FileWriter(i[0] + i[1] + "ProductsFile", false);
    // bw = new BufferedWriter(fw);
    // pw = new PrintWriter(bw);
    // for (Product product : getProducts()) {
    // String storeName = Amazeon.getStoreNameFromID(product.getStoreId());
    // pw.println(product.getName() + "," + storeName + "," + product.getStoreId()
    // + product.getDescription() + "," + product.getQuantity() + "," +
    // product.getPrice());
    // }
    // pw.flush();
    // pw.close();
    // bw.close();
    // fw.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    // public static void updateSellersFile() {
    // try {
    // // public Seller(int id, ArrayList<Product> products, String email, String
    // // password, ArrayList<Sale> sales) {
    // FileWriter fw = new FileWriter(Utils.DATA_DIR + Utils.SELLER_FILE);
    // BufferedWriter bw = new BufferedWriter(fw);
    // PrintWriter pw = new PrintWriter(bw);
    // for (Seller seller : Amazeon.sellers) {
    // pw.println(seller.getId() +
    // Utils.splitIdsByPipe(Amazeon.getProductIds(seller.getProducts()).toString()).toString()
    // +
    // seller.getEmail() + seller.getPassword() +
    // Utils.splitIdsByPipe(Amazeon.getSaleIds(seller.getSales()).toString()));
    // }
    // pw.flush();
    // pw.close();
    // bw.close();
    // fw.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void editAccount(String email, String password) {
        for (Seller seller1 : Amazeon.sellers) {
            if (seller1.getEmail().equalsIgnoreCase(this.getEmail())
                    && seller1.getPassword().equalsIgnoreCase(this.getPassword())) {
                Amazeon.sellers.remove(seller1);
                Amazeon.sellers.add(this);
                // updateSellersFile();
            }
        }
        // updateSellersFile();
    }

    @Override
    public void deleteAccount() {
        for (Seller seller : Amazeon.sellers) {
            if (seller.getEmail().equalsIgnoreCase(this.getEmail())
                    && seller.getPassword().equalsIgnoreCase(this.getPassword())) {
                Amazeon.sellers.remove(seller);
                // updateSellersFile();
            }
        }
    }

    // TODO: adapt these for backend
    public static Seller getSellerById(int sellerId) {
        for (Seller seller : Amazeon.sellers) {
            if (seller.getId() == sellerId) {
                return seller;
            }
        }
        return new Seller(-1, new ArrayList<>(), "", "", new ArrayList<>());
    }

    public static ArrayList<Seller> getSellersByIds(ArrayList<Integer> sellerIds) {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        for (int sellerId : sellerIds) {
            sellers.add(getSellerById(sellerId));
        }
        return sellers;
    }

    public static int getNextSellerId() {
        // int sellerListSize = customers.size() - 1;
        // if (sellerListSize < 0) {
        // return 1;
        // }
        // return sellers.get(sellers.size() - 1).getId() + 1;
        return 0;
    }
}