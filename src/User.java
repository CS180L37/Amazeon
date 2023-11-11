import java.io.*;
import java.util.ArrayList;

public class User {
    private int id;
    private ArrayList<Product> products;

    public User(int id, ArrayList<Product> products) {
        this.id = id;
        this.products = products;
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

    public static int createAccount(String email, String password) {
        File credentialsFile;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;
        FileReader fr;
        BufferedReader br;
        try {
            credentialsFile = new File("credentials.txt");
            fw = new FileWriter(credentialsFile);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            fr = new FileReader(credentialsFile);
            br = new BufferedReader(fr);
            String currentLine = br.readLine();
            while (currentLine != null) {
                if (currentLine.split(";")[0].equals(email)) {
                    return -1;
                }
                currentLine = br.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        assert pw != null;
        pw.println(email + ";" + password);
        return 1;
    }

    public static int login(String email, String password) {
        File credentialsFile;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;
        FileReader fr;
        BufferedReader br;
        try {
            credentialsFile = new File("credentials.txt");
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
