import java.io.*;
import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private int id;
    private ArrayList<Product> products; // userCustomer --> array of products purchased, userSeller --> array of
                                         // products being sold

    public User(int id, ArrayList<Product> products, String email, String password) {
        this.id = id;
        this.products = products;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int createAccount(String email, String password, String type) {
        File credentialsFile;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw;
        FileReader fr;
        BufferedReader br;
        try {
            if (type.equalsIgnoreCase("seller")) {
                credentialsFile = new File(Utils.DATA_DIR + Utils.SELLER_FILE);
            } else {
                credentialsFile = new File(Utils.DATA_DIR + Utils.CUSTOMER_FILE);
            }
            fw = new FileWriter(credentialsFile);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            fr = new FileReader(credentialsFile);
            br = new BufferedReader(fr);
            String currentLine = br.readLine();
            while (currentLine != null) {
                if (currentLine.split(";")[0].equals(email)) {
                    return Utils.NO;
                }
                currentLine = br.readLine();
            }
            return Utils.YES;
        } catch (IOException e) {
            e.printStackTrace();
            return Utils.NO;
        }
    }

    public int login(String email, String password, String type) {
        File credentialsFile;
        FileReader fr;
        BufferedReader br;
        try {
            if (type.equalsIgnoreCase("seller")) {
                credentialsFile = new File(Utils.DATA_DIR + Utils.SELLER_FILE);
            } else {
                credentialsFile = new File(Utils.DATA_DIR + Utils.CUSTOMER_FILE);
            }
            fr = new FileReader(credentialsFile);
            br = new BufferedReader(fr);
            String currentLine = br.readLine();
            while (currentLine != null) {
                if (currentLine.split(",")[1].equals(email)) {
                    if (currentLine.split(",")[2].equals(password)) {
                        return Utils.NO;
                    }
                }
                currentLine = br.readLine();
            }
            return Utils.YES;

        } catch (IOException e) {
            e.printStackTrace();
            return Utils.NO;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
