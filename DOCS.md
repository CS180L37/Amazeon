# Documentation
## Amazeon
### Functionality
Amazeon.java is our main class. The entirety of our program is run from here. Firstly, all of the data needed to run the project is read and initialized right at the beginning of our main method. The creation of a new Amazeon object calls multiple read methods in order to initialize the data.
Next, our class goes thorough the login process if our user already has an account. Otherwise, they will be taken through the process of creating an account. Once they create an account, their data will be added to the customers' file which will update and preserve it as needed. Whether the user is logging in or creating an account, they will be asked to input their email and password and based on the user type (customer/seller) will be assigned a customer/seller id. If they are customer, the customer's loop will be called, otherwise the seller's loop will be called. These two loops are the main bodies of the program as they run the meat of it-from what customers/sellers can each see and do immediately after login until their time of exit.
After the customer or seller loop's run, all new data in addition to data that needs to be updated will be written to files to make sure that the data is preserved.
In addition to the flow of the program being run here, Amazeon also has many helper methods that make it easy to access instances of other classes using a simple parameter (almost reminiscent of a NoSQL database). They have come in much handy when implementing the rest of the application.

## TODO
- Compile into one executable

## Client Dependencies
For compiling and running from source: Java, and Maven dependencies for FireStore and JUnit. Maven handles a lot of the process for you.
For the built JAR file: just Java. :)

