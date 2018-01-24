package com.epam.auction.service;

import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.math.BigDecimal;
import java.util.List;

/**
 * Tries to create new client user
 */
public class RegistrationService {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationService.class);
    private static final String NEW_REGISTRATION = "New user registration :";
    private static final String SPACE = " ";
    private static final int BANNED_ROLE_ID = 3;
    private static final BigDecimal START_BALANCE = new BigDecimal(0);

    private UserDao userDao;

    public RegistrationService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Tries to create new user entity
     *
     * @param name     of the user
     * @param surname  of the user
     * @param login    user login
     * @param password user password
     * @return result of the operation
     * @throws LogicException when any DAOException occured
     */

    public boolean createNewUser(String name, String surname, String login, String password) throws LogicException {

        boolean isCreate = false;
        try {
            isCreate = checkLogin(login);

            if (isCreate) {
                isCreate = addNewEntity(name, surname, login, password);
                LOGGER.info(NEW_REGISTRATION + name + SPACE + surname + SPACE + login);
            }

        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }

        return isCreate;
    }


    /**
     * Checks the login for a duplicate
     *
     * @param login entered login
     * @return result of the check
     * @throws DAOException if userDao throws DAOException
     */

    private boolean checkLogin(String login) throws DAOException {
        boolean isCorrectLogin = true;
        List<User> users = userDao.findAll();
        for (User user : users) {
            String userLogin = user.getLogin();
            if (userLogin.equals(login)) {
                isCorrectLogin = false;
                break;
            }
        }
        return isCorrectLogin;
    }

    /**
     * Creates a new user
     *
     * @param name     user name
     * @param surname  user surname
     * @param login    user login
     * @param password user password
     * @return result of the operation
     * @throws DAOException when userDao throws DAOException
     */
    private boolean addNewEntity(String name, String surname, String login, String password) throws DAOException {


        User user = new User(BANNED_ROLE_ID, login, password, name, surname, START_BALANCE);
        return userDao.create(user);
    }

}
