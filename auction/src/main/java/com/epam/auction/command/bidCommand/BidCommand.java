package com.epam.auction.command.bidCommand;

import com.epam.auction.command.ActionCommand;
import com.epam.auction.resource.Info;

import com.epam.auction.entity.Entity;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.ActualBalanceService;
import com.epam.auction.service.bidding.Bidding;
import com.epam.auction.service.creator.BaseListCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class is responsible for initializing new bids operation.
 */

public abstract class BidCommand implements ActionCommand {


    protected static final String NEW_BID_CREATED = "New bid was created LOT: ";
    protected static final String USER = "; USER: ";
    protected static final String RESULT = "; RESULT: ";
    protected Bidding biddingService;
    protected BaseListCreator<LotDto, Lot> creator;

    private ActualBalanceService balanceService;
    private static final Logger LOGGER = LogManager.getLogger(BidCommand.class);

    public BidCommand(Bidding biddingService, BaseListCreator<LotDto, Lot> creator, ActualBalanceService balanceService) {
        this.biddingService = biddingService;
        this.creator = creator;
        this.balanceService = balanceService;
    }

    /**
     * The method takes parameter with lot id.
     * If it not null tries to do a new bid using doBid() method.
     * Then it gets a new updated information about user using {@link ActualBalanceService}.
     *
     * @param request from user
     * @return page name
     * @throws CommandException when any {@link LogicException} occurred,
     *                          or if the bid did not made.
     */

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String stringId = request.getParameter(Info.PARAM_BID);
        String page = getPage();
        if (stringId != null) {
            int lotId = Integer.parseInt(stringId);
            Entity lot;
            try {
                lot = biddingService.getBiddingLot(lotId);

                HttpSession session = request.getSession(true);
                session.setAttribute(Info.ATTRIBUTE_NOT_MONEY, null);

                User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);
                int userId = user.getId();
                int biddingLotId = lot.getId();

                boolean isBid = doBid(biddingLotId, userId);

                if (!isBid) {
                    String local = (String) session.getAttribute(Info.LOCAL);
                    Locale locale = new Locale(local);
                    ResourceBundle bundle = ResourceBundle.getBundle(Info.MESS_BUNDLE, locale);
                    String message = bundle.getString(Info.MESS_NOT_ENOUGH_MONEY);
                    session.setAttribute(Info.ATTRIBUTE_NOT_MONEY, message);
                }

                List<Lot> lots = getLotList(userId);
                List<LotDto> actualLots = creator.createListDto(lots);

                user = balanceService.getActualUserInfo(userId);

                session.setAttribute(Info.ATTRIBUTE_USER, user);
                session.setAttribute(Info.ATTRIBUTE_LIST, actualLots);

            } catch (LogicException exception) {
                LOGGER.error(exception.getMessage(), exception);
                throw new CommandException(exception.getMessage(), exception);
            }
        }

        return page;
    }

    /**
     * The method initializing a new bid
     *
     * @param lotId  to bid on
     * @param userId that made a bid
     * @return state of result action
     * @throws LogicException when any {@link LogicException} occurred.
     */
    protected abstract boolean doBid(int lotId, int userId) throws LogicException;

    /**
     * The method updates the list of lots on the view
     *
     * @param idUser that made a bid
     * @return List of the actual lots
     * @throws LogicException when any {@link LogicException} occurred.
     */
    protected abstract List<Lot> getLotList(int idUser) throws LogicException;

    /**
     * Method of getting the path to the page
     *
     * @return page name
     */
    protected abstract String getPage();

}
