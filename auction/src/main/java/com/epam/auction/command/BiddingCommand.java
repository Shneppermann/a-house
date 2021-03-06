package com.epam.auction.command;

import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.BiddingService;
import com.epam.auction.service.creator.BaseListCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class is responsible for initializing active bids page.
 */

public class BiddingCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(BiddingCommand.class);

    private BaseListCreator<LotDto, Lot> creator;
    private BiddingService service;

    public BiddingCommand(BiddingService service, BaseListCreator<LotDto, Lot> creator) {
        this.service = service;
        this.creator = creator;
    }

    /**
     * The method tries to set correct session attributes.
     *
     * @param request from user
     * @return path of the active bids page
     * @throws CommandException when any {@link LogicException} occurred.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);
        String page;
        try {
            int idUser = user.getId();
            List<Lot> lots = service.getSelfBidList(idUser);

            List<LotDto> actualLots = creator.createListDto(lots);
            session.setAttribute(Info.ATTRIBUTE_USER, user);
            session.setAttribute(Info.ATTRIBUTE_LIST, actualLots);

        } catch (LogicException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new CommandException(exception.getMessage(), exception);
        }

        page = ConfigurationManager.getProperty(Info.BIDDING_PAGE);
        return page;
    }
}
