package com.epam.auction.command;


import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * The class is responsible for localization of the pages
 */
public class ChangeLangCommand implements ActionCommand {

    /**
     * Changes session local attribute
     *
     * @param request from client
     * @return current page path
     */
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        session.setAttribute(Info.ATTRIBUTE_NOT_MONEY, null);
        session.setAttribute(Info.ATTRIBUTE_BAN, null);

        String local = request.getParameter(Info.LOCAL);

        String page = request.getParameter(Info.CONTEXT_PATH);

        session.setAttribute(Info.LOCAL, local);

        return page;

    }

}
