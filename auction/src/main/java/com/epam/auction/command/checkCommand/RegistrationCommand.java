package com.epam.auction.command.checkCommand;

import com.epam.auction.resource.Info;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class is responsible for initializing registration of the new user.
 */
public class RegistrationCommand extends BaseCheckCommand {

    private RegistrationService service;

    public RegistrationCommand(RegistrationService service){
        this.service = service;
    }


    /**
     * Initializing registration of the new user
     *
     * @param request from the client
     * @return path to the page
     * @throws CommandException when {@link LogicException} occurred
     */
    @Override
    public String execute(HttpServletRequest request)throws CommandException{

        String name = request.getParameter(Info.PARAM_USER_NAME);
        String surname = request.getParameter(Info.PARAM_USER_SURNAME);
        String login = request.getParameter(Info.PARAM_NAME_LOGIN);
        String password = request.getParameter(Info.PARAM_NAME_PASSWORD);

        boolean isCorrectParam = (checkCorrectParameters(name)&&checkCorrectParameters(surname)
                    && checkCorrectParameters(login)&&checkCorrectParameters(password));
        String page = null;
        if(isCorrectParam){
            try {
                boolean isCreate =service.createNewUser(name,surname,login,password);

                HttpSession session = request.getSession(true);
                String local = (String) session.getAttribute(Info.LOCAL);

                ResourceBundle bundle;
                if(local!=null) {
                    Locale locale = new Locale(local);
                    bundle = ResourceBundle.getBundle(Info.MESS_BUNDLE, locale);
                } else{
                    Locale locale = Locale.getDefault();
                    bundle = ResourceBundle.getBundle(Info.MESS_BUNDLE, locale);
                }

                //sets the message to the user and sets the page

                if(isCreate){
                    String message = bundle.getString(Info.MESS_REGISTRATION_SUCCESS);
                    session.setAttribute(Info.ATTRIBUTE_BAN,message);
                    page = ConfigurationManager.getProperty(Info.LOGIN_PAGE);
                } else {
                    String message = bundle.getString(Info.MESS_WRONG_LOGIN);
                    session.setAttribute(Info.ATTRIBUTE_BAN,message);
                    page = ConfigurationManager.getProperty(Info.REGISTRATION_PAGE);
                }

            }catch (LogicException exception){
                throw new CommandException(exception.getMessage(),exception);
            }
        }
        return page;
    }

}
