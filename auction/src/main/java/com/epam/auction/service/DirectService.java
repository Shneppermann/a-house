package com.epam.auction.service;

import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the actual information about the direct auction's lots
 */

public class DirectService {

    private static final Logger LOGGER = LogManager.getLogger(DirectService.class);

    private LotDao lotDao;
    private BidDao bidDao;

    public DirectService(LotDao lotDao,BidDao bidDao) {
        this.lotDao = lotDao;
        this.bidDao = bidDao;
    }

    /**
     * The method tries to return all available direct auction lots
     *
     * @param userId client id
     * @return list of all available direct auction lots
     * @throws LogicException when {@link DAOException} occurred
     */

    public List<Lot> getDirectLotList(Integer userId) throws LogicException {

        List<Lot> resultLots = new ArrayList<>();
        try {
            List<Lot> allDirectLots = lotDao.findAllDirectBiddingLots();
            for (Lot lot : allDirectLots) {

                int lotId = lot.getId();

                Bid bid = bidDao.findMaxBidByLotId(lotId);

                boolean isNotPersonalLot = checkLot(lot, userId);
                boolean isNotMaxBid = checkBid(bid, userId);

                if (isNotPersonalLot && isNotMaxBid) {
                    resultLots.add(lot);
                }

            }
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage()+exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return resultLots;
    }

    /**
     * Checks owner of the lot
     *
     * @param lot    checked lot
     * @param userId client id
     * @return result of the check
     */
    private boolean checkLot(Lot lot, Integer userId) {
        int userLotId = lot.getOwnerId();
        return (userId != userLotId);
    }

    /**
     * Checks maximum bid of the lot
     *
     * @param bid    maximum bid
     * @param userId client id
     * @return result of the check
     */
    private boolean checkBid(Bid bid, Integer userId) {
        boolean isChecked = false;
        if (bid != null) {
            int bidOwnerId = bid.getOwnerId();
            if (userId != bidOwnerId) {
                isChecked = true;
            }
        } else {
            isChecked = true;
        }
        return isChecked;
    }
}
