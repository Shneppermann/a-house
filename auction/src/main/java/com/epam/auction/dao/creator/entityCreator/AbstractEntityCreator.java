package com.epam.auction.dao.creator.entityCreator;

import com.epam.auction.entity.Entity;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract entity creator
 *
 * @param <K> entity
 */
public abstract class AbstractEntityCreator<K extends Entity> {

    /**
     * Creates new entity
     *
     * @param resultSet result set
     * @return entity
     * @throws SQLException when it occurred
     */
    public abstract K createElement(ResultSet resultSet) throws SQLException;

}
