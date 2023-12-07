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

public class CustomerSearchGUI extends JComponent implements Runnable{
    JFrame frame;
    JButton nameButton;
    JButton storeIdButton;
    JButton descriptionButton;
//    JButton nameEnterButton;
//    JTextField name;
//    JButton storeIdEnterButton;
//    JTextField storeId;
//    JButton descriptionEnterButton;
//    JTextField description;
    JButton returnHomeButton;
    JButton logOutButton;

    Product productOne = new Product(1, "Air Heads", 3, "tangy, taffy-like, chewy candy", 4.00, 1, 1);
    Product productTwo = new Product(2, "SourPatch Kids", 5, "soft candy with a coating of invert sugar and sour sugar", 2.00, 2, 1);
    Product productThree = new Product(3, "Dairy Milk", 7, "smooth and creamy wave of deliciousness moulded into a unique chocolate taste", 5.00, 3, 1);
    Product productFour = new Product(4, "Candy Cane", 9, "cane-shaped stick candy", 1.00, 1, 2);
    Product productFive = new Product(5, "Dots", 11, "gum drops", 3.00, 2, 2);
    Product productSix = new Product(6, "Haribo", 11, "gummy bears", 6.00, 3, 2);
    ArrayList<Product> storeOneProducts = new ArrayList<>();
    ArrayList<Product> storeTwoProducts = new ArrayList<>();

    Store storeOne;
    Store storeTwo;

    ArrayList<Store> storesList = new ArrayList<>();

    //constructor -- needed to create this mock data
    public CustomerSearchGUI() throws IOException {
        storeOneProducts.add(productOne);
        storeOneProducts.add(productTwo);
        storeOneProducts.add(productThree);
        storeTwoProducts.add(productFour);
        storeTwoProducts.add(productFive);
        storeTwoProducts.add(productSix);

        storeOne = new Store(100, storeOneProducts);
        storeOne.setName("Candy One");
        storeTwo = new Store(101, storeTwoProducts);
        storeTwo.setName("Candy Two");
        storesList.add(storeOne);
        storesList.add(storeTwo);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nameButton) {
                frame.dispose();
                JFrame frame = new JFrame();

                Container content = frame.getContentPane();
                content.setLayout(new BorderLayout());

                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);

                JPanel topPanel = new JPanel();
                topPanel.add(new JLabel("Enter the name of the product"));

                content.add(topPanel, BorderLayout.NORTH);


                JTextField name = new JTextField(25);
                JButton nameEnterButton = new JButton("Enter");
                nameEnterButton.addActionListener(actionListener);