## Dev Dependencies
- firebase CLI (https://firebase.google.com/docs/cli)
- service_account.json file in `src/main/resources`
- Maven
- JUnit
- google-cloud-firestore

## Usage
```
git clone https://github.com/CS180L37/Project4-5.git
cd Project4-5
mv service_account.json src/main/resources
cd src/main/java
find . -name "*.java" -print | xargs javac
java Amazeon.java
```

## Testing
> For more specifics, see (https://firebase.google.com/docs/cli)
- Install the firebase CLI via `curl -sL https://firebase.tools/ | bash` or `npm install -g firebase-tools` if you're on a Windows machine. You may need to authenticate your Google account to use the emulator
- Start the firestore emulator via `firebase emulators:start`
- Run the test suite
- Stop the firestore emulator after the tests have completed and you're done! :)

For an example of the process, view the screencast here:
[TestCases.webm](https://github.com/CS180L37/Project4-5/assets/86136010/ba9ea788-1bc2-4f1f-8001-7250c13bc2cb)

## Documentation

- [Amazeon.Java](DOCS.md#Amazeon)
- [MarketInterface.Java](DOCS.md#MarketInterface)
- [Market.Java](DOCS.md#Market)
- [CustomerMarket.java](DOCS.md#CustomerMarket)
- [SellerMarket.java](DOCS.md#SellerMarket)
- [DashboardInterface.java](DOCS.md#DashbooardInterface)
- [Dashboard.java (abstract)](DOCS.md#Dashboard)
- [CustomerDashboard.java](DOCS.md#CustomerDashboard)
- [SellerDashboard.java](DOCS.md#SellerDashboard)
- [UserInterface.java](DOCS.md#UserInterface)
- [User.java](DOCS.md#User)
- [Customer.java](DOCS.md#Customer)
- [Seller.java](DOCS.md#Seller)
- [Store.java](DOCS.md#Store)
- [Product.java](DOCS.md#Product)
- [Sale.java](DOCS.md#Sale)
- [Cart.java](DOCS.md#Cart)
- [Utils.java](DOCS.md#Utils)
- [ValidateInterface.java](DOCS.md#ValidateInterface)
- [Testing](DOCS.md#Testing)



### Front-End
#### Customer
The Customer class foremost has a Login Page which requires the customer to input the email and password. The Login Page
also has an option to Create an account page. This again requires the customer to input their details in the form of email and password.
The Customer class has a Customer MarketPlace page which in turn has three JButtons - Search, Purchase and Display Dashboard.
The Display Dashboard JButton has a dropdown which gives us two ways of sorting.
The sort 1 sorts the stores by products sold. Whereas sort 2 sorts stores by products purchased by the particular customer.
Each screen under the Customer has a LogOut and Return Home JButton.

The next screen for implementation is the Product Page
which has various labels in the order of Store Name, Product Name, Description, Quantity(Stock) and Price. The Product Page
also has an Add to Cart JButton.
Next up is the Dashboard Page which has two sort methods as explained earlier.
The Search Page screen has three JButtons - Name, StoreID and Description. Each of these JButtons in turn have a Text Field for the
name of Product ID. This is simply done using a JButton called Enter Button which leads to a page with a list of user inputs such as Product Name, Store Name, etc.
The Purchase Page again includes a Text Field for Product ID using an Enter Button.
The Cart Page displays the customer's id. The Cart Page includes a list of Products which are inputted by the user.
This includes JButtons such as Remove and Purchase. Finally the Cart Page also includes an option for Purchasing all the products the customer requires.

#### Seller
The Seller class has a Seller MarketPlace page which have six JButtons under it. Following are the JButtons - Create, Edit, Delete, Sales, Dashboard and Cart.
The DashBoard JButton has a dropdown which sorts the inputted data. Sort 1 sorts customers by the number of items purchased.
Whereas sort 2 sorts the products by sale. Each screen of Seller includes two JButtons knows as LogOut and Return Home.

The first screen is the Create Page screen which has various Text Fields under it. The Text Fields include Product Name, Product Description,
Product Price, Product Stock, Product ID and Store ID. Additionally it includes another JButton called the Create Product Button.
Next up the Edit Page includes a Text Field for Product ID displayed in the form of Enter Button. The Edit Page in turn has a Product ID page
which has various text fields such as New Name, New Store ID, New Description, New Quantity(Stock) and New Price. Finally it additionally has a JButton called the Update Button to update the changes made.
The Sales Page has a five labels under it. These labels include Product Name, Customer Email, Customer ID, Revenue from Sale and Store Name.
The Dashboard page sorts the data in two possible ways. The sort 1 sorts stores by Customer ID. Sort 2 sorts stores by Product ID. Finally the Cart Page has a listof Customers and information about all the products in their carts.

### Back-End
Each class in the backend individually ensures its data persists.
Aside from those fields mentioned, every class:
- includes a documentReference field and a CollectionReference object (Generally, getter and setter methods do not exist for these fields.)
- has a constructor that takes every field other than these two as parameters,
- has a constructor that take a QueryDocumentSnapshot as a parameter for initializing a pre-existing object from the database.
  Each class also has methods for:
- create<Object> (such as createCustomer(<params>))
- getting an object from the remote database using its ID (e.g. getCustomerById)
- sorting a given collection of such objects, with or without filtering deleted ones (e.g. sortNonDeletedCarts(String field, Direction direction), sortCarts(String field, Direction direction))
- and getting the ID of the next such object in its collection in the remote database (last ID plus one).
</a>

Finally, all 6 classes also have toString() and equals() methods.


#### Customer & Seller
The Customer and Seller classes, which are similar, both have the fields email, password, isDeleted, and products. In addition, Customer has customerId, and cart; Seller has name, sellerId, and sales. Both classes have constructors that take parameters of all these fields. Cart in Customer is the only field without a setter (as it is modified in the Cart class).
Both of these classes have the functionality to find users from the database by their email, and marking the current user as deleted. In addition, the Customer class can be used to add a product to a customer's purchase history. The Seller class can also be used to add to their sales, and add or remove products from their range of products.

#### Store
The Store class has the fields storeId, name, isDeleted, storeProducts, and storeCustomers.
It has the functionality to
- get a store's documentReference,
- and includes a centralized method that can be used to update the database with any changes.

#### Cart
The Cart class has the fields customerId, isDeleted, cartProducts, cartsCollection and documentReference.
it has the functionality to
- purchase a cart, get a non-deleted by its ID,
- get multiple carts (with or without the filter of including deleted ones).

#### Product
The Product class has the fields productId, name, quantity, description, price, sellerId, storeId, and isDeleted.
It has the functionality to
- fetch the document of the current instance of the product from the remote database,
- and fetch multiple or one product on the basis of their/its name(/s) or description(/s) (with or without including deleted products).

#### Sale
The Sale class has the fields saleId, customerId, productId, cost, numPurchased, and isDeleted.
It has the functionality to fetch the document of the current instance of the sale from the remote database and calculate the total price of all products in the sale.

### Testing
For testing, we used the Junit testing framework for testing and created helper ```@Before``` and ```@After``` methods in TestUtils which we then extended into the rest of the test classes. We wrote test cases for almost all our methods, located in the test directory in src. All 63 of them pass.
We utilized the FireStore emulator so as to keep the actual data safe, and we clear and reinitialize the emulator before each test class.
We also wrote an integration test for the whole application.

## utils
### Fields
This class has only static strings that can be used to query FireStore:
- fields.customerId --> customerId
- fields.customerIds --> customerIds
- fields.sellerId --> sellerId
- fields.email --> email
- fields.password --> password
- fields.description --> description
- fields.name --> name
- fields.price --> price
- fields.quantity --> quantity
- fields.productId --> productId
- fields.saleIds --> saleIds
- fields.saleId --> saleId
- fields.productIds --> productIds
- fields.storeId --> storeId
- fields.numPurchased --> numPurchased
- fields.cost --> cost
- fields.isDeleted --> isDeleted

### ValidateInterface
This is an interface for designing methods that can be used to validate input by the user.

### AuthenticationType
This class contains the Enums LOGIN and CREATE, for logging in and creating an account, respectively.



