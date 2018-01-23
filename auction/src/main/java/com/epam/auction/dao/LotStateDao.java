package com.epam.auction.dao;

import com.epam.auction.dao.creator.entityCreator.LotStateCreator;
import com.epam.auction.dao.creator.statementCreator.LotStateStatementCreator;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.LotState;

import java.util.List;

/**
 * Realization of the CRUD operations for LotState entity
 */
public class LotStateDao extends AbstractDaoHelper<LotState> {

    /**
     * All needed queries
     */

    private static final String SELECT_ALL_STATES = "SELECT `id_state`, `state` FROM `lot_state`;";
    private static final String SELECT_STATE_BY_ID = "SELECT `id_state`, `state` FROM `lot_state` WHERE `id_state`=?;";
    private static final String INSERT_NEW_STATE = "INSERT INTO `lot_state` (`state`) VALUES (?);";

    public LotStateDao() {
        super(new LotStateStatementCreator(), new LotStateCreator());
    }

    @Override
    public List<LotState> findAll() throws DAOException {
        return findAll(SELECT_ALL_STATES);
    }

    @Override
    public LotState findEntityById(Integer id) throws DAOException {
        return findEntityById(id, SELECT_STATE_BY_ID);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public boolean create(LotState entity) throws DAOException {
        return create(entity, INSERT_NEW_STATE);
    }

    @Override
    public LotState update(LotState entity) throws DAOException {
        return entity;
    }

}
