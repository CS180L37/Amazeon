import java.util.ArrayList;

// Parent class for SellerDashboard and CustomerDashboard
public class Dashboard<T, U> {
    private ArrayList<T> data1; // The data contained in the dashboard
    private ArrayList<U> data2; // The data contained in the dashboard

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
}
