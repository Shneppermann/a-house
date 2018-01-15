package com.epam.auction.command.checkCommand;

import com.epam.auction.resource.Info;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.DirectService;
import com.epam.auction.service.LoginService;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.service.UserManageService;
import com.epam.auction.service.creator.BaseListCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class is responsible for entering the auction
 */
public class LoginCommand extends BaseCheckCommand {

    private DirectService directService;
    private UserManageService manageService;
    private LoginService loginService;
    private BaseListCreator<UserDto, User> userCreator;
    private BaseListCreator<LotDto, Lot> lotsCreator;

    public LoginCommand(DirectService directService, UserManageService manageService, LoginService loginService,
                        BaseListCreator<UserDto, User> userCreator, BaseListCreator<LotDto, Lot> lotsCreator) {
        this.directService = directService;
        this.manageService = manageService;
        this.loginService = loginService;
        this.userCreator = userCreator;
        this.lotsCreator = lotsCreator;
    }


    /**
     * Initializes verification of user-entered password and login
     *
     * @param request from client
     * @return path to the page
     * @throws CommandException when {@link LogicException} occurred
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String login = getParam(request, Info.PARAM_NAME_LOGIN);
        String password = getParam(request, Info.PARAM_NAME_PASSWORD);

        boolean isCorrectParameter = (checkCorrectParameters(login) && checkCorrectParameters(password));

        HttpSession session = request.getSession(true);

        String local = request.getParameter(Info.LOCAL);
        session.setAttribute(Info.LOCAL,local);
        Locale locale = new Locale(local);
        ResourceBundle bundle = ResourceBundle.getBundle(Info.MESS_BUNDLE, locale);

        String page = null;
        if (isCorrectParameter) {
            try {
                page = getPage(login, password, request);
                String loginPage =ConfigurationManager.getProperty(Info.LOGIN_PAGE);

                if(page.equals(loginPage)){
                    String message = bundle.getString(Info.MESS_INCORRECT_LOGIN_OR_PASS);
                    session.setAttribute(Info.ATTRIBUTE_BAN,message);
                }

            } catch (LogicException exception) {
                throw new CommandException(exception.getMessage(), exception);
            }
        } else {
            String message = bundle.getString(Info.MESS_INCORRECT_LOGIN_OR_PASS);
            session.setAttribute(Info.ATTRIBUTE_BAN,message);
            page = ConfigurationManager.getProperty(Info.LOGIN_PAGE);
        }
        return page;
    }

    /**
     * Returns page
     *
     * @param login    of the user
     * @param password of the user
     * @param request  from client
     * @return path to the page
     * @throws LogicException when {@link LogicException} occurred
     */
    private String getPage(String login, String password, HttpServletRequest request) throws LogicException {

        User user = loginService.getUser(login, password);

        String page = null;
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(Info.ATTRIBUTE_USER, user);

            boolean isAdmin = loginService.isAdmin(user);

            int idUser = user.getId();

            if (isAdmin) {

                List<User> users = manageService.getUserList(idUser);

                List<UserDto> usersDto = userCreator.createListDto(users);

                session.setAttribute(Info.ATTRIBUTE_USER_LIST, usersDto);

                page = ConfigurationManager.getProperty(Info.USER_MANAGE_PAGE);

            } else {

                List<Lot> lots = directService.getDirectLotList(idUser);

                List<LotDto> actualLots = lotsCreator.createListDto(lots);

                session.setAttribute(Info.ATTRIBUTE_LIST, actualLots);

                page = ConfigurationManager.getProperty(Info.CUSTOMER_PAGE);
            }

        } else {
            page = ConfigurationManager.getProperty(Info.LOGIN_PAGE);
        }
        return page;
    }

}
