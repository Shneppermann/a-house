package com.epam.auction.command;

import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.UserManageService;
import com.epam.auction.service.creator.BaseListCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class is responsible for initializing user manage page.
 */

public class UserManageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(UserManageCommand.class);
    private UserManageService service;
    private BaseListCreator<UserDto, User> creator;

    public UserManageCommand(UserManageService service, BaseListCreator<UserDto, User> creator) {
        this.service = service;
        this.creator = creator;
    }

    /**
     * The method tries to set correct session attributes.
     *
     * @param request from client
     * @return path to the user manage page
     * @throws CommandException when any {@link LogicException} occurred.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        String page;
        try {

            User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);
            int idUser = user.getId();

            List<User> users = service.getUserList(idUser);
            List<UserDto> usersDto = creator.createListDto(users);

            session.setAttribute(Info.ATTRIBUTE_USER_LIST, usersDto);

        } catch (LogicException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new CommandException(exception.getMessage(), exception);
        }
        page = ConfigurationManager.getProperty(Info.USER_MANAGE_PAGE);
        return page;
    }
}
