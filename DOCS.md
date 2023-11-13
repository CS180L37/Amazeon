# Documentation
## Amazeon
### Functionality
Amazeon.java is our main class. The entirety of our program is run from here. Firstly, all of the data needed to run the project is read and initialized right at the beginning of our main method. The creation of a new Amazeon object calls multiple read methods in order to initialize the data.
Next, our class goes thorough the login process if our user already has an account. Otherwise, they will be taken through the process of creating an account. Once they create an account, their data will be added to the customers' file which will update and preserve it as needed. Whether the user is logging in or creating an account, they will be asked to input their email and password and based on the user type (customer/seller) will be assigned a customer/seller id. If they are customer, the customer's loop will be called, otherwise the seller's loop will be called. These two loops are the main bodies of the program as they run the meat of it-from what customers/sellers can each see and do immediately after login until their time of exit.
After the customer or seller loop's run, all new data in addition to data that needs to be updated will be written to files to make sure that the data is preserved.
In addition to the flow of the program being run here, Amazeon also has many helper methods that make it easy to access instances of other classes using a simple parameter. They have come in much handy when implementing the rest of the program, especially the customer marker and seller market classes. _________________ etc..

### Testing:
_________________________________________etc..

Relationship to other classes:
It is the main class which runs our program. Thus, it is not the parent class to any subclass nor does it implement any interface. However, instances of other classes are made within it and used to call methods from their respective classes which may extend and/or implement other classes.




## MarketInterface.java
Functionality: The market interface takes in generics as paranmeters. These parameterized types are used in Market.java and represent the user type (customer or seller). The interface contains methods displayMarketplace(), displayDashboard(), displayProductPage(Product product), and displayCart(). Although the implementations of these methods are different for each user type, these are shared methods between the customer market and seller market.

Market.Java
Functionality: Market.java consists of a list of all the stores in the marketplace, regardless of whether you are a seller or customer. The class takes in generic parameter T which, in this case, represents user type (customer or seller). The main purpose of this class is to provide us with a list of all stores in the marketplace and whether a customer or seller is interacting with it.

CustomerMarket.java
Functionality: CustomerMarket has a dashboard field used to display the customer's dashobard. This class implements all methods in the MarketInterface class:
- displayMarketplace(): the entry point for customer marketplace, which displays every product available for purchase along with product information associated with each such as the 					product's name, description, and store name it is associated with
- displayProductPage(Product product): displays the page of the product the customer wants to look into (when customer clicks on product); the page includes the product's name, 							description, price, quanity available, name of store the product is associated with, the customer's purchase history in this marketplace, and a 						purchaseProduct option
- displayDashboard(): calls CustomerDashboard.java displayDashboard() method
- sort(boolean price, boolean quantityAvailable): customer is given the option to sort the marketplace listing page by price of the product or quantityAvailable of the product
- search(String name, String storeId, String description): allows customer to search for a product based on name, storeId, or description and ouptuts a list of all the products containing 								   that name, storeId, or description
- displayCart() - allows customer to view their cart by calling cart's display method

	Relationship to other classes:
		- extends Market impelements MarketInterface
SellerMarket.java
Functionality: SellerMarket has a dashboard field used to display the customer's dashobard. This class implements all methods in the MarketInterface class:
- displayMarketplace(): the entry point for seller marketplace, which displays every product being sold by the seller along with product information associated w/ each just like in 					customer's marketplace
- displayDashboard(): calls SellerDashboard.java displayDashboard() method
- displayCart(): allows sellers to view all of their customer's carts by calling cart's display method alog with the store and product details associated with each product

	Relationship to other classes:
		- extends Market impelements MarketInterface


