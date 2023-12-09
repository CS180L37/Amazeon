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

### Front-End
### Back-End
Each class in the backend individually ensures its data persists.
Aside from those fields mentioned, every class includes a documentReference field and a CollectionReference object. Generally, getter and setter methods do not exist for these fields. There are generally constructors that take every field other than these two as parameters, and contructors that take a QueryDocumentSnapshot as a parameter for initializing a pre-existing object from the database.
There are also create<Object> methods (such as createCustomer(<params>)) for every class, as also methods for getting an object from the remote database using its ID (e.g. getCustomerById), sorting a given collection of such objects, with or without filtering deleted ones (e.g. sortNonDeletedCarts(String field, Direction direction), sortCarts(String field, Direction direction)), and getting the ID of the next such object in its collection in the remote database (last ID plus one).
Finally, all 6 classes also have toString() and equals() methods.
#### Customer & Seller
The Customer and Seller classes, which are similar, both have the fields email, password, isDeleted, and products. In addition, Customer has customerId, and cart; Seller has name, sellerId, and sales. Both classes have constructors that take parameters of all these fields. Cart in Customer is the only field without a setter (as it is modified in the Cart class).
Both of these classes have the functionality to find users from the database by their email, and marking the current user as deleted. In addition, the Customer class can be used to add a product to a customer's purchase history. The Seller class can also be used to add to their sales, and add or remove products from their range of products.
#### Store
The Store class has the fields storeId, name, isDeleted, storeProducts, and storeCustomers.
It has the functionality to get a store's documentReference, and includes a centralized method that can be used to update the database with any changes.
#### Cart
The Cart class has the fields customerId, isDeleted, cartProducts, cartsCollection and documentReference.
it has the functionality to purchase a cart, get a non-deleted by its ID, get multiple carts (with or without the filter of including deleted ones).
#### Product
The Product class has the fields productId, name, quantity, description, price, sellerId, storeId, and isDeleted.
It has the functionality to fetch the document of the current instance of the product from the remote database, and fetch multiple or one product on the basis of their/its name(/s) or description(/s), with or without including deleted products.
#### Sale
The Sale class has the fields saleId, customerId, productId, cost, numPurchased, and isDeleted.
It has the functionality to fetch the document of the current instance of the sale from the remote database and calculate the total of 
### Testing
For testing, we made test cases for every method and they all pass.
### Utils
We have a field of static strings that can be used to query FireStore (such as fields.storeId which represents "storeId"), and some Enums.
We also have functionality to validate any input by the user, and methods to initialize FireStore and read, write and retrieve data.