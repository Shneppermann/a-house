package com.epam.auction.command.bidCommand;


import com.epam.auction.resource.Info;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.service.ActualBalanceService;
import com.epam.auction.service.ReverseService;
import com.epam.auction.service.bidding.ReverseBiddingService;
import com.epam.auction.service.creator.LotsListCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class is responsible for initializing new reverse bids operations.
 */

public class ReverseBidCommand extends BidCommand {

    private static final Logger LOGGER = LogManager.getLogger(ReverseBidCommand.class);

    private ReverseService reverseService;


    public ReverseBidCommand(ReverseService reverseService, ReverseBiddingService biddingService,
                             LotsListCreator creator, ActualBalanceService balanceService) {
        super(biddingService, creator, balanceService);
        this.reverseService = reverseService;
    }

    /**
     * The method initializing a new reverse bid
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
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return isBid;
    }


    /**
     * Gets list of the reverse auction lots
     *
     * @param idUser that made a bid
     * @return reverse auction lot list
     * @throws LogicException when {@link LogicException} occurred
     */

    @Override
    protected List<Lot> getLotList(int idUser) throws LogicException {
        return reverseService.getReverseLotList(idUser);
    }

    /**
     * Method of getting the path to the reverse auction page
     *
     * @return path to the reverse auction page
     */
    @Override
    protected String getPage() {
        return ConfigurationManager.getProperty(Info.REVERSE_PAGE);
    }
}
