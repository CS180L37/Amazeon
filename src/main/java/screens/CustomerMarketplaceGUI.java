package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

public class CustomerMarketplaceGUI extends JComponent implements Runnable {
    // frame
    JFrame frame;
    // container for frame
    Container content;

    // list of buttons you need
    JButton searchButton;
    JButton purchaseButton;
    JButton dashboardButton;
    JButton sortMarketplaceButton;
    JButton cartButton;
    JButton logOutButton;

    // dashboard button's dropdown menu (for sorts)
    JPopupMenu sortDashboardMenu;
    JMenuItem menuItemSort1;
    JMenuItem menuItemSort2;

    // sort marketplace button's dropdown menu (for sorts)
    JPopupMenu sortMarketplaceMenu;
    JMenuItem mpmenuItemSort1; // price
    JMenuItem mpmenuItemSort2; // quantity
    JPanel middlePanel; // panel where sorting happens

    ArrayList<Store> stores;

    Customer customer;

    // constructor -- needed to create this mock data
    public CustomerMarketplaceGUI(Customer customer) throws IOException {
        // System.out.println("getting here"); //for debugging
        this.customer = customer;
        stores = Store.sortNonDeletedStores(fields.storeId, Query.Direction.ASCENDING);
    }

    // action listeners --> depending on what button you click, you do certain
    // things (for now only the dropdowns are implemented)
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == dashboardButton) {
                sortDashboardMenu.show(dashboardButton, 0, dashboardButton.getHeight());
            }
            if (e.getSource() == sortMarketplaceButton) {
                sortMarketplaceMenu.show(sortMarketplaceButton, 0, sortMarketplaceButton.getHeight());
            }
            if (e.getSource() == logOutButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new LoginGUI());
            }
            if (e.getSource() == purchaseButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new CustomerPurchaseGUI(customer));
            }
            if (e.getSource() == searchButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerSearchGUI(customer));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == menuItemSort1) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerSortOneDashboardGUI(customer));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == menuItemSort2) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerSortTwoDashboardGUI(customer));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getSource() == mpmenuItemSort1) {
                middlePanel.removeAll();
                middlePanel.revalidate();
                middlePanel.repaint();
                middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of
                                                             // products in the marketplace

                ArrayList<Product> allProducts = new ArrayList<Product>();
                for (int i = 0; i < stores.size(); i++) {
                    for (int j = 0; j < stores.get(i).getStoreProducts().size(); j++) {
                        allProducts.add(stores.get(i).getStoreProducts().get(j));
                    }
                }

                for (int i = 0; i < allProducts.size() - 1; i++) {
                    int minIndex = i;
                    for (int j = i + 1; j < allProducts.size(); j++) {
                        if (allProducts.get(j).getPrice() < allProducts.get(minIndex).getPrice()) {
                            minIndex = j;
                        }
                    }

                    if (minIndex != i) {
                        Product product = allProducts.get(minIndex);
                        allProducts.set(minIndex, allProducts.get(i));
                        allProducts.set(i, product);
                    }
                }

                // // prints all products in marketplace
                for (int i = 0; i < allProducts.size(); i++) {
                    JButton productButton = new JButton( // html used for style purposes only
                            "<html>" +
                                    "<div style='text-align: center;'>" +
                                    "<div>" + "Product Name: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "StoreName: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "Product Price: $" + allProducts.get(i).getPrice() + "0" + "</div>" +
                                    "</div>" +
                                    "</html>");
                    productButton.setPreferredSize(new Dimension(200, 100)); // sets size of each product button
                    middlePanel.add(productButton);
                    Product product = allProducts.get(i);
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

            }

            if (e.getSource() == mpmenuItemSort2) {
                middlePanel.removeAll();
                middlePanel.revalidate();
                middlePanel.repaint();
                middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of
                                                             // products in the marketplace

                ArrayList<Product> allProducts = new ArrayList<Product>();
                for (int i = 0; i < stores.size(); i++) {
                    for (int j = 0; j < stores.get(i).getStoreProducts().size(); j++) {
                        allProducts.add(stores.get(i).getStoreProducts().get(j));
                    }
                }

                for (int i = 0; i < allProducts.size() - 1; i++) {
                    int minIndex = i;
                    for (int j = i + 1; j < allProducts.size(); j++) {
                        if (allProducts.get(j).getQuantity() < allProducts.get(minIndex).getQuantity()) {
                            minIndex = j;
                        }
                    }

                    if (minIndex != i) {
                        Product product = allProducts.get(minIndex);
                        allProducts.set(minIndex, allProducts.get(i));
                        allProducts.set(i, product);
                    }
                }

                // // prints all products in marketplace
                for (int i = 0; i < allProducts.size(); i++) {
                    JButton productButton = new JButton( // html used for style purposes only
                            "<html>" +
                                    "<div style='text-align: center;'>" +
                                    "<div>" + "Product Name: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "StoreName: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "Product Price: $" + allProducts.get(i).getPrice() + "0" + "</div>" +
                                    "</div>" +
                                    "</html>");
                    productButton.setPreferredSize(new Dimension(200, 100)); // sets size of each product button
                    middlePanel.add(productButton);
                    Product product = allProducts.get(i);
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
            }
            if (e.getSource() == cartButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new CustomerCartGUI(customer));
            }
        }
    };

    // everything that needs to be displayed goes into the run method
    public void run() {
        frame = new JFrame("Customer Marketplace"); // creates frame
        try {
            frame.setIconImage(javax.imageio.ImageIO.read(new java.io.File("src/main/resources/logo.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = frame.getContentPane(); // creates a container in which things go for that frame
        content.setLayout(new BorderLayout()); // sets layout nicely

        // sets frame style and displays frame
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        // initialize your buttons and add action listsners to each
        searchButton = new JButton("Search");
        searchButton.addActionListener(actionListener);

        purchaseButton = new JButton("Purchase");
        purchaseButton.addActionListener(actionListener);

        dashboardButton = new JButton("Dashboard");
        dashboardButton.addActionListener(actionListener);

        sortMarketplaceButton = new JButton("Sort Marketplace");
        sortMarketplaceButton.addActionListener(actionListener);

        cartButton = new JButton("Cart");
        cartButton.addActionListener(actionListener);

        // dashboard button's dropdown menu
        sortDashboardMenu = new JPopupMenu("Dashboard");

        menuItemSort1 = new JMenuItem("Sort 1");
        menuItemSort1.addActionListener(actionListener);
        sortDashboardMenu.add(menuItemSort1);

        menuItemSort2 = new JMenuItem("Sort 2");
        menuItemSort2.addActionListener(actionListener);
        sortDashboardMenu.add(menuItemSort2);

        // sort marketplace button's dropdown menu
        sortMarketplaceMenu = new JPopupMenu("Dashboard");

        mpmenuItemSort1 = new JMenuItem("Sort 1");
        mpmenuItemSort1.addActionListener(actionListener);
        sortMarketplaceMenu.add(mpmenuItemSort1);

        mpmenuItemSort2 = new JMenuItem("Sort 2");
        mpmenuItemSort2.addActionListener(actionListener);
        sortMarketplaceMenu.add(mpmenuItemSort2);

        // creates panel at top of frame and adds buttons
        JPanel topPanel = new JPanel();
        topPanel.add(searchButton);
        topPanel.add(purchaseButton);
        topPanel.add(dashboardButton);
        topPanel.add(sortMarketplaceButton);
        topPanel.add(cartButton);

        content.add(topPanel, BorderLayout.NORTH); // adds the panel to the container

        // creates panel at bottom and adds log out button
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH); // adds panel to container

        // creates panel in middle
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in
                                                     // the marketplace

        // iterates through stores list and each stores products' list in order to
        // display product
        for (int i = 0; i < stores.size(); i++) {
            for (int j = 0; j < stores.get(i).getStoreProducts().size(); j++) {
                JButton productButton = new JButton( // html used for style purposes only
                        "<html>" +
                                "<div style='text-align: center;'>" +
                                "<div>" + "Product Name: " + stores.get(i).getStoreProducts().get(j).getName()
                                + "</div>"
                                +
                                "<div>" + "StoreName: " + stores.get(i).getName() + "</div>" +
                                "<div>" + "Product Price: $" + stores.get(i).getStoreProducts().get(j).getPrice() + "0"
                                + "</div>" +
                                "</div>" +
                                "</html>");
                productButton.setPreferredSize(new Dimension(200, 100)); // sets size of each product button
                middlePanel.add(productButton);
                Product product = stores.get(i).getStoreProducts().get(j);
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
        }

        // sets up a scroll bar for panel
        JScrollPane scrollPane = new JScrollPane(middlePanel);

        // Set preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        content.add(scrollPane, BorderLayout.CENTER); // adds the scroll bar to the container

    }

    // public static void main(String[] args) { // runs the program
    // try {
    // SwingUtilities.invokeLater(new CustomerMarketplaceGUI());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
}
