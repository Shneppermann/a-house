package com.epam.auction.dao;

import com.epam.auction.dao.creator.entityCreator.RoleCreator;
import com.epam.auction.dao.creator.statementCreator.RoleStatementCreator;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Role;

import java.util.List;

/**
 * Realization of the CRUD operations for Role entity
 */
public class RoleDao extends AbstractDaoHelper<Role> {

    /**
     * All needed queries
     */

    private static final String SELECT_ALL_ROLES = "SELECT `id_role`, `role_name` FROM `role`;";
    private static final String SELECT_ROLE_BY_ID = "SELECT `id_role`, `role_name` FROM `role` WHERE `id_role`=?;";
    private static final String INSERT_NEW_ROLE = "INSERT INTO `role`  (`role_name`) VALUES (?);";

    public RoleDao() {
        super(new RoleStatementCreator(), new RoleCreator());
    }

    @Override
    public List<Role> findAll() throws DAOException {
        return findAll(SELECT_ALL_ROLES);
    }

    @Override
    public Role findEntityById(Integer id) throws DAOException {
        return findEntityById(id, SELECT_ROLE_BY_ID);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Role entity) throws DAOException {
        return create(entity, INSERT_NEW_ROLE);
    }

    @Override
    public Role update(Role entity) throws DAOException {
        return entity;
    }
}
