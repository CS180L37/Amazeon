package screens;

import javax.swing.*;

public class CustomerSortTwoDashboardGUI extends JComponent implements Runnable{
    //sorts stores by products purchased by this particular customer
    public void run() {
        JFrame frame = new JFrame();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CustomerSortTwoDashboardGUI());
    }
}
