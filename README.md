# Amazeon
Amazon watch out, there's a new competitor on the block! This is our submission for option 3 of Purdue's CS 180 Project 4 and 5

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

Or you could just watch the process in action like so:
![](https://github.com/CS180L37/Project4-5/blob/master/src/test/resources/TestCases.mp4)

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
- [ValidInterface.java](DOCS.md#ValidInterface)
- [Testing](DOCS.md#Testing)
