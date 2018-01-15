package com.epam.auction.service;


import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;

import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the actual information about the actual client bids
 */

public class BiddingService {
    private static final Logger LOGGER = LogManager.getLogger(BiddingService.class);
    private static final int DIRECT_TYPE = 1;
    private static final int REVERSE_TYPE = 2;

    private LotDao lotDao;
    private BidDao bidDao;

    public BiddingService(LotDao lotDao, BidDao bidDao) {
        this.lotDao = lotDao;
        this.bidDao = bidDao;
    }

    /**
     * The method tries to return all lots with client's high bid
     *
     * @param userId client id
     * @return list of lots with client's high bid
     * @throws LogicException when {@link DAOException} occurred
     */

    public List<Lot> getSelfBidList(int userId) throws LogicException {

        List<Lot> result;
        try {
            List<Lot> lots = lotDao.findAll();
            result = getListWithMaxBids(lots, userId);
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage()+exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return result;
    }

    /**
     * The method finds and returns all lots with client's high bid
     *
     * @param lots   all active lots
     * @param userId client id
     * @return list of lots with client's high bid
     * @throws DAOException when {@link DAOException} occurred
     */
    private List<Lot> getListWithMaxBids(List<Lot> lots, int userId) throws DAOException {
        List<Lot> resultList = new ArrayList<>();
        for (Lot lot : lots) {
            int lotAuctionType = lot.getAuctionType();
            boolean isUserActualBid = false;
            if (DIRECT_TYPE == lotAuctionType) {
                isUserActualBid = checkDirect(lot, userId);
            } else if (REVERSE_TYPE == lotAuctionType) {
                isUserActualBid = checkReverse(lot, userId);
            }
            if (isUserActualBid) {
                resultList.add(lot);
            }
        }
        return resultList;
    }

    /**
     * Checks lots of direct auction for the maximum client bid
     *
     * @param lot    checked lot
     * @param userId client id
     * @return result of checking
     * @throws DAOException when {@link DAOException} occurred
     */

    private boolean checkDirect(Lot lot, int userId) throws DAOException {
        int lotId = lot.getId();
        boolean isActualBid = false;
        Bid actualBid = null;
        try {
            actualBid = bidDao.findMaxBidByLotId(lotId);
        } catch (DAOException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
        if (actualBid != null) {
            int bidOwnerId = actualBid.getOwnerId();
            if (userId == bidOwnerId) {
                isActualBid = true;
            }
        }
        return isActualBid;
    }

    /**
     * Checks lots of reverse auction for the maximum client bid
     *
     * @param lot    checked lot
     * @param userId client id
     * @return result of checking
     * @throws DAOException when {@link DAOException} occurred
     */

    private boolean checkReverse(Lot lot, int userId) throws DAOException {
        int lotId = lot.getId();
        boolean isActualBid = false;
        Bid actualBid = null;
        try {
            actualBid = bidDao.findMinBidByLotId(lotId);
        } catch (DAOException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
        if (actualBid != null) {
            int bidOwnerId = actualBid.getOwnerId();
            if (userId == bidOwnerId) {
                isActualBid = true;
            }
        }
        return isActualBid;
    }
}
