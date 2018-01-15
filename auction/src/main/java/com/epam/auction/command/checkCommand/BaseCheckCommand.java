package com.epam.auction.command.checkCommand;

import com.epam.auction.command.ActionCommand;
import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseCheckCommand implements ActionCommand {

    /**
     * Gets parameters from the request
     * @param request client request
     * @param paramName name of the parameter
     * @return {@link String} parameter
     */
    protected String getParam(HttpServletRequest request, String paramName){
        return request.getParameter(paramName);
    }

    /**
     * Checks that the string is not empty
     * @param parameter {@link String}
     * @return true, if parameter is not empty
     */
    protected boolean checkCorrectParameters(String parameter){
        return (!Info.EMPTY_STRING.equals(parameter));
    }
}
