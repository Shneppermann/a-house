package com.epam.auction.service;

import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;

/**
 * The class is responsible for checking the owners of entities
 */


public abstract class CheckService {

    /**
     * Checks owner of the lot
     *
     * @param lot    checked lot
     * @param userId client id
     * @return result of the check
     */
    protected boolean checkLot(Lot lot, Integer userId) {
        int userLotId = lot.getOwnerId();
        return (userId != userLotId);
    }

    /**
     * Checks actual bid of the lot
     *
     * @param bid    checked bid
     * @param userId client id
     * @return result of the check
     */
    protected boolean checkBid(Bid bid, Integer userId) {
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
