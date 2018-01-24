package com.epam.auction.exceptions;

/**
 * Logic exceptions occur in services classes
 */

public class LogicException extends AuctionException {

    private static final long serialVersionUID = 2268792504217342933L;

    public LogicException() {
        super();
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }
}
