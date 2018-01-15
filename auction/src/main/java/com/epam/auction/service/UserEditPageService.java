package com.epam.auction.service;

import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.UserDtoAssembler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class responsible for searching information about edit user
 */
public class UserEditPageService {

    private static final Logger LOGGER = LogManager.getLogger(UserEditPageService.class);

    private UserDao userDao;
    private UserDtoAssembler assembler;

    public UserEditPageService(UserDao userDao, UserDtoAssembler assembler) {
        this.userDao = userDao;
        this.assembler = assembler;
    }

    /**
     * Finds User entity by ID and creates UserDto object
     *
     * @param  userId user id
     * @return {@link UserDto} object
     * @throws LogicException when {@link DAOException} occurred
     */
    public UserDto getChangedUser(int userId) throws LogicException {
        User user = new User();
        try {
            user = userDao.findEntityById(userId);
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage()+exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return assembler.createObjectDTO(user);
    }

}
