import java.lang.reflect.Array;
import java.util.*;
import java.io.*;



public class Seller extends User implements UserInterface{
    private String name;
    private String ID;
    private ArrayList<Product> productsSold;
    private ArrayList<Sale> sales;
    private ArrayList<Integer> productRevenues;
    Scanner scan = new Scanner(System.in);
    Product product = new Product();

    public Seller(String id, ArrayList<Product> products, ArrayList<Sale> sales) {
        super(id, products);
        this.sales = sales;
    }
    public void displayProducts() {
        for (int i = 0; i < getProductsSold().size(); i++) {
            System.out.printf("%d. %s\n", i, getProductsSold().get(i));
        }
    }

    public Seller(String name, String ID, ArrayList<Product> productsSold, ArrayList<Sale> sales, ArrayList<Integer> productRevenues) {
        super();
        this.name = name;
        this.ID = ID;
        this.productsSold = productsSold;
        this.sales = sales;
        this.productRevenues = productRevenues;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public ArrayList<Product> getProductsSold() {
        return productsSold;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public ArrayList<Integer> getProductRevenues() {
        return productRevenues;
    }

    public void displayDashboard(Scanner scan) {
    // Add a product to the sellers products list
        System.out.println("How do you want to sort?\n1. Customer name\n2. Product name\n3.");
        int choice = 3; //default
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

        //add sorting code

        //list of customers with number of items that they have purchased
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

        //list of products with the number of sales.
        int numberSales = 0;
        for (int i = 0; i < productsSold.size(); i++) {
            Product product = productsSold.get(i);
            for (Sale sale : sales) {
                if (sale.getProduct().equals(product)) {
                    numberSales++;
                }
            }

            System.out.println(i + ". " + productsSold.get(i) + " - " + numberSales + " Sales");
            numberSales = 0;
        }

        //code for sorting by items per customer:
        ArrayList<Map.Entry<Customer, Integer>> keyList = new ArrayList<>(customerIntegerMap.entrySet());
        keyList.sort(Comparator.comparing(Map.Entry::getValue));
        Map<Customer, Integer> sortedCustomerIntegerMap = new LinkedHashMap<>();

        for (Map.Entry<Customer, Integer> key : keyList) {
            sortedCustomerIntegerMap.put(key.getKey(), key.getValue());
        }

        //another way
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
        getProductsSold().remove(product);
    }

    public static void updateProduct(Product product, Scanner scan) {
        System.out.println("Enter new name:");
        String newName = scan.nextLine();
        //TODO
        System.out.println("Enter stores?");
        //something waiting for edstem
        System.out.println("Enter new description:");
        String newDesc = scan.next();
        System.out.println("Enter new quantity:");
        int newQuan = Integer.parseInt(scan.nextLine());
        System.out.println("Enter new price:");
        int newPrice = Integer.parseInt(scan.nextLine());
    }

    public void createProduct(String filename) throws IOException {
            int lineNumber = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("filename.csv"));
            while (br.readLine() != null) {
                lineNumber++;
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
            ArrayList<Integer> intArray = new ArrayList<Integer>();
            try {
                BufferedReader br = new BufferedReader(new FileReader("filename.csv"));
                String line = br.readLine();
                ArrayList<String[][]> data = new ArrayList<String[][]>();
                while (line != null) {
                    String[][] lines = new String[0][lineNumber];
                    lines = new String[][]{line.split(",")};
                    data.add(lines);
                    br.readLine();
                }
                String[] strArray = new String[data.size()];
                for (int i = 0; i < data.size(); i++) {
                    strArray = data.get(i)[3];
                }
                for (int j = 0; j < strArray.length; j++) {
                    intArray.set(j, Integer.parseInt(strArray[j]));
                }
                for (int k = 0; k < data.size(); k++) {
                    Product product = new Product(Integer.parseInt(data.get(k)[0][0]),
                            Integer.parseInt(data.get(k)[0][1]), data.get(k)[0][2], intArray, data.get(k)[0][4], Double.parseDouble(data.get()[0][5]));
                }
            } catch (IOException r) {
                r.printStackTrace();
            }
        }
    }


    public void exportProducts () {
        File outFile = new File("exported products.csv");
        try {
            FileWriter fw = new FileWriter(outFile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (Product product : getProductsSold()) {
                String storesString = "";
                ArrayList<Integer> storeIDs = product.getStoreIds();
                for (Store store : SellerMarket.getStores()) {
                    for (int i = 1; i < storeIDs.size(); i++) {
                        if (storeIDs.get(i) == store.getId()) {
                            storesString += store.getName() + ";";
                        }
                    }
                    if (storeIDs.get(0) == store.getId()) {
                        storesString += store.getName();
                    }
                }
                pw.println(product.getName() + "," + storesString + ","
                        + product.getDescription() + "," + product.getQuantity() + "," + product.getPrice());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateProducts() {
        File sellerProductFile;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw;
        FileReader fr;
        BufferedReader br;
        try {
            String[] i = getID().split("@");
            fw = new FileWriter(i[0] + i[1] + "ProductFile", false);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            for (Product product : getProducts()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportData(String filepath) {

    }

    // Import products from a csv file
    public void importData (String filepath){
        throw new UnsupportedOperationException("Unimplemented method 'importData'");
    }

    public static Seller getSellerById ( int sellerId){
        throw new UnsupportedOperationException("Unimplemented method 'getSellerById'");
    }

    public void setSales (ArrayList < Sale > sales) {
        this.sales = sales;
    }
}