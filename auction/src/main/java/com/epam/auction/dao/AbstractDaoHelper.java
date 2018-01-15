package com.epam.auction.dao;


import com.epam.auction.connection.ConnectionPool;
import com.epam.auction.dao.creator.entityCreator.AbstractEntityCreator;
import com.epam.auction.dao.creator.statementCreator.AbstractStatementCreator;
import com.epam.auction.exceptions.ConnectionPoolException;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with realization CRUD operations
 *
 * @param <T> entity
 */
public abstract class AbstractDaoHelper<T extends Entity> implements BaseDao<T> {

    protected AbstractEntityCreator<T> entityCreator;
    protected AbstractStatementCreator<T> statementCreator;

    public AbstractDaoHelper(AbstractStatementCreator<T> statementCreator, AbstractEntityCreator<T> entityCreator) {
        this.entityCreator = entityCreator;
        this.statementCreator = statementCreator;
    }

    /**
     * Find all entities
     *
     * @param query sql query
     * @return List of entities
     * @throws DAOException when SQLException or ConnectionPoolException occurred
     */
    protected List<T> findAll(String query) throws DAOException {
        List<T> entities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T element = entityCreator.createElement(resultSet);
                entities.add(element);
            }
        } catch (SQLException | ConnectionPoolException exception) {
            throw new DAOException(exception.getMessage(), exception.getCause());
        } finally {
            close(resultSet, statement, connection);
        }
        return entities;
    }

    /**
     * Find entity by id
     *
     * @param id    of the entity
     * @param query sql query
     * @return entity
     * @throws DAOException when SQLException or ConnectionPoolException occurred
     */

    protected T findEntityById(Integer id, String query) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T entity = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = entityCreator.createElement(resultSet);
            }
        } catch (SQLException | ConnectionPoolException exception) {
            throw new DAOException(exception.getMessage(), exception.getCause());
        } finally {
            close(resultSet, statement, connection);
        }
        return entity;
    }

    /**
     * Delete entity by id
     *
     * @param id    of the entity
     * @param query sql query
     * @return result of the delete
     * @throws DAOException when SQLException or ConnectionPoolException occurred
     */
    protected boolean delete(Integer id, String query) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isDelete = false;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int changedRows = statement.executeUpdate();
            isDelete = (changedRows == 1);
        } catch (SQLException | ConnectionPoolException exception) {
            throw new DAOException(exception.getMessage(), exception.getCause());
        } finally {
            close(null, statement, connection);
        }
        return isDelete;
    }

    /**
     * Create new entity in database
     *
     * @param entity new entity
     * @param query  sql query
     * @return result of the create
     * @throws DAOException when SQLException or ConnectionPoolException occurred
     */
    protected boolean create(T entity, String query) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isCreate = false;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statementCreator.createInsertStatement(entity, statement);
            int changedRows = statement.executeUpdate();
            isCreate = (changedRows == 1);
        } catch (SQLException | ConnectionPoolException exception) {
            throw new DAOException(exception.getMessage(), exception.getCause());
        } finally {
            close(null, statement, connection);
        }
        return isCreate;
    }

    /**
     * Change entity in the database
     *
     * @param entity with new state
     * @param query  sql query
     * @return changed entity
     * @throws DAOException hen SQLException or ConnectionPoolException occurred
     */
    protected T update(T entity, String query) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        T updatedEntity = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statementCreator.createUpdateStatement(entity, statement);
            int changedRows = statement.executeUpdate();
            if (changedRows == 1) {
                updatedEntity = findEntityById(entity.getId());
            }
        } catch (SQLException | ConnectionPoolException exception) {
            throw new DAOException(exception.getMessage(), exception.getCause());
        } finally {
            close(null, statement, connection);
        }
        return updatedEntity;
    }

    /**
     * Tries to get connection from connection pool
     *
     * @return connection
     * @throws ConnectionPoolException when ConnectionPollException occurred
     */
    protected Connection getConnection() throws ConnectionPoolException {
        ConnectionPool pool = ConnectionPool.getInstance();
        return pool.getConnection();
    }

    /**
     * Tries to close result set, statement and connection
     *
     * @param resultSet  result set
     * @param statement  statement
     * @param connection connection
     * @throws DAOException when any SQLException occurred
     */
    protected void close(ResultSet resultSet, Statement statement, Connection connection) throws DAOException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage(), e.getCause());
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage(), e.getCause());
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage(), e.getCause());
            }
        }

    }
}
