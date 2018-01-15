package com.epam.auction.dao;

import com.epam.auction.dao.creator.entityCreator.BidCreator;
import com.epam.auction.dao.creator.statementCreator.BidStatementCreator;
import com.epam.auction.exceptions.ConnectionPoolException;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Bid;


import java.sql.*;
import java.util.List;

/**
 * Realization of the CRUD operations for Bid entity
 */
public class BidDao extends AbstractDaoHelper<Bid> {

    /**
     * All needed queries
     */
    private static final String SELECT_MAX_BID = "SELECT * FROM `bid` WHERE `lot_id` = ? AND `bid`.`bid` = (SELECT MAX(`created_bid`.`bid`) AS `MAX BID`  FROM (SELECT `bid`.`bid` AS `bid` FROM `bid` JOIN `lot` ON `lot`.`id_lot` =`bid`.`lot_id` AND `bid`.`lot_id` = ?) AS `created_bid`);";
    private static final String SELECT_MIN_BID = "SELECT * FROM `bid` WHERE `lot_id` = ? AND `bid`.`bid` = (SELECT MIN(`created_bid`.`bid`) AS `MIN BID`  FROM (SELECT `bid`.`bid` AS `bid` FROM `bid` JOIN `lot` ON `lot`.`id_lot` =`bid`.`lot_id` AND `bid`.`lot_id` = ?) AS `created_bid`);";
    private static final String SELECT_ALL_BIDS = "SELECT `id_bid`, `owner_id`,`lot_id`, `bid`  FROM `bid`;";
    private static final String SELECT_BID_BY_ID = "SELECT * FROM `bid` WHERE `id_bid` =?;";
    private static final String DELETE_BID_BY_ID = "DELETE FROM `bid` WHERE `id_bid` =?;";
    private static final String UPDATE_BID = "UPDATE `auction_db`.`bid` SET `bid`=? WHERE `id_bid`=?;";
    private static final String INSERT_NEW_BID = "INSERT INTO `bid`  (`owner_id`,`lot_id`,`bid`) VALUES (?,?,?);";

    public BidDao() {
        super(new BidStatementCreator(), new BidCreator());
    }

    @Override
    public List<Bid> findAll() throws DAOException {
        return findAll(SELECT_ALL_BIDS);
    }

    @Override
    public Bid findEntityById(Integer id) throws DAOException {
        return findEntityById(id, SELECT_BID_BY_ID);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return delete(id, DELETE_BID_BY_ID);
    }

    @Override
    public boolean create(Bid entity) throws DAOException {
        return create(entity, INSERT_NEW_BID);
    }

    @Override
    public Bid update(Bid entity) throws DAOException {
        return update(entity, UPDATE_BID);
    }

    /**
     * Finds maximum bid by lot id
     *
     * @param lotId lot id
     * @return maximum bid
     * @throws DAOException when it occurred
     */
    public Bid findMaxBidByLotId(int lotId) throws DAOException {
        return getBid(lotId, SELECT_MAX_BID);
    }

    /**
     * Finds minimum bid by lot id
     *
     * @param lotId lot id
     * @return minimum bid
     * @throws DAOException when it occurred
     */
    public Bid findMinBidByLotId(int lotId) throws DAOException {
        return getBid(lotId, SELECT_MIN_BID);
    }

    /**
     * Finds bid entity by lot id and sql query
     *
     * @param lotId lot id
     * @param query sql query
     * @return bid entity
     * @throws DAOException when it occurred
     */
    private Bid getBid(int lotId, String query) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Bid resultBid = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, lotId);
            statement.setInt(2, lotId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultBid = entityCreator.createElement(resultSet);
            }
        } catch (SQLException | ConnectionPoolException exception) {
            throw new DAOException(exception.getMessage(), exception.getCause());
        } finally {
            close(resultSet, statement, connection);
        }
        return resultBid;
    }

}
