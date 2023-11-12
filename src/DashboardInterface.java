import java.util.ArrayList;

public interface DashboardInterface<T, U> {
    ArrayList<T> sort1(); // Returns a sorted list of the desired values.

    ArrayList<U> sort2(); // Returns a sorted list of the desired values.

    void displayDashboard(); // Displays the dashboard.
}
