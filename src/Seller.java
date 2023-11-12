import java.util.*;
import java.io.*;

public class Seller extends User implements UserInterface<Seller> {
    private String name;
    private ArrayList<Sale> sales;
    // Already has a list of products and an id
    // Revenue can be calculated from the list of products

    public Seller(String email, String password) {
        super(Amazeon.getNextSellerId(), new ArrayList<Product>(), email, password);
        this.sales = new ArrayList<Sale>();
    }

    public Seller(int id, String email, String password, ArrayList<Product> products, ArrayList<Sale> sales) {
        super(id, products, email, password);
        this.sales = sales;
    }

    public void displayProducts() {
        // for (int i = 0; i < getProductsSold().size(); i++) {
        // System.out.printf("%d. %s\n", i, getProductsSold().get(i));
        // }
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public ArrayList<Store> getStores() {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (Product product : this.getProducts()) {
            stores.add(Amazeon.getStoreById(product.getStoreId()));
        }
        return stores;
    }

    public void displayDashboard(Scanner scan) {
        // Add a product to the sellers products list
        System.out.println("How do you want to sort?\n1. Customer name\n2. Product name\n3.");
        int choice = 3; // default
        while (true) {
            try {
                choice = scan.nextInt();
                if (choice != 1 && choice != 2 && choice != 3) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid option number.");
            }
        }

        // add sorting code

        // list of customers with number of items that they have purchased
        Map<Customer, Integer> customerIntegerMap = new HashMap<>();
        for (Sale sale : getSales()) {
            if (customerIntegerMap.containsKey(sale.getCustomer())) {
                customerIntegerMap.put(sale.getCustomer(), customerIntegerMap.get(sale.getCustomer()) + 1);
            } else {
                customerIntegerMap.put(sale.getCustomer(), 1);
            }
        }
        for (Map.Entry<Customer, Integer> entry : customerIntegerMap.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

        // list of products with the number of sales.
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

        // code for sorting by items per customer:
        ArrayList<Map.Entry<Customer, Integer>> keyList = new ArrayList<>(customerIntegerMap.entrySet());
        keyList.sort(Comparator.comparing(Map.Entry::getValue));
        Map<Customer, Integer> sortedCustomerIntegerMap = new LinkedHashMap<>();

        for (Map.Entry<Customer, Integer> key : keyList) {
            sortedCustomerIntegerMap.put(key.getKey(), key.getValue());
        }

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

    public void deleteProduct(Product product) {
        getProducts().remove(product);
    }

    public void createProduct(String filename) throws IOException {
        // int lineNumber = 0;
        // try {
        // BufferedReader br = new BufferedReader(new FileReader("filename.csv"));
        // while (br.readLine() != null) {
        // lineNumber++;
        // }
        // br.close();
        // } catch (IOException e) {
        // throw new RuntimeException(e);
        // ArrayList<Integer> intArray = new ArrayList<Integer>();
        // try {
        // BufferedReader br = new BufferedReader(new FileReader("filename.csv"));
        // String line = br.readLine();
        // ArrayList<String[][]> data = new ArrayList<String[][]>();
        // while (line != null) {
        // String[][] lines = new String[0][lineNumber];
        // lines = new String[][] { line.split(",") };
        // data.add(lines);
        // br.readLine();
        // }
        // String[] strArray = new String[data.size()];
        // for (int i = 0; i < data.size(); i++) {
        // strArray = data.get(i)[3];
        // }
        // for (int j = 0; j < strArray.length; j++) {
        // intArray.set(j, Integer.parseInt(strArray[j]));
        // }
        // for (int k = 0; k < data.size(); k++) {
        // Product product = new Product(Integer.parseInt(data.get(k)[0][0]),
        // Integer.parseInt(data.get(k)[0][1]), data.get(k)[0][2], intArray,
        // data.get(k)[0][4],
        // Double.parseDouble(data.get()[0][5]));
        // }
        // } catch (IOException r) {
        // r.printStackTrace();
        // }
        // }
    }

    public String getStoreNameFromID(int storeID) {
        for (Store store : Market.getStores()) {
            if (store.getId() == storeID) {
                return store.getName();
            }
        }
        return String.valueOf(Utils.NO);
    }

    public int getStoreIDFromName(String storeName) {
        for (Store store : Market.getStores()) {
            if (store.getName().equalsIgnoreCase(storeName)) {
                return store.getId();
            }
        }
        return Utils.NO;
    }

    public void exportProducts() {
        File outFile = new File("exported products.csv");
        try {
            FileWriter fw = new FileWriter(outFile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (Product product : getProducts()) {
                int storeID = product.getStoreId();
                String storeName = getStoreNameFromID(storeID);
                if (storeName.equals(String.valueOf(Utils.NO))) {
                    System.out.println("store not found");
                    continue; // this shouldnt happen generally
                }
                pw.println(product.getName() + "," + storeName + ","
                        + product.getDescription() + "," + product.getQuantity() + "," + product.getPrice());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateProducts() {
        // FileWriter fw;
        // BufferedWriter bw;
        // PrintWriter pw;
        // try {
        // String[] i = getId().split("@");
        // fw = new FileWriter(i[0] + i[1] + "ProductsFile", false);
        // bw = new BufferedWriter(fw);
        // pw = new PrintWriter(bw);
        // for (Product product : getProducts()) {
        // String storeName = getStoreNameFromID(product.getStoreId());
        // pw.println(product.getName() + "," + storeName + "," + product.getStoreId()
        // + product.getDescription() + "," + product.getQuantity() + "," +
        // product.getPrice());
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    @Override
    public void exportData(String filepath) {

    }

    // Import products from a csv file
    public void importData(String filepath) {
        throw new UnsupportedOperationException("Unimplemented method 'importData'");
    }

    public static Seller getSellerById(int sellerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public static int getNextSellerId() {
        throw new UnsupportedOperationException("Unimplemented method 'getNextSellerId'");
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public void editAccount(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'editAccount'");
    }

    @Override
    public void deleteAccount() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    // Contains lists of all products and sales as parameters
    public static ArrayList<Seller> readSellers() {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        try {
            BufferedReader br = Utils.createReader(Utils.DATA_DIR + Utils.CART_FILE);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");
                sellers.add(new Seller(
                        Integer.parseInt(data[0]), data[1], data[2],
                        (!data[3].equals(Utils.NA)) ? Amazeon.getProductByIds(Utils.splitIdsByPipe(data[3]))
                                : new ArrayList<Product>(),
                        (!data[4].equals(Utils.NA)) ? Amazeon.getSalesByIds(Utils.splitIdsByPipe(data[4]))
                                : new ArrayList<Sale>()));
            }
            return sellers;
        } catch (IOException e) {
            return new ArrayList<Seller>();
        }
    }

    public static void writeSellers(ArrayList<Seller> sellers) {
        try {
            BufferedWriter bw = Utils.createWriter(Utils.DATA_DIR + Utils.CART_FILE);
            for (Seller seller : sellers) {
                bw.write(Integer.toString(seller.getId()) + "," + seller.getEmail() + "," + seller.getPassword() + ","
                        + Utils.convertToIdString(Amazeon.getProductIds(seller.getProducts()).toString()) + ","
                        + Utils.convertToIdString(Amazeon.getSaleIds(seller.getSales()).toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}