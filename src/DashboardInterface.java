import java.util.ArrayList;

public interface DashboardInterface<T, U> {
    ArrayList<T> sort1(User user); // Returns a sorted list of the desired values

    ArrayList<U> sort2(User user); // Returns a sorted list of the desired values

    void displayDashboard(); // Display the dashboard
}
