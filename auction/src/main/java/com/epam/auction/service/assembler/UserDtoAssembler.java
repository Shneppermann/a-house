package com.epam.auction.service.assembler;

import com.epam.auction.dao.BaseDao;
import com.epam.auction.entity.Role;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;

import java.math.BigDecimal;

/**
 * Class assembles an user dto objects
 */
public class UserDtoAssembler extends AbstractAssembler<User, UserDto> {

    private BaseDao<Role> roleDao;

    public UserDtoAssembler(BaseDao<Role> roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * Creates user DTO objects
     *
     * @param user simple user entity
     * @return UserDto object
     * @throws LogicException when {@link DAOException} occurred
     */
    @Override
    public UserDto createObjectDTO(User user) throws LogicException {
        UserDto userDTO = new UserDto();

        int userId = user.getId();
        userDTO.setId(userId);

        String userLogin = user.getLogin();
        userDTO.setLogin(userLogin);

        String userName = user.getName();
        userDTO.setName(userName);

        String userSurname = user.getSurname();
        userDTO.setSurname(userSurname);

        BigDecimal userBalance = user.getBalance();
        userDTO.setBalance(userBalance);

        try {
            int idRole = user.getIdRole();
            Role role = roleDao.findEntityById(idRole);

            String userRole = role.getRoleName();
            userDTO.setRole(userRole);

        } catch (DAOException exception) {
            throw new LogicException(exception.getMessage(), exception);
        }
        return userDTO;
    }
}
