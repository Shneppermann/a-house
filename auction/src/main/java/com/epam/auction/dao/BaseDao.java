package com.epam.auction.dao;

import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Entity;

import java.util.List;

/**
 * Base DAO interface with CRUD operations
 *
 * @param <T> entity
 */
public interface BaseDao<T extends Entity> {
    List<T> findAll() throws DAOException;

    T findEntityById(Integer id) throws DAOException;

    boolean delete(Integer id) throws DAOException;

    boolean create(T entity) throws DAOException;

    T update(T entity) throws DAOException;
}
