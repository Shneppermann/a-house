package com.epam.auction.dao.creator.statementCreator;

import com.epam.auction.entity.Role;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets the parameters in the PreparedStatement for Role entity
 */
public class RoleStatementCreator extends AbstractStatementCreator<Role> {

    @Override
    public void createInsertStatement(Role entity, PreparedStatement statement) throws SQLException {
        String roleName = entity.getRoleName();
        statement.setString(1, roleName);

    }

    @Override
    public void createUpdateStatement(Role entity, PreparedStatement statement) throws SQLException {

        String roleName = entity.getRoleName();
        statement.setString(1, roleName);

        int idRole = entity.getId();
        statement.setInt(2, idRole);
    }
}
