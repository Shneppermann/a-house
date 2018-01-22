package com.epam.auction.command;

import com.epam.auction.entity.Lot;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.LotManageService;
import com.epam.auction.service.creator.LotsListCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class is responsible for initializing lot manage page.
 */

public class LotManageCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger(LotManageCommand.class);

    private LotManageService manageService;
    private LotsListCreator creator;

    public LotManageCommand(LotManageService manageService, LotsListCreator creator) {
        this.manageService = manageService;
        this.creator = creator;
    }

    /**
     * The method tries to set correct session attributes.
     *
     * @param request from client
     * @return path to the lot manage page
     * @throws CommandException when any {@link LogicException} occurred.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            List<Lot> lots = manageService.getLots();
            List<LotDto> actualLots = creator.createListDto(lots);
            HttpSession session = request.getSession(true);
            session.setAttribute(Info.ATTRIBUTE_LIST, actualLots);
        } catch (LogicException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new CommandException(exception.getMessage(), exception);
        }
        page = ConfigurationManager.getProperty(Info.LOT_MANAGE_PAGE);
        return page;
    }

}
