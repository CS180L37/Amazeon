package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerDashboardTwoGUI extends JComponent implements Runnable {
    //sorts products by number of sales
    JFrame frame;

    JButton sortOneButton;
    JButton returnHomeButton;
    JButton logOutButton;


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == sortOneButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerDashboardOneGUI());
            }
            if(e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
            if(e.getSource() == returnHomeButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerMarketplaceGUI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    };


    public void run() {
        frame = new JFrame();

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setTitle("Dashboard Sort One Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Dashboard"));
        content.add(topPanel, BorderLayout.NORTH);

        sortOneButton = new JButton("Sort By Sales of Products");
        sortOneButton.addActionListener(actionListener);
        sortOneButton.setPreferredSize(new Dimension(150, 30));
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        // sorts by products by number of sales
//        JPanel westPanel = new JPanel();
//        westPanel.setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(5,5,5,5);
//
//        int numberSales = 0;
//        for (int i = 0; i < sellerProducts.size(); i++) {
//            Product product = sellerProducts.get(i);
//            for (Sale sale : getSellerById(sellerProducts.get(i).getSellerId()).getSales()) {
//                if (sale.getProduct().equals(product)) {
//                    numberSales++;
//                }
//            }
//
//            System.out.println(i + ". " + sellerProducts.get(i) + " - " + numberSales + " Sales");
//            numberSales = 0;
//        }

        JPanel eastPanel = new JPanel();
        eastPanel.add(sortOneButton);
        content.add(eastPanel, BorderLayout.EAST);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SellerDashboardTwoGUI());
    }
}