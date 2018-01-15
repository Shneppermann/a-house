package com.epam.auction.command;

import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class responsible for processing the logout command
 */

public class LogoutCommand implements ActionCommand {

    /**
     * The method invalidate user session
     *
     * @param request from client
     * @return path of the index page
     */

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(Info.INDEX_PAGE);
        HttpSession session = request.getSession();
        session.invalidate();
        return page;
    }
}
