package com.epam.auction.command;

import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class returns path to the previous page
 */
public class BackCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        String page = (String) session.getAttribute(Info.PARAM_REDIRECT_PAGE);
        return page;
    }
}
