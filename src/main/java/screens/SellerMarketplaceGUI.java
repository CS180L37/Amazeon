package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import models.Cart;
import models.Customer;
import models.Product;
import models.Sale;
import models.Seller;
import models.Store;

public class SellerMarketplaceGUI extends JComponent implements Runnable{
    JFrame frame;
    JButton createButton;
    JButton editButton;
    JButton deleteButton;
    JButton salesButton;
    JButton dashboardButton;
    JButton cartButton;
    JButton logOutButton;

    JPopupMenu sortDashboardMenu;
    JMenuItem menuItemSort1;
    JMenuItem menuItemSort2;
    JButton sortMarketplaceButton;
    JPopupMenu sortMarketplaceMenu;
    JMenuItem mpmenuItemSort1;
    JMenuItem mpmenuItemSort2;

//    Product productOne = new Product(1, "Air Heads", 3, "tangy, taffy-like, chewy candy", 4.00, 1, 1);
//    Product productTwo = new Product(2, "SourPatch Kids", 5, "soft candy with a coating of invert sugar and sour sugar", 2.00, 2, 1);
//    Product productThree = new Product(3, "Dairy Milk", 7, "smooth and creamy wave of deliciousness moulded into a unique chocolate taste", 5.00, 3, 1);
//    Product productFour = new Product(4, "Candy Cane", 9, "cane-shaped stick candy", 1.00, 1, 2);
//    Product productFive = new Product(5, "Dots", 11, "gum drops", 3.00, 2, 2);
//    Product productSix = new Product(6, "Haribo", 11, "gummy bears", 6.00, 3, 2);
//    ArrayList<Product> storeOneProducts = new ArrayList<>();
//    ArrayList<Product> storeTwoProducts = new ArrayList<>();
//
//    Store storeOne;
//    Store storeTwo;
//
//    ArrayList<Store> storesList = new ArrayList<>();

    //constructor -- needed to create this mock data
    public SellerMarketplaceGUI() throws IOException {
//        storeOneProducts.add(productOne);
//        storeOneProducts.add(productTwo);
//        storeOneProducts.add(productThree);
//        storeTwoProducts.add(productFour);
//        storeTwoProducts.add(productFive);
//        storeTwoProducts.add(productSix);
//
//        storeOne = new Store(100, storeOneProducts);
//        storeOne.setName("Candy One");
//        storeTwo = new Store(101, storeTwoProducts);
//        storeTwo.setName("Candy Two");
//        storesList.add(storeOne);
//        storesList.add(storeTwo);
    }


    //action listeners --> depending on what button you click, you do certain things (for now only the dropdowns are implemented)
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createButton) {
                frame.dispose();
                SwingUtilities.invokeLater(new SellerCreateProductGUI());
            }
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
        }
    };



    //everything that needs to be displayed goes into the run method
    public void run() {
        frame = new JFrame("Seller Marketplace"); //creates frame

        Container content = frame.getContentPane(); //creates a container in which things go for that frame
        content.setLayout(new BorderLayout()); //sets layout nicely

        //sets frame style and displays frame
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


        //initialize your buttons and add action listsners to each
        createButton = new JButton("Create");
        createButton.addActionListener(actionListener);

        editButton = new JButton("Edit");
        editButton.addActionListener(actionListener);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(actionListener);

        salesButton = new JButton("Sales");
        salesButton.addActionListener(actionListener);

        dashboardButton = new JButton("Dashboard");
        dashboardButton.addActionListener(actionListener);

        cartButton = new JButton("Cart");
        cartButton.addActionListener(actionListener);

        sortMarketplaceButton = new JButton();
        sortMarketplaceButton.addActionListener(actionListener);




        //dashboard button's dropdown menu
        sortDashboardMenu= new JPopupMenu("Dashboard");

        menuItemSort1 = new JMenuItem("Sort 1");
        sortDashboardMenu.add(menuItemSort1);

        menuItemSort2 = new JMenuItem("Sort 2");
        sortDashboardMenu.add(menuItemSort2);

        //sort marketplace button's dropdown menu
        sortMarketplaceMenu= new JPopupMenu("Dashboard");

        mpmenuItemSort1 = new JMenuItem("Sort 1");
        sortMarketplaceMenu.add(mpmenuItemSort1);

        mpmenuItemSort2 = new JMenuItem("Sort 2");
        sortMarketplaceMenu.add(mpmenuItemSort2);


        //creates panel at top of frame and adds buttons
        JPanel topPanel = new JPanel();
        topPanel.add(createButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        topPanel.add(salesButton);
        topPanel.add(dashboardButton);
        topPanel.add(cartButton);

        content.add(topPanel, BorderLayout.NORTH); // adds the panel to the container

        //creates panel at bottom and adds log out button
        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(actionListener);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logOutButton);

        content.add(bottomPanel, BorderLayout.SOUTH); // adds panel to container

        //creates panel in middle
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(0, 1)); // creates layout needed for a vertical arranagement of products in the marketplace

        //iterates through stores list and each stores products' list in order to display product information
        //change to seller's products (currently it is customer's products)
//        for(int i = 0; i < storesList.size(); i++){
//            for(int j = 0; j < storesList.get(i).getProducts().size(); j++){
//                JButton productButton = new JButton( //html used for style purposes only
//                        "<html>" +
//                                "<div style='text-align: center;'>" +
//                                "<div>" + "Product Name: " + storesList.get(i).getProducts().get(j).getName() + "</div>" +
//                                "<div>" + "StoreName: " + storesList.get(i).getName() + "</div>" +
//                                "<div>" + "Product Stock: " + storesList.get(i).getProducts().get(j).getQuantity() + "</div>" +
//                                "</div>" +
//                                "</html>"
//                );
//                productButton.setPreferredSize(new Dimension(200, 100)); //sets size of each product button
//                middlePanel.add(productButton);
//
//            }
//        }

        //sets up a scroll bar for panel
        JScrollPane scrollPane = new JScrollPane(middlePanel);

        // Set preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        content.add(scrollPane, BorderLayout.CENTER); // adds the scroll bar to the container

    }

    public static void main (String[] args) { //runs the program
        try{
            SwingUtilities.invokeLater(new SellerMarketplaceGUI());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
