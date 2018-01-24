package com.epam.auction.service;


import com.epam.auction.dao.BaseDao;
import com.epam.auction.entity.Role;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.encoding.ShaStringCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class responsible for user search by username and password, and for checking user roles
 */
public class LoginService {

    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);
    private final static String ADMIN = "Admin";

    private BaseDao<User> userDao;
    private BaseDao<Role> roleDao;

    public LoginService(BaseDao<User> userDao, BaseDao<Role> roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }


    /**
     * The method searches user in the database
     *
     * @param enteredLogin login of the user
     * @param enteredPass  password of the user
     * @return {@link User} entity
     * @throws LogicException when {@link DAOException} occurred
     */
    public User getUser(String enteredLogin, String enteredPass) throws LogicException {
        List<User> users;
        User user = null;
        try {
            users = userDao.findAll();
            String shaEnterPass = ShaStringCreator.sha(enteredPass);
            for (User element : users) {
                String login = element.getLogin();
                String password = element.getPassword();
                if (enteredLogin.equals(login) && shaEnterPass.equals(password)) {
                    user = element;
                    break;
                }
            }
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return user;
    }

    /**
     * Checks the user's role
     *
     * @param user checked user
     * @return result of the checking
     * @throws LogicException when {@link DAOException} occurred
     */

    public boolean isAdmin(User user) throws LogicException {

        int roleId = user.getIdRole();
        Role role;
        try {
            role = roleDao.findEntityById(roleId);
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        boolean isAdmin = false;
        if (role != null) {
            String roleName = role.getRoleName();
            if (ADMIN.equals(roleName)) {
                isAdmin = true;
            }
        }

        return isAdmin;
    }

}
