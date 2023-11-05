// Parent class for SellerDashboard and CustomerDashboard

import java.util.ArrayList;

public class Dashboard<T> {
    private ArrayList<T> data; // The data contained in the dashboard

    public Dashboard(ArrayList<T> data) {
        this.data = data;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

}
