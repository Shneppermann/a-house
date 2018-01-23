package com.epam.auction.dao.creator.entityCreator;


import com.epam.auction.entity.Lot;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates Lot entity from result set
 */
public class LotCreator extends AbstractEntityCreator<Lot> {

    /**
     * Columns names
     */
    private static final String COLUMN_ID = "id_lot";
    private static final String COLUMN_STATE = "lot_state";
    private static final String COLUMN_OWNER_ID = "owner_id";
    private static final String COLUMN_AUCTION_TYPE = "auction_type";
    private static final String COLUMN_NAME = "lot_name";
    private static final String COLUMN_STEP = "step";
    private static final String COLUMN_START_PRICE = "start_price";

    @Override
    public Lot createElement(ResultSet resultSet) throws SQLException {
        Lot lot = new Lot();

        int lotID = resultSet.getInt(COLUMN_ID);
        lot.setId(lotID);

        int state = resultSet.getInt(COLUMN_STATE);
        lot.setLotState(state);

        int ownerId = resultSet.getInt(COLUMN_OWNER_ID);
        lot.setOwnerId(ownerId);

        int auctionId = resultSet.getInt(COLUMN_AUCTION_TYPE);
        lot.setAuctionType(auctionId);

        String name = resultSet.getString(COLUMN_NAME);
        lot.setLotName(name);

        BigDecimal step = resultSet.getBigDecimal(COLUMN_STEP);
        lot.setStep(step);

        BigDecimal startPrice = resultSet.getBigDecimal(COLUMN_START_PRICE);
        lot.setStartPrice(startPrice);

        return lot;
    }
}
