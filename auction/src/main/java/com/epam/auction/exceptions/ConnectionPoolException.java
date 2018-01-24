package com.epam.auction.exceptions;

/**
 * Connection pool exceptions occur in connection pool class
 */

public class ConnectionPoolException extends AuctionException {

    private static final long serialVersionUID = -4901022801759566667L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
