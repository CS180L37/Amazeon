// Parent class for SellerDashboard and CustomerDashboard
public class Dashboard<T> {
    private T[] data; // The data contained in the dashboard

    public Dashboard(T[] data) {
        this.data = data;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }

}
