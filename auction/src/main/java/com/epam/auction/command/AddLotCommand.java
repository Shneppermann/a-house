package com.epam.auction.command;

import com.epam.auction.exceptions.CommandException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;

import javax.servlet.http.HttpServletRequest;


public class AddLotCommand implements ActionCommand {


    /**
     * Method of getting the path to the add lot page
     *
     * @param request client request
     * @return path to the add lot page
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        return ConfigurationManager.getProperty(Info.ADD_LOT_PAGE);
    }

}
