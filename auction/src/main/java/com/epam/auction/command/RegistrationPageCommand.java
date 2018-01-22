package com.epam.auction.command;

import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class is responsible for initializing login page.
 */
public class RegistrationPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        session.invalidate();
        return ConfigurationManager.getProperty(Info.REGISTRATION_PAGE);

    }
}
