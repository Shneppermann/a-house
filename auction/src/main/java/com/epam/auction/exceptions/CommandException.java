package com.epam.auction.exceptions;

/**
 * Command exceptions occur in commands classes
 */

public class CommandException extends AuctionException {

    private static final long serialVersionUID = 4599651298270112754L;

    public CommandException() {
        super();
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