                JPanel middlePanel = new JPanel();
                middlePanel.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 5, 5, 5);

                middlePanel.add(name, gbc);
                gbc.gridy++;
                middlePanel.add(nameEnterButton, gbc);

                content.add(middlePanel, BorderLayout.CENTER);

                JPanel bottomPanel = new JPanel();
                bottomPanel.add(returnHomeButton);
                bottomPanel.add(new JLabel("     "));
                bottomPanel.add(logOutButton);

                content.add(bottomPanel, BorderLayout.SOUTH);

                nameEnterButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        JFrame frame = new JFrame();

                        Container content = frame.getContentPane();
                        content.setLayout(new BorderLayout());

                        frame.setSize(600, 400);
                        frame.setLocationRelativeTo(null);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);

                        JPanel topPanel = new JPanel();
                        topPanel.add(new JLabel("Related Products"));
                        content.add(topPanel, BorderLayout.NORTH);

                        JPanel panel = new JPanel();

                        for(int i = 0; i < storesList.size(); i++){
                            for(int j = 0; j < storesList.get(i).getProducts().size(); j++){
                                if(storesList.get(i).getProducts().get(j).getName().contains(name.getText())){
                                    JButton productButton = new JButton("<html>" +
                                            "<div style='text-align: center;'>" +
                                            "<div>" + "Product Name: " + storesList.get(i).getProducts().get(j).getName() + "</div>" +
                                            "<div>" + "StoreName: " + storesList.get(i).getName() + "</div>" +
                                            "<div>" + "Product Price: $" + storesList.get(i).getProducts().get(j).getPrice() + "0" + "</div>" +
                                            "</div>" +
                                            "</html>");
                                    productButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
                                    panel.add(productButton);
                                    Product product = storesList.get(i).getProducts().get(j);

                                    productButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            frame.dispose();
                                            SwingUtilities.invokeLater(new CustomerProductPage(product));
                                        }
                                    });
                                }
                            }
                        }
                        content.add(panel, BorderLayout.CENTER);

                        JPanel bottomPanel = new JPanel();
                        bottomPanel.add(returnHomeButton);
                        bottomPanel.add(new JLabel("     "));
                        bottomPanel.add(logOutButton);
                        content.add(bottomPanel, BorderLayout.SOUTH);
                    }
                });

            }
            if (e.getSource() == storeIdButton) {
                frame.dispose();
                JFrame frame = new JFrame();

                Container content = frame.getContentPane();
                content.setLayout(new BorderLayout());

                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);

                JPanel topPanel = new JPanel();
                topPanel.add(new JLabel("Enter the store id of the product"));

                content.add(topPanel, BorderLayout.NORTH);


                JTextField storeId = new JTextField(25);
                JButton storeIdEnterButton = new JButton("Enter");
                storeIdEnterButton.addActionListener(actionListener);

                JPanel middlePanel = new JPanel();
                middlePanel.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 5, 5, 5);

                middlePanel.add(storeId, gbc);
                gbc.gridy++;
                middlePanel.add(storeIdEnterButton, gbc);

                content.add(middlePanel, BorderLayout.CENTER);

                JPanel bottomPanel = new JPanel();
                bottomPanel.add(returnHomeButton);
                bottomPanel.add(new JLabel("     "));
                bottomPanel.add(logOutButton);

                content.add(bottomPanel, BorderLayout.SOUTH);

                storeIdEnterButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        JFrame frame = new JFrame();

                        Container content = frame.getContentPane();
                        content.setLayout(new BorderLayout());

                        frame.setSize(600, 400);
                        frame.setLocationRelativeTo(null);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);

                        JPanel topPanel = new JPanel();
                        topPanel.add(new JLabel("Related Products"));
                        content.add(topPanel, BorderLayout.NORTH);

                        JPanel panel = new JPanel();

                        for(int i = 0; i < storesList.size(); i++){
                            for(int j = 0; j < storesList.get(i).getProducts().size(); j++){
                                if(String.valueOf(storesList.get(i).getProducts().get(j).getStoreId()).contains(storeId.getText())){
                                    JButton productButton = new JButton("<html>" +
                                            "<div style='text-align: center;'>" +
                                            "<div>" + "Product Name: " + storesList.get(i).getProducts().get(j).getName() + "</div>" +
                                            "<div>" + "StoreName: " + storesList.get(i).getName() + "</div>" +
                                            "<div>" + "Product Price: $" + storesList.get(i).getProducts().get(j).getPrice() + "0" + "</div>" +
                                            "</div>" +
                                            "</html>");
                                    productButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
                                    panel.add(productButton);
                                    Product product = storesList.get(i).getProducts().get(j);

                                    productButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            frame.dispose();
                                            SwingUtilities.invokeLater(new CustomerProductPage(product));
                                        }
                                    });
                                }
                            }
                        }
                        content.add(panel, BorderLayout.CENTER);

                        JPanel bottomPanel = new JPanel();
                        bottomPanel.add(returnHomeButton);
                        bottomPanel.add(new JLabel("     "));
                        bottomPanel.add(logOutButton);
                        content.add(bottomPanel, BorderLayout.SOUTH);
                    }
                });
            }
            if (e.getSource() == descriptionButton) {
                frame.dispose();
                JFrame frame = new JFrame();

                Container content = frame.getContentPane();
                content.setLayout(new BorderLayout());

                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);

                JPanel topPanel = new JPanel();
                topPanel.add(new JLabel("Enter the description of the product"));

                content.add(topPanel, BorderLayout.NORTH);


                JTextField description = new JTextField(45);
                JButton descriptionEnterButton = new JButton("Enter");
                descriptionEnterButton.addActionListener(actionListener);

                JPanel middlePanel = new JPanel();
                middlePanel.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 5, 5, 5);

                middlePanel.add(description, gbc);
                gbc.gridy++;
                middlePanel.add(descriptionEnterButton, gbc);

                content.add(middlePanel, BorderLayout.CENTER);

                JPanel bottomPanel = new JPanel();
                bottomPanel.add(returnHomeButton);
                bottomPanel.add(new JLabel("     "));
                bottomPanel.add(logOutButton);

                content.add(bottomPanel, BorderLayout.SOUTH);
                descriptionEnterButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        JFrame frame = new JFrame();

                        Container content = frame.getContentPane();
                        content.setLayout(new BorderLayout());

                        frame.setSize(600, 400);
                        frame.setLocationRelativeTo(null);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setVisible(true);

                        JPanel topPanel = new JPanel();
                        topPanel.add(new JLabel("Related Products"));
                        content.add(topPanel, BorderLayout.NORTH);

                        JPanel panel = new JPanel();

                        for(int i = 0; i < storesList.size(); i++){
                            for(int j = 0; j < storesList.get(i).getProducts().size(); j++){
                                if(storesList.get(i).getProducts().get(j).getDescription().contains(description.getText())){
                                    JButton productButton = new JButton("<html>" +
                                            "<div style='text-align: center;'>" +
                                            "<div>" + "Product Name: " + storesList.get(i).getProducts().get(j).getName() + "</div>" +
                                            "<div>" + "StoreName: " + storesList.get(i).getName() + "</div>" +
                                            "<div>" + "Product Price: $" + storesList.get(i).getProducts().get(j).getPrice() + "0" + "</div>" +
                                            "</div>" +
                                            "</html>");
                                    productButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
                                    panel.add(productButton);
                                    Product product = storesList.get(i).getProducts().get(j);

                                    productButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            frame.dispose();
                                            SwingUtilities.invokeLater(new CustomerProductPage(product));
                                        }
                                    });
                                }
                            }
                        }
                        content.add(panel, BorderLayout.CENTER);

                        JPanel bottomPanel = new JPanel();
                        bottomPanel.add(returnHomeButton);
                        bottomPanel.add(new JLabel("     "));
                        bottomPanel.add(logOutButton);
                        content.add(bottomPanel, BorderLayout.SOUTH);
                    }
                });
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
    public void run(){
        frame = new JFrame();

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        nameButton = new JButton("Name");
        nameButton.addActionListener(actionListener);
        storeIdButton = new JButton("Store ID");
        storeIdButton.addActionListener(actionListener);
        descriptionButton = new JButton("Description");
        descriptionButton.addActionListener(actionListener);

        returnHomeButton = new JButton("Return Home");
        returnHomeButton.addActionListener(actionListener);
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JLabel forTopPanel = new JLabel("Search By");

        JPanel topPanel = new JPanel();
        topPanel.add(forTopPanel);

        content.add(topPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        nameButton.setPreferredSize(new Dimension(150, 40));
        panel.add(nameButton, gbc);
        gbc.gridx++;
        storeIdButton.setPreferredSize(new Dimension(150,40));
        panel.add(storeIdButton, gbc);
        gbc.gridx++;
        descriptionButton.setPreferredSize(new Dimension(150,40));
        panel.add(descriptionButton, gbc);

        content.add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

    }
    public static void main(String[] args) {
        try{
            SwingUtilities.invokeLater(new CustomerSearchGUI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
