package com.epam.auction.service;

import com.epam.auction.dao.RoleDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.Role;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class responsible for editing the user
 */
public class UserEditService {

    private static final Logger LOGGER = LogManager.getLogger(UserEditService.class);
    private static final String CHANGE_USER = "User role was changed ";

    private static final int EMPTY_ROLE = 0;

    private UserDao userDao;
    private RoleDao roleDao;

    public UserEditService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    /**
     * The method tries to change user role
     *
     * @param userDto   edited user
     * @param newRoleId new role of the user
     * @return result of the editing
     * @throws LogicException when {@link DAOException} occurred
     */

    public boolean changeUserRole(UserDto userDto, String newRoleId) throws LogicException {
        int userId = userDto.getId();
        boolean isChanged = false;
        try {
            User user = userDao.findEntityById(userId);
            int changedUserRole = getUserRoleId(newRoleId);
            if (changedUserRole != EMPTY_ROLE) {
                user.setIdRole(changedUserRole);
                user = userDao.update(user);
                if (user != null) {
                    isChanged = true;
                    LOGGER.info(CHANGE_USER + userId);
                }
            }
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return isChanged;

    }

    /**
     * Searching id of the new user role
     *
     * @param newRoleId new role of the user
     * @return id of the new user role
     * @throws DAOException when {@link DAOException} occurred
     */
    private int getUserRoleId(String newRoleId) throws DAOException {
        List<Role> roles = roleDao.findAll();
        int roleId = EMPTY_ROLE;
        for (Role role : roles) {
            String roleName = role.getRoleName();
            if (roleName.equals(newRoleId)) {
                roleId = role.getId();
            }
        }
        return roleId;
    }

}
