package com.epam.auction.dao.creator.statementCreator;

import com.epam.auction.entity.Bid;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets the parameters in the PreparedStatement for Bid entity
 */

public class BidStatementCreator extends AbstractStatementCreator<Bid> {

    @Override
    public void createInsertStatement(Bid entity, PreparedStatement statement) throws SQLException {
        int idOwner = entity.getOwnerId();
        statement.setInt(1, idOwner);

        int idLot = entity.getLotId();
        statement.setInt(2, idLot);

        BigDecimal bid = entity.getBid();
        statement.setBigDecimal(3, bid);
    }

    @Override
    public void createUpdateStatement(Bid entity, PreparedStatement statement) throws SQLException {

        BigDecimal bid = entity.getBid();
        statement.setBigDecimal(1, bid);

        int idBid = entity.getId();
        statement.setInt(2, idBid);
    }
}
