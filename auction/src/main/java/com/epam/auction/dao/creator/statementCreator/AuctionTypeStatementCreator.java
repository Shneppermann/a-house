package com.epam.auction.dao.creator.statementCreator;

import com.epam.auction.entity.AuctionType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets the parameters in the PreparedStatement for AuctionType entity
 */
public class AuctionTypeStatementCreator extends AbstractStatementCreator<AuctionType> {

    @Override
    public void createInsertStatement(AuctionType entity, PreparedStatement statement) throws SQLException {
        String type = entity.getAuctionType();
        statement.setString(1, type);

    }

    @Override
    public void createUpdateStatement(AuctionType entity, PreparedStatement statement) throws SQLException {

        String type = entity.getAuctionType();
        statement.setString(1, type);

        int idRole = entity.getId();
        statement.setInt(2, idRole);
    }
}
