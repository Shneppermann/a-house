package com.epam.auction.dao.creator.entityCreator;

import com.epam.auction.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates Role entity from result set
 */
public class RoleCreator extends AbstractEntityCreator<Role> {

    /**
     * Columns names
     */
    private static final String COLUMN_ID = "id_role";
    private static final String COLUMN_ROLE_NAME = "role_name";


    @Override
    public Role createElement(ResultSet resultSet) throws SQLException {

        Role role = new Role();

        int roleId = resultSet.getInt(COLUMN_ID);
        role.setId(roleId);

        String roleName = resultSet.getString(COLUMN_ROLE_NAME);
        role.setRoleName(roleName);

        return role;
    }
}