DashboardInterface.java
Functionality: The dashboard interface takes in generics as parameters. These parameterized types are used in Dashboard.java and represent the data contained in the dashboard (different 			types used in customer's dashboard and seller dashboard). The interface contains two sort methods which return a sorted list of desired values. Although the sort options 			are different for customers or sellers, they both have two options, thus making it ideal to put in an interface.

Dashboard.java (abstract)
Functionality: Dashboard is an abstract class which takes in generic parameters T and U which represent data of some aspect of the marketplace (will be known in customer/seller dashboard 		       classes). The main goal of this class is to provide this data to us to be used in CustomerDashboard.java and SellerDashboard.java

CustomerDashboard.java
Functionality: CustomerDashboard.java consists of a customer field used to access a list of products needed to sort the dashobard. It extends Dashboard which implements DasboardInterface 		       which takes in two generics, T and U, which in the case of the customer dasboard represent Store data. Aditionally, this class consists of two sort methods and one display 		       method:
- sort1() --> sorts the customer's dashboard (which displays a list of stores) by the number of prdoucts sold by each store in descending order
- sort2() --> sorts the customer's dashboard (which displays a list of stores) by the number of prdoucts purchased by the particular customer at each store in 						      descending order
- displayDashboard() --> takes in customer input to decide sort1() or sort2(), sorts the dashboard, and displays the list of stores after the sort

	Relationship to other classes:
		- extends Dasboard<Store, Store> implements DashboardInterface<Store, Store>
SellerDashboard.java
Functionality: SellerDashboard.java extends Dashboard which implements DasboardInterface which takes in two generics, T and U, which in the case of the seller dasboard represent Customer 		       data and Product data respectively. Aditionally, this class consists of two sort methods and one display method:
- sort1() --> sorts the sellers's dashboard (which displays a list of customers) by the number of items each customer has purchases in descending order
- sort2() --> sorts the seller's dashboard (which displays a list of products) by the sales associated with each product in descending order
- displayDashboard() --> takes in seller input to decide sort1() or sort2(), sorts the dashboard, and displays the list of stores/products after the sort

	Relationship to other classes:
		- extends Dasboard<Customer, Product> implements DashboardInterface<Customer, Product>

UserInterface.java
- Functionality: The User Interface takes in a generic as a parameter. This generic parameterized type is used in User.java and represents either a customer or a seller. The interface consists of 4 methods: expotData(String filepath), importData(String filepath), editAccount(String email, String password), and deleteAccount(). These methods will be implemented in the Customer and Seller classes and are associated with the flexibility of accounts as well as the preservation of user data.

User.java
- Functionality: User contains fields email, password, id, and products. User's main methods include:
createAccount() --> creates an account for user based on whether they are a customer or a seller and updates the customer's file to preserve that data
login() --> validates user email and login and makes sure they exist in the system before being taken to their respective marketplace

Customer.java
Functionality: The customer class describes an instance of customer and consists of methods describing the actions they can perform:
- exportData() --> exports customer data to customer file
- purchaseProduct() --> adds product to customer's prchase history and reduces quantity available of product
- editAccount(String email, String password) --> allows customer to edit their accounts
- deleteAccount() --> deletes customer account
- readCustomers() --> reads file which contains list of all products and carts as parameters
- writeCustomers() --> writes updated customer data to file to keep data preserved

	Relationship to other classes:
		- extends User implements UserInterface<Customer>


Seller.java
Functionality: The seller class describes an instance of seller and consists of methods describing the actions they can perform:
- displayProducts() -->  displays seller's marketplace
- viewSales() --> displays a list of sales associated with each of the seller's stores and information associated with each sale such as customer id and revenue generated from the 				  sale
- editAccount(String email, String password) --> allows seller to edit their accounts
- deleteAccount() --> deletes seller account
- readSellers() --> contains list of all products and sales as parameters
- writeSellers() --> writes updated seller data to file to keep data preserved
- importData(String filename) --> imports seller information including the products they're listing
- exportData(String filepath) --> exports seller data to csv file
- createProduct() --> takes in seller input to create a product which is added to the seller product list and the marketplace's products
- updateProduct() --> takes in seller input to edit a product
- deleteProduct() --> removes product from product list

	Relationship to other classes:
		- extends User implements UserInterface<Seller>

Store.java
Functionality: describes an instance of a store, contains fields: store name, store id, store's products, and customers; has methods readStores(String filepath) and writeStores(stores, 		       String filepath) which assist in the preservation of data of the stores
Product.java
Functionality: describes an instance of a product, contians fields: product id, product name, product quantity, product description, product price, seller id, and store id; contains method readProducts() and writeProducts(), which assist in the preservation of data of the products
Sale.java
Functionality: describes an instance of a sale; contains methods readSales() and writeSales() which assist in the preservation of data of the sales
Cart.java
Functionality: describes an instance of a cart; contains readCarts() and writeCarts() methods which assist in the preservation of data of the carts; contains methods:
- addToCart(Product product) --> adds product to cart
- removeFromcart(Product product) --> removes product from cart
- purchaseCart() --> removes all products from cart as customers purchase every product in their cart
- display() --> displays the customer's cart

Utils.java
Functionality: contains static shared methods amongst all classes that make our code easier to write, less redundant, and more efficient

ValidInterface.java
Functionality: validates user input