# Amazeon
Amazon watch out, there's a new competitor on the block! This is our submission for option 3 of Purdue's CS 180 Project 4 and 5
> Report, Video, & Code submitted by TODO

## TODO
- Compile into one executable

## Client Dependencies
Just Java :)
> ok maybe not just java if you want to compile and run from source; just java if you want to run the built jar file; maven will handle a lot of the process for you though

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


## Documentation
- frontend
- backend
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
### Testing

