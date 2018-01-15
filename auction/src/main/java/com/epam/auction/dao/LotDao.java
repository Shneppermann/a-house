package com.epam.auction.dao;

import com.epam.auction.dao.creator.entityCreator.LotCreator;
import com.epam.auction.dao.creator.statementCreator.LotStatementCreator;
import com.epam.auction.exceptions.ConnectionPoolException;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Lot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Realization of the CRUD operations for Lot entity
 */
public class LotDao extends AbstractDaoHelper<Lot> {

    /**
     * All needed queries
     */
    private static final String SELECT_ALL_LOTS = "SELECT `id_lot`, `lot_state`, `owner_id`,`auction_type`, `lot_name`, `step`, `start_price` FROM `lot`;";
    private static final String SELECT_LOT_BY_ID = "SELECT `id_lot`, `lot_state`, `owner_id`,`auction_type`, `lot_name`, `step`, `start_price` FROM `lot` WHERE `id_lot` = ?;";
    private static final String DELETE_LOT_BY_ID = "DELETE FROM `lot` WHERE `id_lot` =?;";
    private static final String CREATE_NEW_LOT = "INSERT INTO `lot`  (`lot_state`,`owner_id`,`auction_type`,`lot_name`,`step`,`start_price`) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_LOT = "UPDATE `auction_db`.`lot` SET `lot_state`=?, `auction_type`=?, `lot_name`=? WHERE `id_lot`=?;";

    private static final String BIDDING_DIRECT_LOTS = "SELECT * FROM `lot` \n" +
            "WHERE `lot`.`lot_state` = (SELECT `lot_state`.`id_state` FROM `lot_state` WHERE `lot_state`.`state` = 'bidding_lot') \n" +
            "AND `lot`.`auction_type` = (SELECT `auction_type`.`id_type` FROM `auction_type` WHERE `auction_type`.`type` = 'direct');";
    private static final String BIDDING_REVERSE_LOTS = "SELECT * FROM `lot` \n" +
            "WHERE `lot`.`lot_state` = (SELECT `lot_state`.`id_state` FROM `lot_state` WHERE `lot_state`.`state` = 'bidding_lot') \n" +
            "AND `lot`.`auction_type` = (SELECT `auction_type`.`id_type` FROM `auction_type` WHERE `auction_type`.`type` = 'reverse');";
    private static final String PERSONAL_LOTS = "SELECT * FROM `lot`\n" +
            "WHERE `lot`.`owner_id` = ?;";


    public LotDao() {
        super(new LotStatementCreator(), new LotCreator());
    }

    @Override
    public List<Lot> findAll() throws DAOException {
        return findAll(SELECT_ALL_LOTS);
    }

    @Override
    public Lot findEntityById(Integer id) throws DAOException {
        return findEntityById(id, SELECT_LOT_BY_ID);
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        return delete(id, DELETE_LOT_BY_ID);
    }

    @Override
    public boolean create(Lot entity) throws DAOException {
        return create(entity, CREATE_NEW_LOT);
    }

    @Override
    public Lot update(Lot entity) throws DAOException {
        return update(entity, UPDATE_LOT);
    }

    /**
     * Finds all direct auction lots
     *
     * @return list of the lots
     * @throws DAOException when it occurred
     */
    public List<Lot> findAllDirectBiddingLots() throws DAOException {
        return findAll(BIDDING_DIRECT_LOTS);
    }

    /**
     * Finds all reverse auction lots
     *
     * @return list of the lots
     * @throws DAOException when it occurred
     */
    public List<Lot> findAllReverseBiddingLots() throws DAOException {
        return findAll(BIDDING_REVERSE_LOTS);
    }

    /**
     * Finds all personal user's lots by user id
     *
     * @return list of the lots
     * @throws DAOException when it occurred
     */
    public List<Lot> findAllPersonalLots(Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Lot> entities = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement(PERSONAL_LOTS);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Lot entity = entityCreator.createElement(resultSet);
                entities.add(entity);
            }
        } catch (SQLException | ConnectionPoolException exception) {
            throw new DAOException(exception.getMessage(), exception.getCause());
        } finally {
            close(resultSet, statement, connection);
        }
        return entities;
    }


}
