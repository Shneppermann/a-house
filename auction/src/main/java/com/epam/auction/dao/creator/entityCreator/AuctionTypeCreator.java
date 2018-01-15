package com.epam.auction.dao.creator.entityCreator;

import com.epam.auction.entity.AuctionType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Creates AuctionType entity from result set
 */
public class AuctionTypeCreator extends AbstractEntityCreator<AuctionType> {
    /**
     * Columns names
     */
    private static final String COLUMN_ID = "id_type";
    private static final String COLUMN_TYPE_NAME = "type";


    @Override
    public AuctionType createElement(ResultSet resultSet) throws SQLException {

        AuctionType type = new AuctionType();

        int typeId = resultSet.getInt(COLUMN_ID);
        type.setId(typeId);

        String typeName = resultSet.getString(COLUMN_TYPE_NAME);
        type.setAuctionType(typeName);

        return type;
    }
}
