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

public class SellerUpdateProductGUI extends JComponent implements Runnable {
    JFrame frame;
    JButton editProductButton;
    JButton returnHomeButton;
    JButton logOutButton;

    JTextField strId, nme, desc, quan, prce;

    int productId;

//    Product product  = new Product(1, "Sour Patch", 7, "sweet and sour candy", 3.00, 1, 1);

    public SellerUpdateProductGUI(Product product) {
        productId = product.getProductId();
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editProductButton) {
//                product.setProductId(productId);
//                product.setStoreId(Integer.parseInt(strId.getText()));
//                product.setName(nme.getText());
//                product.setDescription(desc.getText());
//                product.setQuantity(Integer.parseInt(quan.getText()));
//                product.setPrice(Double.parseDouble(prce.getText()));
//                frame.dispose();
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

        frame.setTitle("Update Product Page");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel productID = new JLabel("Product ID: " + productId);
        JLabel newStoreID = new JLabel("Enter new Store Id: ");
        JLabel newName = new JLabel("Enter new name: ");
        JLabel newDescription = new JLabel("Enter new description: ");
        JLabel newQuantity = new JLabel("Enter new quantity: ");
        JLabel newPrice = new JLabel("Enter new price: ");

        strId = new JTextField(10);
        nme = new JTextField(10);
        desc = new JTextField(10);
        quan = new JTextField(10);
        prce= new JTextField(10);

        editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(actionListener);
        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel topPanel = new JPanel();
        topPanel.add(productID);
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
        gbc.insets = new Insets(5,5,5,5);


        middlePanel.add(newStoreID, gbc);
        gbc.gridx++;
        middlePanel.add(strId, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newName, gbc);
        gbc.gridx++;
        middlePanel.add(nme, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newDescription, gbc);
        gbc.gridx++;
        middlePanel.add(desc, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newQuantity, gbc);
        gbc.gridx++;
        middlePanel.add(quan, gbc);
        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(newPrice, gbc);
        gbc.gridx++;
        middlePanel.add(prce, gbc);
//        gbc.gridx--;
        gbc.gridy++;
        middlePanel.add(editProductButton, gbc);

        content.add(middlePanel, BorderLayout.CENTER);


    }
//    public static void main(String[] args) {
//        Product product  = new Product(1, "Sour Patch", 7, "sweet and sour candy", 3.00, 1, 1);
//        SwingUtilities.invokeLater(new EditProductGUI(product));
//    }
}
