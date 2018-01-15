package com.epam.auction.service.bidding;



import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;


import java.math.BigDecimal;

/**
 * The class is responsible for bids operation on the direct auction.
 */

public class DirectBiddingService extends Bidding{

    public DirectBiddingService(UserDao userDAO, LotDao lotDao,BidDao bidDao){
        super(userDAO,lotDao,bidDao);
    }

    /**
     * Gets actual maximum bid entity
     *
     * @param lotId lot's id
     * @return maximum bid entity
     * @throws DAOException when {@link DAOException} occurred
     */
    @Override
    protected Bid getBid(int lotId) throws DAOException{
        return bidDao.findMaxBidByLotId(lotId);
    }

    /**
     * The method gets a new maximum bid price
     *
     * @param bid  previous maximum bid price
     * @param step of the bidding
     * @return new actual bid
     */
    @Override
    protected BigDecimal getNewMaxBid(BigDecimal bid, BigDecimal step){
        return bid.add(step);
    }

    /**
     * Creates a new maximum bid price
     *
     * @param lotId  id of the lot
     * @param userId user's id
     * @return new maximum bid
     * @throws DAOException when {@link DAOException} occurred
     */
    @Override
    protected BigDecimal createBid(int lotId, int userId)throws DAOException{
        Lot lot = lotDao.findEntityById(lotId);
        BigDecimal newBid = lot.getStartPrice();
        BigDecimal step = lot.getStep();
        newBid = newBid.add(step);
        Bid bid = new Bid(userId, lotId, newBid);

        bidDao.create(bid);
        return newBid;
    }

    /**
     * Creates a new maximum bid price
     *
     * @param lotId     id of the lot
     * @param userId    user's id
     * @param actualBid actual maximum bid entity
     * @return new maximum  bid
     * @throws DAOException when {@link DAOException} occurred
     */
    @Override
    protected BigDecimal createBid(int lotId, int userId, Bid actualBid) throws DAOException {
        Lot lot = lotDao.findEntityById(lotId);
        BigDecimal newBid = actualBid.getBid();

        newBid = newBid.add(lot.getStep());
        Bid bid = new Bid(userId, lotId, newBid);

        bidDao.create(bid);
        return newBid;
    }
}
