package com.epam.auction.dao;

import com.epam.auction.dao.creator.entityCreator.AuctionTypeCreator;
import com.epam.auction.dao.creator.statementCreator.AuctionTypeStatementCreator;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.AuctionType;

import java.util.List;

/**
 * Realization of the CRUD operations for AuctionType entity
 */
public class AuctionTypeDao extends AbstractDaoHelper<AuctionType> {

    /**
     * All needed queries
     */
    private static final String SELECT_ALL_TYPES = "SELECT `id_type`, `type` FROM `auction_type`;";
    private static final String SELECT_TYPE_BY_ID = "SELECT `id_type`, `type` FROM `auction_type` WHERE `id_type`=?;";
    private static final String INSERT_NEW_TYPE = "INSERT INTO `auction_type` (`type`) VALUES (?);";

    public AuctionTypeDao() {
        super(new AuctionTypeStatementCreator(), new AuctionTypeCreator());
    }

    @Override
    public List<AuctionType> findAll() throws DAOException {
        return findAll(SELECT_ALL_TYPES);
    }

    @Override
    public AuctionType findEntityById(Integer id) throws DAOException {
        return findEntityById(id, SELECT_TYPE_BY_ID);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return false;
    }

    @Override
    public boolean create(AuctionType entity) throws DAOException {
        return create(entity, INSERT_NEW_TYPE);
    }

    @Override
    public AuctionType update(AuctionType entity) throws DAOException {
        return entity;
    }
}
