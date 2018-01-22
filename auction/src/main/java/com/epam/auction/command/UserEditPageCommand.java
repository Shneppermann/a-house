package com.epam.auction.command;

import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.UserEditPageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class is responsible for initializing user edit page.
 */

public class UserEditPageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(UserEditPageCommand.class);
    private UserEditPageService service;

    public UserEditPageCommand(UserEditPageService service) {
        this.service = service;
    }

    /**
     * The method tries to set correct session attributes.
     *
     * @param request from client
     * @return path to the user edit page if userId parameter is exist,
     * otherwise return path to the user manage page
     * @throws CommandException when any {@link LogicException} occurred.
     */

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String stringUserId = request.getParameter(Info.PARAM_USER_ID);
        String page;
        if (stringUserId != null) {
            int userId = Integer.parseInt(stringUserId);
            UserDto user;
            try {
                user = service.getChangedUser(userId);
            } catch (LogicException exception) {
                LOGGER.error(exception.getMessage(), exception);
                throw new CommandException(exception.getMessage(), exception);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(Info.ATTRIBUTE_EDITED_USER, user);
            page = ConfigurationManager.getProperty(Info.USER_EDIT_PAGE);
        } else {
            page = ConfigurationManager.getProperty(Info.USER_MANAGE_PAGE);
        }
        return page;
    }
}
