package com.epam.auction.service;

import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class is responsible for the actual information about the client
 */

public class ActualBalanceService {

    private static final Logger LOGGER = LogManager.getLogger(ActualBalanceService.class);

    private UserDao userDao;

    public ActualBalanceService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * The method tries to return actual user entity
     *
     * @param userId client ID
     * @return {@link User} entity
     * @throws LogicException when {@link DAOException} occurred.
     */

    public User getActualUserInfo(int userId) throws LogicException {

        User user;
        try {
            user = userDao.findEntityById(userId);
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return user;
    }
}
