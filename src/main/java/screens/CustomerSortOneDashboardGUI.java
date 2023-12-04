package screens;

import javax.swing.*;

public class CustomerSortOneDashboardGUI extends JComponent implements Runnable{
    //sorts stores by products sold
    public void run() {
        JFrame frame = new JFrame();

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CustomerSortOneDashboardGUI());
    }
}
