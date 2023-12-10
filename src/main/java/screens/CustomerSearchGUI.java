package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import com.google.cloud.firestore.Query;
import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;
import utils.fields;

public class CustomerSearchGUI extends JComponent implements Runnable {
    JFrame frame;
    JButton nameButton;
    JButton storeIdButton;
    JButton descriptionButton;
    JButton returnHomeButton;
    JButton logOutButton;
    Customer customer;
    ArrayList<Store> stores;

    // constructor -- needed to create this mock data
    public CustomerSearchGUI(Customer customer) throws IOException {
        this.customer = customer;
        stores = Store.sortNonDeletedStores(fields.storeId, Query.Direction.ASCENDING);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nameButton) {
                frame.dispose();
                frame = new JFrame();
                try {
                    frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

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
                        frame = new JFrame();
                        try {
                            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }


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

                        ArrayList<Product> products;
                        JButton productButton;
                        try {
                            products = Product.getNonDeletedProductsByName(name.getText());
                            if(products.size() > 0) {
                                for(int i = 0; i < products.size(); i++) {
                                    productButton = new JButton("<html>" + "<div style='text-align: center;'>"
                                            + "<div>" + "Product Name: " + products.get(i).getName()
                                            + "</div>" + "<div>" + "StoreName: " + Store.getStoreById(products.get(i).getStoreId()).getName() + "</div>"
                                            + "<div>" + "Product Price: $" + products.get(i).getPrice() + "0" + "</div>"
                                            + "</div>" + "</html>");
                                    productButton.setPreferredSize(new Dimension(200, 100));
                                    panel.add(productButton);
                                    Product product = products.get(i);
                                    productButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                frame.dispose();
                                                SwingUtilities.invokeLater(new CustomerProductPage(customer, product));
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        }
                                    });
                                }
                            } else {
                                panel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                panel.add(new JLabel("No Related Products"));
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
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
                frame = new JFrame();
                try {
                    frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                Container content = frame.getContentPane();
                content.setLayout(new BorderLayout());

                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);

                JPanel topPanel = new JPanel();
                topPanel.add(new JLabel("Enter the store name of the product"));

                content.add(topPanel, BorderLayout.NORTH);

                JTextField storeName = new JTextField(25);
                JButton storeIdEnterButton = new JButton("Enter");
                storeIdEnterButton.addActionListener(actionListener);

                JPanel middlePanel = new JPanel();
                middlePanel.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 5, 5, 5);

                middlePanel.add(storeName, gbc);
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
                        frame = new JFrame();
                        try {
                            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

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

                        ArrayList<Product> products;
                        JButton productButton;
                        try {
                            products = Product.getNonDeletedProductsByStoreName(storeName.getText());
                            if(products.size() > 0) {
                                for(int i = 0; i < products.size(); i++) {
                                    productButton = new JButton("<html>" + "<div style='text-align: center;'>"
                                            + "<div>" + "Product Name: " + products.get(i).getName()
                                            + "</div>" + "<div>" + "StoreName: " + Store.getStoreById(products.get(i).getStoreId()).getName() + "</div>"
                                            + "<div>" + "Product Price: $" + products.get(i).getPrice() + "0" + "</div>"
                                            + "</div>" + "</html>");
                                    productButton.setPreferredSize(new Dimension(200, 100));
                                    panel.add(productButton);
                                    Product product = products.get(i);

                                    productButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                frame.dispose();
                                                SwingUtilities.invokeLater(new CustomerProductPage(customer, product));
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        }
                                    });
                                }
                            } else {
                                panel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                panel.add(new JLabel("No Related Products!"));
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
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
                frame = new JFrame();
                try {
                    frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

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
                        frame = new JFrame();
                        try {
                            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

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

                        ArrayList<Product> products;
                        JButton productButton;
                        try {
                            products = Product.getNonDeletedProductsByDescription(description.getText());
                            if(products.size() > 0) {
                                for(int i = 0; i < products.size(); i++) {
                                    productButton = new JButton("<html>" + "<div style='text-align: center;'>"
                                            + "<div>" + "Product Name: " + products.get(i).getName()
                                            + "</div>" + "<div>" + "StoreName: " + Store.getStoreById(products.get(i).getStoreId()).getName() + "</div>"
                                            + "<div>" + "Product Price: $" + products.get(i).getPrice() + "0" + "</div>"
                                            + "</div>" + "</html>");
                                    productButton.setPreferredSize(new Dimension(200, 100));
                                    panel.add(productButton);
                                    Product product = products.get(i);
                                    productButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                frame.dispose();
                                                SwingUtilities.invokeLater(new CustomerProductPage(customer, product));
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                        }
                                    });
                                }
                            } else {
                                panel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                panel.add(new JLabel("No Related Products!"));
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
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
                    SwingUtilities.invokeLater(new CustomerMarketplaceGUI(customer));
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
        try {
            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        nameButton = new JButton("Name");
        nameButton.addActionListener(actionListener);
        storeIdButton = new JButton("Store Name");
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
        storeIdButton.setPreferredSize(new Dimension(150, 40));
        panel.add(storeIdButton, gbc);
        gbc.gridx++;
        descriptionButton.setPreferredSize(new Dimension(150, 40));
        panel.add(descriptionButton, gbc);

        content.add(panel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnHomeButton);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

    }
    // public static void main(String[] args) {
    // try{
    // SwingUtilities.invokeLater(new CustomerSearchGUI());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
}
