package com.epam.auction.exceptions;

/**
 * DAO exceptions occur in the DAO classes
 */

public class DAOException extends AuctionException{

    private static final long serialVersionUID = -1952738304421113340L;

    public DAOException() {
        super();
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
