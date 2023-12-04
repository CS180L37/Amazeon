package utils;

public class fieldNameEnums {
    enum carts {
        customerId,
        productIds
    }
    enum customers {
        cartId,
        customerId,
        email,
        password,
        productIds;
    }
    enum products {
        description,
        name,
        price,
        productId,
        quantity,
        sellerId,
        storeId;
    }

    enum sales {
        cost,
        customerId,
        numPurchased,
        productId,
        saleId;
    }

    enum sellers {
        email,
        name,
        password,
        productIds,
        saleIds,
        sellerId;
    }

    enum stores {
        customerIds,
        name,
        productIds,
        storeId;
    }
}