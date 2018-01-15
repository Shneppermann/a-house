package com.epam.auction.command;


import com.epam.auction.entity.Lot;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.LotEditService;
import com.epam.auction.service.LotManageService;
import com.epam.auction.service.creator.LotsListCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class is responsible for initializing update of the lots.
 */
public class LotEditCommand implements ActionCommand {

    private static final String ERROR_MESSAGE = "Error! Lot not updated";

    private LotManageService manageService;
    private LotEditService editService;
    private LotsListCreator creator;

    public LotEditCommand(LotManageService manageService, LotEditService editService, LotsListCreator creator) {
        this.manageService = manageService;
        this.editService = editService;
        this.creator = creator;
    }

    /**
     * The method tries to update lot
     *
     * @param request from client
     * @return path to the lot manage page
     * @throws CommandException when any {@link LogicException} occurred.
     */

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String newState = request.getParameter(Info.PARAM_NEW_LOT_STATE);
        HttpSession session = request.getSession(true);
        LotDto actualLot = (LotDto) session.getAttribute(Info.ATTRIBUTE_LOT);
        String page = null;
        try {
            boolean isChange = editService.changeLot(actualLot, newState);
            if(!isChange){
                throw new CommandException(ERROR_MESSAGE);
            }
            List<Lot> lots = manageService.getLots();
            List<LotDto> actualLots = creator.createListDto(lots);
            session.setAttribute(Info.ATTRIBUTE_LIST, actualLots);
        } catch (LogicException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }

        page = ConfigurationManager.getProperty(Info.LOT_MANAGE_PAGE);
        return page;
    }

}
