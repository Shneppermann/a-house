package com.epam.auction.dao.creator.entityCreator;

import com.epam.auction.entity.Bid;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates Bid entity from result set
 */
public class BidCreator extends AbstractEntityCreator<Bid> {

    /**
     * Columns names
     */
    private static final String COLUMN_ID = "id_bid";
    private static final String COLUMN_OWNER_ID = "owner_id";
    private static final String COLUMN_LOT_ID = "lot_id";
    private static final String COLUMN_BID = "bid";

    @Override
    public Bid createElement(ResultSet resultSet) throws SQLException {

        Bid bid = new Bid();

        int lotID = resultSet.getInt(COLUMN_ID);
        bid.setId(lotID);

        int ownerId = resultSet.getInt(COLUMN_OWNER_ID);
        bid.setOwnerId(ownerId);

        int lotId = resultSet.getInt(COLUMN_LOT_ID);
        bid.setLotId(lotId);

        BigDecimal bidValue = resultSet.getBigDecimal(COLUMN_BID);
        bid.setBid(bidValue);

        return bid;
    }
}
