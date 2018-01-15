package com.epam.auction.dao.creator.statementCreator;

import com.epam.auction.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets the parameters in the PreparedStatement
 *
 * @param <K> entity
 */
public abstract class AbstractStatementCreator<K extends Entity> {

    /**
     * Sets the parameters in the PreparedStatement for inserting
     *
     * @param entity    inserted entity
     * @param statement prepared statement
     * @throws SQLException when it occurred
     */
    public abstract void createInsertStatement(K entity, PreparedStatement statement) throws SQLException;

    /**
     * Sets the parameters in the PreparedStatement for updating
     *
     * @param entity    updated entity
     * @param statement prepared statement
     * @throws SQLException when it occurred
     */
    public abstract void createUpdateStatement(K entity, PreparedStatement statement) throws SQLException;
}
