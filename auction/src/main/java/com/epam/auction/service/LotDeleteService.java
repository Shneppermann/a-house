package com.epam.auction.service;

import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

/**
 * Tries to delete lot
 */
public class LotDeleteService {

    private static final Logger LOGGER = LogManager.getLogger(LotDeleteService.class);
    private static final String LOT_DELETED = "Lot was deleted ";
    private static final int EMPTY_OWNER_ID = 0;
    private static final String RETURN_ERROR_MESSAGE = "Error! Can't return bid";
    private static final String DELETE_BIDS_ERROR_MESSAGE = "Error! Can't delete bids";

    private LotDao lotDao;
    private BidDao bidDao;
    private UserDao userDao;

    public LotDeleteService(LotDao lotDao, BidDao bidDao, UserDao userDao) {
        this.lotDao = lotDao;
        this.bidDao = bidDao;
        this.userDao = userDao;
    }

    /**
     * Tries to delete lot
     *
     * @param lot deleted lot dto
     * @return result of delete operation
     * @throws LogicException when {@link DAOException} occurred
     */
    public boolean deleteLot(LotDto lot) throws LogicException {

        int maxBidOwnerId = lot.getBidOwnerId();
        BigDecimal actualBid = lot.getBid();
        int lotId = lot.getId();
        if (maxBidOwnerId != EMPTY_OWNER_ID) {
            try {
                boolean isReturn = returnMaxBid(actualBid, maxBidOwnerId);
                if (!isReturn) {
                    throw new LogicException(RETURN_ERROR_MESSAGE);
                }

                boolean isDeleteBids = deleteLotBids(lotId);
                if (!isDeleteBids) {
                    throw new LogicException(DELETE_BIDS_ERROR_MESSAGE);
                }
            } catch (DAOException exception) {
                throw new LogicException(exception.getMessage(), exception);
            }
        }
        boolean isLotDelete = false;
        try {
            isLotDelete = lotDao.delete(lotId);
            LOGGER.info(LOT_DELETED + lotId);
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }

        return isLotDelete;
    }

    /**
     * Returns actual bid to the owner
     *
     * @param bid    actual bid
     * @param userId bid owner id
     * @return result of return of the bid
     * @throws DAOException when it occurred
     */
    private boolean returnMaxBid(BigDecimal bid, int userId) throws DAOException {

        User user = userDao.findEntityById(userId);
        BigDecimal balance = user.getBalance();
        balance = balance.add(bid);
        user.setBalance(balance);
        user = userDao.update(user);
        return (user != null);
    }

    /**
     * Tries to delete all of the lot's bids
     *
     * @param lotId of tje lot
     * @return result of bids delete
     * @throws DAOException when it occurred
     */
    private boolean deleteLotBids(int lotId) throws DAOException {

        List<Bid> allBids = bidDao.findAll();
        boolean isDelete = false;
        for (Bid bid : allBids) {
            int lotBidId = bid.getLotId();
            if (lotBidId == lotId) {
                int bidId = bid.getId();
                isDelete = bidDao.delete(bidId);
            }
        }
        return isDelete;
    }

}
