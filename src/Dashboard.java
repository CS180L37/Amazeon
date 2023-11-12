import java.util.ArrayList;

// Parent class for SellerDashboard and CustomerDashboard
public abstract class Dashboard<T, U> {
    private ArrayList<T> data1; // The data contained in the dashboard
    private ArrayList<U> data2; // The data contained in the dashboard

    public Dashboard() {
        this.data1 = new ArrayList<T>();
        this.data2 = new ArrayList<U>();
    }

    public Dashboard(ArrayList<T> data1, ArrayList<U> data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public ArrayList<T> getData1() {
        return data1;
    }

    public void setData1(ArrayList<T> data1) {
        this.data1 = data1;
    }

    public ArrayList<U> getData2() {
        return data2;
    }

    public void setData2(ArrayList<U> data2) {
        this.data2 = data2;
    }

    // 2 sort methods exist to account for seller having to sort two different data
    // types
    // Sort stores by products sold
    public abstract ArrayList<Store> sort1(User user);

    // Sort stores by products purchased by a customer
    public abstract ArrayList<Store> sort2(User user);
}
