package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerMarketplaceGUI extends JComponent implements Runnable{
    //frame
    JFrame frame;
    //container for frame
    Container content;

    //list of buttons you need
    JButton searchButton;
    JButton purchaseButton;
    JButton dashboardButton;
    JButton sortMarketplaceButton;
    JButton cartButton;
    JButton logOutButton;

    //dashboard button's dropdown menu (for sorts)
    JPopupMenu sortDashboardMenu;
    JMenuItem menuItemSort1;
    JMenuItem menuItemSort2;

    //sort marketplace button's dropdown menu (for sorts)
    JPopupMenu sortMarketplaceMenu;
    JMenuItem mpmenuItemSort1; //price
    JMenuItem mpmenuItemSort2; //quantity
    JPanel middlePanel; //panel where sorting happens



    //mock data
    //In the marketplace, we will have
    //2 stores
    //each with 3 products

    Product productOne = new Product(1, "Air Heads", 3, "tangy, taffy-like, chewy candy", 4.00, 1, 1);
    Product productTwo = new Product(2, "SourPatch Kids", 5, "soft candy with a coating of invert sugar and sour sugar", 2.00, 2, 1);
    Product productThree = new Product(3, "Dairy Milk", 7, "smooth and creamy wave of deliciousness moulded into a unique chocolate taste", 5.00, 3, 1);
    Product productFour = new Product(4, "Candy Cane", 9, "cane-shaped stick candy", 1.00, 1, 2);
    Product productFive = new Product(5, "Dots", 11, "gum drops", 3.00, 2, 2);
    Product productSix = new Product(6, "Haribo", 13, "gummy bears", 6.00, 3, 2);
    ArrayList<Product> storeOneProducts = new ArrayList<>();
    ArrayList<Product> storeTwoProducts = new ArrayList<>();

    Store storeOne;
    Store storeTwo;

    ArrayList<Store> storesList = new ArrayList<>();

    //constructor -- needed to create this mock data
    public CustomerMarketplaceGUI() throws IOException {
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


    //action listeners --> depending on what button you click, you do certain things (for now only the dropdowns are implemented)
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
                SwingUtilities.invokeLater(new CustomerPurchaseGUI());
            }
            if (e.getSource() == searchButton) {
                try {
                    frame.dispose();
                    SwingUtilities.invokeLater(new CustomerSearchGUI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == menuItemSort1) {
                frame.dispose();
                SwingUtilities.invokeLater(new CustomerSortOneDashboardGUI());
            }
            if (e.getSource() == menuItemSort2) {
                frame.dispose();
                SwingUtilities.invokeLater(new CustomerSortTwoDashboardGUI());
            }
            if (e.getSource() == mpmenuItemSort1) {
                middlePanel.removeAll();
                middlePanel.revalidate();
                middlePanel.repaint();
                middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in the marketplace

                ArrayList<Product> allProducts = new ArrayList<Product>();
                for (int i = 0; i < storesList.size(); i++) {
                    for(int j = 0; j < storesList.get(i).getProducts().size(); j++){
                        allProducts.add(storesList.get(i).getProducts().get(j));
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
                    JButton productButton = new JButton( //html used for style purposes only
                            "<html>" +
                                    "<div style='text-align: center;'>" +
                                    "<div>" + "Product Name: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "StoreName: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "Product Price: $" + allProducts.get(i).getPrice() + "0" + "</div>" +
                                    "</div>" +
                                    "</html>"
                    );
                    productButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
                    middlePanel.add(productButton);
                    Product product = allProducts.get(i);
                    productButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose();
                            SwingUtilities.invokeLater(new CustomerProductPage(product));
                        }
                    });
                }

            }


            if (e.getSource() == mpmenuItemSort2) {
                middlePanel.removeAll();
                middlePanel.revalidate();
                middlePanel.repaint();
                middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in the marketplace

                ArrayList<Product> allProducts = new ArrayList<Product>();
                for (int i = 0; i < storesList.size(); i++) {
                    for(int j = 0; j < storesList.get(i).getProducts().size(); j++){
                        allProducts.add(storesList.get(i).getProducts().get(j));
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
                    JButton productButton = new JButton( //html used for style purposes only
                            "<html>" +
                                    "<div style='text-align: center;'>" +
                                    "<div>" + "Product Name: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "StoreName: " + allProducts.get(i).getName() + "</div>" +
                                    "<div>" + "Product Price: $" + allProducts.get(i).getPrice() + "0" + "</div>" +
                                    "</div>" +
                                    "</html>"
                    );
                    productButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
                    middlePanel.add(productButton);
                    Product product = allProducts.get(i);
                    productButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose();
                            SwingUtilities.invokeLater(new CustomerProductPage(product));
                        }
                    });
                }
            }
            if (e.getSource() == cartButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new CustomerCartGUI());
            }
        }
    };


    //everything that needs to be displayed goes into the run method
    public void run() {
        frame = new JFrame("Customer Marketplace"); //creates frame

        content = frame.getContentPane(); //creates a container in which things go for that frame
        content.setLayout(new BorderLayout()); //sets layout nicely

        //sets frame style and displays frame
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


        //initialize your buttons and add action listsners to each
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


        //dashboard button's dropdown menu
        sortDashboardMenu= new JPopupMenu("Dashboard");

        menuItemSort1 = new JMenuItem("Sort 1");
        menuItemSort1.addActionListener(actionListener);
        sortDashboardMenu.add(menuItemSort1);

        menuItemSort2 = new JMenuItem("Sort 2");
        menuItemSort2.addActionListener(actionListener);
        sortDashboardMenu.add(menuItemSort2);

        //sort marketplace button's dropdown menu
        sortMarketplaceMenu= new JPopupMenu("Dashboard");

        mpmenuItemSort1 = new JMenuItem("Sort 1");
        mpmenuItemSort1.addActionListener(actionListener);
        sortMarketplaceMenu.add(mpmenuItemSort1);

        mpmenuItemSort2 = new JMenuItem("Sort 2");
        mpmenuItemSort2.addActionListener(actionListener);
        sortMarketplaceMenu.add(mpmenuItemSort2);


        //creates panel at top of frame and adds buttons
        JPanel topPanel = new JPanel();
        topPanel.add(searchButton);
        topPanel.add(purchaseButton);
        topPanel.add(dashboardButton);
        topPanel.add(sortMarketplaceButton);
        topPanel.add(cartButton);

        content.add(topPanel, BorderLayout.NORTH); // adds the panel to the container

        //creates panel at bottom and adds log out button
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH); // adds panel to container

        //creates panel in middle
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in the marketplace

        //iterates through stores list and each stores products' list in order to display product
        for(int i = 0; i < storesList.size(); i++){
            for(int j = 0; j < storesList.get(i).getProducts().size(); j++){
                JButton productButton = new JButton( //html used for style purposes only
                        "<html>" +
                                "<div style='text-align: center;'>" +
                                "<div>" + "Product Name: " + storesList.get(i).getProducts().get(j).getName() + "</div>" +
                                "<div>" + "StoreName: " + storesList.get(i).getName() + "</div>" +
                                "<div>" + "Product Price: $" + storesList.get(i).getProducts().get(j).getPrice() + "0" + "</div>" +
                                "</div>" +
                                "</html>"
                );
                productButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
                middlePanel.add(productButton);
                Product product = storesList.get(i).getProducts().get(j);
                productButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        SwingUtilities.invokeLater(new CustomerProductPage(product));
                    }
                });

            }
        }

        //sets up a scroll bar for panel
        JScrollPane scrollPane = new JScrollPane(middlePanel);

        // Set preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        content.add(scrollPane, BorderLayout.CENTER); // adds the scroll bar to the container

    }

    public static void main (String[] args) { //runs the program
        try{
            SwingUtilities.invokeLater(new CustomerMarketplaceGUI());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

