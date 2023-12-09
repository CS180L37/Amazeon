package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class SellerSalesGUI extends JComponent implements Runnable{
    JFrame frame;
    JButton returnHomeButton;
    JButton logOutButton;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
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
        topPanel.add(new JLabel("Sales"));
        content.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

//        JPanel middlePanel = new JPanel();
//        middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in the marketplace
//
//        for(int i = 0; i < sales.size(); i++) {
//            JButton salesButton = new JButton("Sale ID: " + sales.get(i).getSaleId());
//
//            salesButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
//            middlePanel.add(salesButton);
//            Sale sale = sales.get(i);
//            salesButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    frame.dispose();
//                    SwingUtilities.invokeLater(new SellerSalePage(sale));
//                }
//            });
//        }
//        content.add(middlePanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SellerSalesGUI());
    }
}
