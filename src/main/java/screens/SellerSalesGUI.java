package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class SellerSalesGUI extends JComponent implements Runnable {
    JFrame frame;
    JButton returnHomeButton;
    JButton logOutButton;

    Seller seller;

    ArrayList<Sale> sales = new ArrayList<Sale>();

    public SellerSalesGUI(Seller seller) {
        this.seller = seller;
        sales = seller.getSales();
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
            if (e.getSource() == returnHomeButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerMarketplaceGUI(seller));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    };

    public void run() {
        frame = new JFrame();
        try {
            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setTitle("Sale Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Sales"));
        content.add(topPanel, BorderLayout.NORTH);

        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in
                                                     // the marketplace

        for (int i = 0; i < sales.size(); i++) {
            JButton salesButton = new JButton("Sale ID: " + sales.get(i).getSaleId());

            salesButton.setPreferredSize(new Dimension(150, 30)); // sets size of each product button
            middlePanel.add(salesButton);
            Sale sale = sales.get(i);
            salesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        frame.dispose();
                        SwingUtilities.invokeLater(new SellerSalePage(seller, sale));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        content.add(middlePanel, BorderLayout.CENTER);
    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerSalesGUI());
    // }
}
