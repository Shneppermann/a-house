package com.epam.auction.dao.creator.entityCreator;

import com.epam.auction.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Creates User entity from result set
 */

public class UserCreator extends AbstractEntityCreator<User> {

    /**
     * Columns names
     */
    private static final String COLUMN_ID = "id_user";
    private static final String COLUMN_ROLE = "id_role";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_SECOND_NAME = "second_name";
    private static final String COLUMN_BALANCE = "balance";

    @Override
    public User createElement(ResultSet resultSet) throws SQLException {

        User user = new User();

        int idUser = resultSet.getInt(COLUMN_ID);
        user.setId(idUser);

        int idRole = resultSet.getInt(COLUMN_ROLE);
        user.setIdRole(idRole);

        String login = resultSet.getString(COLUMN_LOGIN);
        user.setLogin(login);

        String password = resultSet.getString(COLUMN_PASSWORD);
        user.setPassword(password);

        String name = resultSet.getString(COLUMN_FIRST_NAME);
        user.setName(name);

        String surname = resultSet.getString(COLUMN_SECOND_NAME);
        user.setSurname(surname);

        BigDecimal balance = resultSet.getBigDecimal(COLUMN_BALANCE);
        user.setBalance(balance);

        return user;
    }
}
