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

public class SellerDeleteProductGUI extends JComponent implements Runnable {
    JFrame frame;
    JButton deleteButton;
    JButton returnHomeButton;
    JButton logOutButton;
    JComboBox<String> productName;

    Seller seller;

    public SellerDeleteProductGUI(Seller seller) {
        this.seller = seller;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == deleteButton) {
                Product product;
                try {
                    System.out.println(productName.getSelectedItem().toString());
                    product = Product.getProductByName(productName.getSelectedItem().toString());
                    System.out.println(product.toString());
                    ArrayList<Product> newProductList = seller.getProducts();
                    newProductList.remove(product);
                    seller.setProducts(newProductList);
                    product.setDeleted(true);
                    frame.dispose();
                    SwingUtilities.invokeLater(new SellerMarketplaceGUI(seller));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
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

        frame.setTitle("Delete Product Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel deleteLabel = new JLabel("Select the product that you would like to delete");
        ArrayList<Product> products = seller.getProducts();
        ArrayList<String> productNames = new ArrayList<String>();
        for (Product product : products) {
            productNames.add(product.getName());
        }
        String[] productNamesList = new String[productNames.size()];
        productName = new JComboBox<String>(productNames.toArray(productNamesList));

        deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(actionListener);
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Delete"));
        content.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);
        content.add(bottomPanel, BorderLayout.SOUTH);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        middlePanel.add(deleteLabel, gbc);
        gbc.gridy++;
        middlePanel.add(productName, gbc);
        gbc.gridy++;
        middlePanel.add(deleteButton, gbc);

        content.add(middlePanel, BorderLayout.CENTER);
    }
    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new SellerDeleteProductGUI());
    // }
}
