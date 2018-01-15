package com.epam.auction.service.bidding;

import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;

import java.math.BigDecimal;

/**
 * The class is responsible for bids operation on the reverse auction.
 */
public class ReverseBiddingService extends Bidding {

    public ReverseBiddingService(UserDao userDao, LotDao lotDao, BidDao bidDao) {
        super(userDao, lotDao, bidDao);
    }

    /**
     * Gets actual minimum bid entity
     *
     * @param lotId lot's id
     * @return minimum bid entity
     * @throws DAOException when {@link DAOException} occurred
     */
    @Override
    protected Bid getBid(int lotId) throws DAOException {
        return bidDao.findMinBidByLotId(lotId);
    }

    /**
     * The method gets a new minimum bid price
     *
     * @param bid  previous minimum bid price
     * @param step of the bidding
     * @return new actual bid
     */
    @Override
    protected BigDecimal getNewMaxBid(BigDecimal bid, BigDecimal step) {
        return bid.subtract(step);
    }

    /**
     * Creates a new minimum bid price
     *
     * @param lotId  id of the lot
     * @param userId user's id
     * @return new minimum bid
     * @throws DAOException when {@link DAOException} occurred
     */
    @Override
    protected BigDecimal createBid(int lotId, int userId) throws DAOException {
        Lot lot = lotDao.findEntityById(lotId);
        BigDecimal newBid = lot.getStartPrice();
        BigDecimal step = lot.getStep();
        newBid = newBid.subtract(step);
        Bid bid = new Bid(userId, lotId, newBid);
        bidDao.create(bid);
        return newBid;
    }

    /**
     * Creates a new minimum bid price
     *
     * @param lotId     id of the lot
     * @param userId    user's id
     * @param actualBid actual minimum bid entity
     * @return new minimum  bid
     * @throws DAOException when {@link DAOException} occurred
     */

    @Override
    protected BigDecimal createBid(int lotId, int userId, Bid actualBid) throws DAOException {
        Lot lot = lotDao.findEntityById(lotId);
        BigDecimal newBid = actualBid.getBid();

        BigDecimal step = lot.getStep();
        newBid = newBid.subtract(step);
        Bid bid = new Bid(userId, lotId, newBid);

        bidDao.create(bid);
        return newBid;
    }
}
