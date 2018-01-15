package com.epam.auction.dao.creator.entityCreator;

import com.epam.auction.entity.LotState;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates LotState entity from result set
 */
public class LotStateCreator extends AbstractEntityCreator<LotState> {

    /**
     * Columns names
     */
    private static final String COLUMN_ID = "id_state";
    private static final String COLUMN_STATE_NAME = "state";


    @Override
    public LotState createElement(ResultSet resultSet) throws SQLException {

        LotState state = new LotState();

        int stateId = resultSet.getInt(COLUMN_ID);
        state.setId(stateId);

        String stateName = resultSet.getString(COLUMN_STATE_NAME);
        state.setLotState(stateName);

        return state;
    }
}
