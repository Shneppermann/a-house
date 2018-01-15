package com.epam.auction.controller;

import com.epam.auction.command.ActionCommand;
import com.epam.auction.command.factory.ActionFactory;
import com.epam.auction.exceptions.AuctionException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Front controller
 */

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private static final long serialVersionUID = -8124318840483007160L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        try {
            page = command.execute(request);
        } catch (AuctionException exception) {
            LOGGER.error(exception.getMessage() + exception);
            page = ConfigurationManager.getProperty(Info.ERROR_PAGE);
            response.sendRedirect(request.getContextPath() + page);
        }
        response.sendRedirect(request.getContextPath() + page);

    }


}
