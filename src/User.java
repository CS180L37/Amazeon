import java.io.*;
import java.util.ArrayList;

public class User {
    private String id;
    private ArrayList<Product> products;

    public User(String id, ArrayList<Product> products) {
        this.id = id;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public static int createAccount(String email, String password, String type) {
        File credentialsFile;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw;
        FileReader fr;
        BufferedReader br;
        try {
            if (type.equalsIgnoreCase("seller")) {
                credentialsFile = new File("seller credentials.txt");
            } else {
                credentialsFile = new File("customer credentials.txt");
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
            pw.println(email + ";" + password);
            return Utils.YES;
        } catch (IOException e){
            e.printStackTrace();
            return Utils.NO;
        }
    }

    public static int login(String email, String password, String type) {
        File credentialsFile;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;
        FileReader fr;
        BufferedReader br;
        try {
            if (type.equalsIgnoreCase("seller")) {
            credentialsFile = new File("seller credentials.txt");
            } else {
                credentialsFile = new File("customer credentials.txt");
            }
            fw = new FileWriter(credentialsFile);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            fr = new FileReader(credentialsFile);
            br = new BufferedReader(fr);
            String currentLine = br.readLine();
            while (currentLine != null) {
                if (currentLine.split(";")[0].equals(email)) {
                    if (currentLine.split(";")[1].equals(password)) {
                        return 1;
                    }
                }
                currentLine = br.readLine();
            }
            return -1;

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
