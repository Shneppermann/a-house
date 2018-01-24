package com.epam.auction.service;

import com.epam.auction.dao.BaseDao;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * The class is responsible for adding the lot to the database
 */

public class AddLotService {

    private static final Logger LOGGER = LogManager.getLogger(AddLotService.class);
    private static final String NEW_LOT ="New lot created :";
    private final static String DIRECT_TYPE = "direct";
    private final static Integer DIRECT_TYPE_NUMBER = 1;
    private final static Integer REVERSE_TYPE_NUMBER = 2;
    private final static Integer DEFAULT_LOT_STATE = 1;
    private BaseDao<Lot> dao;


    public AddLotService(BaseDao<Lot> dao) {
        this.dao = dao;
    }

    /**
     * The method tries to add a new lot to the database
     *
     * @param userId     owner id
     * @param lotName    name of the lot
     * @param startPrice start price of the lot
     * @param step       of the lot
     * @param type       of auction
     * @return result of adding lot
     * @throws LogicException when {@link DAOException} occurred.
     */

    public boolean addLot(int userId, String lotName, String startPrice, String step, String type) throws LogicException {

        BigDecimal decStep;
        BigDecimal decPrice;
        try {
            decStep = new BigDecimal(step);
            decPrice = new BigDecimal(startPrice);
        } catch (NumberFormatException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        int lotAuctionType = getType(type);

        Lot newLot = new Lot(DEFAULT_LOT_STATE, userId, lotAuctionType, lotName, decStep, decPrice);
        boolean isCreate = false;
        try {
            isCreate = dao.create(newLot);
            LOGGER.info(NEW_LOT+lotName);
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return isCreate;
    }

    /**
     * Defines type of auction
     *
     * @param type of auction
     * @return auction type number
     */

    private int getType(String type) {
        int result;
        if (DIRECT_TYPE.equals(type)) {
            result = DIRECT_TYPE_NUMBER;
        } else {
            result = REVERSE_TYPE_NUMBER;
        }
        return result;
    }
}
