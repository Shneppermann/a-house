package com.epam.auction.command.factory;

import com.epam.auction.command.ActionCommand;
import com.epam.auction.command.EmptyCommand;
import com.epam.auction.command.client.CommandEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * Creates action commands
 */
public class ActionFactory {

    private static final String COMMAND = "command";

    /**
     * Defines and creates new action command
     *
     * @param request from client
     * @return new action command
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }

        CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
        current = currentEnum.getCurrentCommand();
        return current;
    }

}
