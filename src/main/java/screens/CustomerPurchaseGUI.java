package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class CustomerPurchaseGUI extends JComponent implements Runnable {
    JFrame frame;
    JTextField productId;
    JButton enterButton;
    JButton returnHomeButton;
    JButton logOutButton;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enterButton) {
                //takes us to that product's page for purchase
                //use get Product by productId if valid
                //SwingUtilities.invokeLater(new CustomerProductPage(Product));
            }
            if (e.getSource() == returnHomeButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerMarketplaceGUI());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
        }
    };
    public void run() {
        frame = new JFrame();

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JLabel topLabel = new JLabel("Purchase");
        JLabel label = new JLabel("Enter product id of product you would like to purchase");
        productId = new JTextField(20);
        enterButton = new JButton("Enter");
        enterButton.addActionListener(actionListener);
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        topPanel.add(topLabel);

        content.add(topPanel, BorderLayout.NORTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        middlePanel.add(label, gbc);
        gbc.gridy++;
        middlePanel.add(productId, gbc);
        gbc.gridy++;
        middlePanel.add(enterButton, gbc);

        content.add(middlePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH);


    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new CustomerPurchaseGUI());
    }
}
