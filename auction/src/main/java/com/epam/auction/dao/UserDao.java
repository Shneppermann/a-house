package com.epam.auction.dao;

import com.epam.auction.dao.creator.entityCreator.UserCreator;
import com.epam.auction.dao.creator.statementCreator.UserStatementCreator;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.User;

import java.util.List;

/**
 * Realization of the CRUD operations for User entity
 */
public class UserDao extends AbstractDaoHelper<User> {
    /**
     * All needed queries
     */

    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM `user`;";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE `id_user` = ?;";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `id_user` =?;";
    private static final String UPDATE_USER = "UPDATE `auction_db`.`user` SET `id_role`=?, `login`=?, `password`=?, `first_name`=?, `second_name`=?, `balance`=? WHERE `id_user`=?;";
    private static final String INSERT_NEW_USER = "INSERT INTO `user`  (`id_role`,`login`,`password`,`first_name`,`second_name`,`balance`) VALUES (?,?,SHA(?),?,?,?);";


    public UserDao() {
        super(new UserStatementCreator(), new UserCreator());
    }

    @Override
    public List<User> findAll() throws DAOException {
        return findAll(SQL_SELECT_ALL_USERS);
    }

    @Override
    public User findEntityById(Integer id) throws DAOException {
        return findEntityById(id, SQL_SELECT_USER_BY_ID);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return delete(id, SQL_DELETE_USER_BY_ID);
    }

    @Override
    public boolean create(User entity) throws DAOException {
        return create(entity, INSERT_NEW_USER);
    }

    @Override
    public User update(User entity) throws DAOException {
        return update(entity, UPDATE_USER);
    }

}
