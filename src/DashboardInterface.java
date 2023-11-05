import java.util.ArrayList;

public interface DashboardInterface<T> {
    ArrayList<T> sort(); // Returns a sorted list of the desired values

    void display(); // Display the dashboard
}
