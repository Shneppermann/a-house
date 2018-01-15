package com.epam.auction.command;

import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;

/**
 * Class is responsible for initializing login page.
 */
public class LoginPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request){

        return ConfigurationManager.getProperty(Info.LOGIN_PAGE);

    }

}
