package com.epam.auction.service.bidding;

import com.epam.auction.dao.BaseDao;
import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.LogicException;


import java.math.BigDecimal;

/**
 * The class is responsible for bids operation.
 */

public abstract class Bidding {

    protected BaseDao<User> userDao;
    protected BaseDao<Lot> lotDao;
    protected BidDao bidDao;


    public Bidding(UserDao userDAO, LotDao lotDao, BidDao bidDao) {
        this.userDao = userDAO;
        this.lotDao = lotDao;
        this.bidDao = bidDao;
    }

    /**
     * The method tries to do a new bid
     *
     * @param lotId  lot id
     * @param userId user id
     * @return result of the bid
     * @throws LogicException when {@link DAOException} occurred
     */

    public boolean doBid(int lotId, int userId) throws LogicException {

        boolean isEnoughMoney = false;
        try {
            BigDecimal userBalance = getBalance(userId);
            Bid actualBid = getBid(lotId);
            if (actualBid != null) {
                isEnoughMoney = checkBalanceMaxBid(userBalance, actualBid);
            } else {
                Lot lot = lotDao.findEntityById(lotId);
                BigDecimal startPrice = lot.getStartPrice();
                isEnoughMoney = checkBalanceStartPrice(userBalance, startPrice);
            }
            if (isEnoughMoney && actualBid != null) {
                returnActualBid(actualBid);
                BigDecimal createdBid = createBid(lotId, userId, actualBid);
                updateBalance(userId, createdBid);
            } else if (isEnoughMoney) {
                BigDecimal createdBid = createBid(lotId, userId);
                updateBalance(userId, createdBid);
            }
        } catch (DAOException exception) {
            throw new LogicException(exception.getMessage(), exception);
        }
        return isEnoughMoney;
    }

    /**
     * Tries to get bidding lot
     *
     * @param lotId lot id
     * @return bidding lot
     * @throws LogicException when {@link DAOException} occurred
     */

    public Lot getBiddingLot(int lotId) throws LogicException {
        Lot lot;
        try {
            lot = lotDao.findEntityById(lotId);
        } catch (DAOException exception) {
            throw new LogicException(exception.getMessage(), exception);
        }
        return lot;
    }


    /**
     * Gets the user's balance
     *
     * @param userId user id
     * @return user's balance
     * @throws DAOException when {@link DAOException} occurred
     */
    private BigDecimal getBalance(int userId) throws DAOException {
        User user = userDao.findEntityById(userId);
        return user.getBalance();
    }

    /**
     * Checks the user's balance
     *
     * @param userBalance user's balance
     * @param actualBid   actual bid
     * @return result of checking
     * @throws DAOException when {@link DAOException} occurred
     */

    private boolean checkBalanceMaxBid(BigDecimal userBalance, Bid actualBid) throws DAOException {
        int idLot = actualBid.getLotId();
        Lot lot = lotDao.findEntityById(idLot);
        BigDecimal step = lot.getStep();
        BigDecimal bid = actualBid.getBid();
        BigDecimal maxBid = getNewMaxBid(bid, step);
        int compare = userBalance.compareTo(maxBid);
        return (compare >= 0);
    }

    /**
     * Checks the user's balance
     *
     * @param userBalance user's balance
     * @param startPrice  start price
     * @return result of checking
     */

    private boolean checkBalanceStartPrice(BigDecimal userBalance, BigDecimal startPrice) {
        int compare = userBalance.compareTo(startPrice);
        return (compare >= 0);
    }

    /**
     * Tries to return the previous actual bid to the owner
     *
     * @param actualBid actual bid entity
     * @throws DAOException when {@link DAOException} occurred
     */
    private void returnActualBid(Bid actualBid) throws DAOException {
        BigDecimal bid = actualBid.getBid();

        int userId = actualBid.getOwnerId();
        User user = userDao.findEntityById(userId);

        BigDecimal newBalance = user.getBalance();
        newBalance = newBalance.add(bid);
        user.setBalance(newBalance);

        userDao.update(user);
    }


    /**
     * Tries to update user's balance
     *
     * @param userId user's id
     * @param newBid new actual bid entity
     * @throws DAOException when {@link DAOException} occurred
     */
    private void updateBalance(int userId, BigDecimal newBid) throws DAOException {
        User user = userDao.findEntityById(userId);

        BigDecimal balance = user.getBalance();
        balance = balance.subtract(newBid);

        user.setBalance(balance);
        userDao.update(user);
    }

    /**
     * Gets actual bid entity
     *
     * @param lotId lot's id
     * @return actual bid entity
     * @throws DAOException when {@link DAOException} occurred
     */
    protected abstract Bid getBid(int lotId) throws DAOException;

    /**
     * The method gets a new actual bid price
     *
     * @param bid  previous actual bid price
     * @param step of the bidding
     * @return new actual bid
     */
    protected abstract BigDecimal getNewMaxBid(BigDecimal bid, BigDecimal step);

    /**
     * Creates a new actual bid price
     *
     * @param lotId  id of the lot
     * @param userId user's id
     * @return new actual bid
     * @throws DAOException when {@link DAOException} occurred
     */
    protected abstract BigDecimal createBid(int lotId, int userId) throws DAOException;

    /**
     * Creates a new actual bid price
     *
     * @param lotId     id of the lot
     * @param userId    user's id
     * @param actualBid actual bid entity
     * @return new actual bid
     * @throws DAOException when {@link DAOException} occurred
     */
    protected abstract BigDecimal createBid(int lotId, int userId, Bid actualBid) throws DAOException;
}
