package com.epam.auction.dao.creator.statementCreator;


import com.epam.auction.entity.User;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets the parameters in the PreparedStatement for User entity
 */
public class UserStatementCreator extends AbstractStatementCreator<User> {

    @Override
    public void createInsertStatement(User entity, PreparedStatement statement) throws SQLException {
        createStatement(entity, statement);
    }

    @Override
    public void createUpdateStatement(User entity, PreparedStatement statement) throws SQLException {

        createStatement(entity, statement);

        int idUser = entity.getId();
        statement.setInt(7, idUser);

    }

    private void createStatement(User entity, PreparedStatement statement) throws SQLException {
        int idRole = entity.getIdRole();
        statement.setInt(1, idRole);

        String login = entity.getLogin();
        statement.setString(2, login);

        String password = entity.getPassword();
        statement.setString(3, password);

        String userName = entity.getName();
        statement.setString(4, userName);

        String userSurname = entity.getSurname();
        statement.setString(5, userSurname);

        BigDecimal balance = entity.getBalance();
        statement.setBigDecimal(6, balance);
    }
}
