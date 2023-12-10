# Amazeon
Amazon watch out, there's a new competitor on the block! This is our submission for option 3 of Purdue's CS 180 Project 4 and 5
> Report & Video submitted by Shlok, Code submitted by Neha

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
- [Customer.java](DOCS.md#Customer)
- [Seller.java](DOCS.md#Seller)
- [Store.java](DOCS.md#Store)
- [Product.java](DOCS.md#Product)
- [Sale.java](DOCS.md#Sale)
- [Cart.java](DOCS.md#Cart)
- [Utils.java](DOCS.md#Utils)
- [ValidateInterface.java](DOCS.md#ValidateInterface)
- [Testing](DOCS.md#Testing)
