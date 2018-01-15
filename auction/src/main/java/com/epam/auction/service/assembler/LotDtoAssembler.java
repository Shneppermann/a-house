package com.epam.auction.service.assembler;

import com.epam.auction.dao.*;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.AuctionType;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.LotState;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.LogicException;

import java.math.BigDecimal;

/**
 * Class assembles an lot dto objects
 */
public class LotDtoAssembler extends AbstractAssembler<Lot, LotDto> {

    private static final int DIRECT_TYPE = 1;
    private static final int EMPTY_PARAM = 0;

    private BaseDao<AuctionType> typeDao;
    private BaseDao<LotState> stateDao;
    private BidDao bidDao;

    public LotDtoAssembler(BaseDao<AuctionType> typeDao, BaseDao<LotState> stateDao, BidDao bidDao) {
        this.typeDao = typeDao;
        this.stateDao = stateDao;
        this.bidDao = bidDao;

    }

    /**
     * Creates lot DTO objects
     *
     * @param lot simple user entity
     * @return LotDto object
     * @throws LogicException when {@link DAOException} occurred
     */
    @Override
    public LotDto createObjectDTO(Lot lot) throws LogicException {

        LotDto lotWithBid = new LotDto();
        try {

            int idState = lot.getLotState();
            LotState state = stateDao.findEntityById(idState);

            String stateStr = state.getLotState();
            lotWithBid.setLotState(stateStr);

            int idType = lot.getAuctionType();
            AuctionType type = typeDao.findEntityById(idType);

            String typeStr = type.getAuctionType();
            lotWithBid.setAuctionType(typeStr);

            int lotId = lot.getId();
            Bid actualBid = getActualBid(lot);

            if (actualBid != null) {
                int bidId = actualBid.getId();
                lotWithBid.setHighBidId(bidId);

                int bidOwnerId = actualBid.getOwnerId();
                lotWithBid.setBidOwnerId(bidOwnerId);

                BigDecimal bid = actualBid.getBid();
                lotWithBid.setBid(bid);
            } else {

                lotWithBid.setHighBidId(EMPTY_PARAM);
                lotWithBid.setBidOwnerId(EMPTY_PARAM);
                BigDecimal startPrice = lot.getStartPrice();
                lotWithBid.setBid(startPrice);
            }

            lotWithBid.setId(lotId);

            int lotOwnerId = lot.getOwnerId();
            lotWithBid.setLotOwnerId(lotOwnerId);

            String lotName = lot.getLotName();
            lotWithBid.setLotName(lotName);

            BigDecimal step = lot.getStep();
            lotWithBid.setStep(step);

        } catch (DAOException exception) {
            throw new LogicException(exception.getMessage(), exception);
        }

        return lotWithBid;
    }

    /**
     * Returns actual bid
     * @param lot lot id
     * @return actual bid
     * @throws DAOException
     */
    private Bid getActualBid(Lot lot) throws DAOException {
        int lotAuctionType = lot.getAuctionType();
        int lotId = lot.getId();
        Bid actualBid = null;
        if (lotAuctionType == DIRECT_TYPE) {
            actualBid = getMaxBid(lotId);
        } else {
            actualBid = getMinBid(lotId);
        }
        return actualBid;
    }

    /**
     * Returns maximum bid
     * @param lotId lot id
     * @return maximum bid
     * @throws DAOException
     */
    private Bid getMaxBid(int lotId) throws DAOException {
        return bidDao.findMaxBidByLotId(lotId);
    }

    /**
     * Returns minimum bid
     * @param lotId lot id
     * @return minimum bid
     * @throws DAOException
     */
    private Bid getMinBid(int lotId) throws DAOException {
        return bidDao.findMinBidByLotId(lotId);
    }


}
