package com.epam.auction.dao.creator.statementCreator;

import com.epam.auction.entity.LotState;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets the parameters in the PreparedStatement for LotState entity
 */

public class LotStateStatementCreator extends AbstractStatementCreator<LotState> {

    @Override
    public void createInsertStatement(LotState entity, PreparedStatement statement) throws SQLException {
        String stateName = entity.getLotState();
        statement.setString(1, stateName);

    }

    @Override
    public void createUpdateStatement(LotState entity, PreparedStatement statement) throws SQLException {

        String stateName = entity.getLotState();
        statement.setString(1, stateName);

        int idState = entity.getId();
        statement.setInt(2, idState);
    }
}
