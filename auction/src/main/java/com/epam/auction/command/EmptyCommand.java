package com.epam.auction.command;

import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;

/**
 * The class responsible for processing an empty command
 */

public class EmptyCommand implements ActionCommand {

    /**
     * Method of getting the path to the login page
     *
     * @param request from user
     * @return path of the login page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(Info.LOGIN_PAGE);
        return page;
    }
}
