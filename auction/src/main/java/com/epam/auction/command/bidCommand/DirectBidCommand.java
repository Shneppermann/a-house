package com.epam.auction.command.bidCommand;

import com.epam.auction.resource.Info;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.service.ActualBalanceService;
import com.epam.auction.service.DirectService;
import com.epam.auction.service.bidding.DirectBiddingService;
import com.epam.auction.service.creator.LotsListCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

/**
 * The class is responsible for initializing new direct bids operations.
 */

public class DirectBidCommand extends BidCommand {

    private static final Logger LOGGER = LogManager.getLogger(DirectBidCommand.class);

    private DirectService directService;


    public DirectBidCommand(DirectService directService, DirectBiddingService biddingService,
                            LotsListCreator creator, ActualBalanceService balanceService) {
        super(biddingService, creator, balanceService);
        this.directService = directService;
    }

    /**
     * The method initializing a new direct bid
     *
     * @param lotId  to bid on
     * @param userId that made a bid
     * @return result of the bid
     * @throws LogicException when any {@link LogicException} occurred
     */
    @Override
    protected boolean doBid(int lotId, int userId) throws LogicException {
        boolean isBid = false;
        try {
            isBid = biddingService.doBid(lotId, userId);
            LOGGER.info(NEW_BID_CREATED + lotId + USER + userId + RESULT + isBid);

        } catch (LogicException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return isBid;
    }

    /**
     * Gets list of the direct auction lots
     *
     * @param idUser that made a bid
     * @return direct auction lot list
     * @throws LogicException when {@link LogicException} occurred
     */
    @Override
    protected List<Lot> getLotList(int idUser) throws LogicException {
        return directService.getDirectLotList(idUser);
    }

    /**
     * Method of getting the path to the direct auction page
     *
     * @return path to the direct auction page
     */
    @Override
    protected String getPage() {
        return ConfigurationManager.getProperty(Info.DIRECT_PAGE);
    }

}
