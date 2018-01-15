package com.epam.auction.dao.creator.statementCreator;

import com.epam.auction.entity.Lot;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets the parameters in the PreparedStatement for Lot entity
 */
public class LotStatementCreator extends AbstractStatementCreator<Lot> {

    @Override
    public void createInsertStatement(Lot entity, PreparedStatement statement) throws SQLException {
        int idString = entity.getLotState();
        statement.setInt(1, idString);

        int idOwner = entity.getOwnerId();
        statement.setInt(2, idOwner);

        int idAuctionType = entity.getAuctionType();
        statement.setInt(3, idAuctionType);

        String lotName = entity.getLotName();
        statement.setString(4, lotName);

        BigDecimal step = entity.getStep();
        statement.setBigDecimal(5, step);

        BigDecimal startPrice = entity.getStartPrice();
        statement.setBigDecimal(6, startPrice);
    }

    @Override
    public void createUpdateStatement(Lot entity, PreparedStatement statement) throws SQLException {
        int idString = entity.getLotState();
        statement.setInt(1, idString);

        int idAuctionType = entity.getAuctionType();
        statement.setInt(2, idAuctionType);

        String lotName = entity.getLotName();
        statement.setString(3, lotName);

        int lotId = entity.getId();
        statement.setInt(4, lotId);
    }
}
