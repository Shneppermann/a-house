package com.epam.auction.command;

import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.UserEditService;
import com.epam.auction.service.UserManageService;
import com.epam.auction.service.creator.UserListCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class is responsible for initializing update of the users.
 */
public class UserEditCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(UserEditCommand.class);
    private static final String ERROR_MESSAGE = "Error! User role is not updated";

    private UserEditService editService;
    private UserManageService manageService;
    private UserListCreator creator;

    public UserEditCommand(UserEditService editService, UserManageService manageService, UserListCreator creator) {
        this.editService = editService;
        this.manageService = manageService;
        this.creator = creator;
    }

    /**
     * The method tries to update user
     *
     * @param request from client
     * @return path to the user manage page
     * @throws CommandException when any {@link LogicException} occurred.
     */

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String newRole = request.getParameter(Info.PARAM_NEW_USER_ROLE);
        HttpSession session = request.getSession(true);
        UserDto changedUser = (UserDto) session.getAttribute(Info.ATTRIBUTE_EDITED_USER);
        String page;
        try {
            boolean isChange = editService.changeUserRole(changedUser, newRole);
            if (!isChange) {
                LOGGER.error(ERROR_MESSAGE);
                throw new CommandException(ERROR_MESSAGE);
            }

            User admin = (User) session.getAttribute(Info.ATTRIBUTE_USER);
            int adminId = admin.getId();

            List<User> users = manageService.getUserList(adminId);
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
