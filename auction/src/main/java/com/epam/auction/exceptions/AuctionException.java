package com.epam.auction.exceptions;

/**
 * Auction exception is superclass of all business exceptions in the application
 */

public class AuctionException extends Exception {

    private static final long serialVersionUID = -6889067181088596377L;

    public AuctionException() {
        super();
    }

    public AuctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuctionException(String message) {
        super(message);
    }

    public AuctionException(Throwable cause) {
        super(cause);
    }
}
