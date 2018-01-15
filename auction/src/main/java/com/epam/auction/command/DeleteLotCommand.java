package com.epam.auction.command;


import com.epam.auction.entity.Lot;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.LotDeleteService;
import com.epam.auction.service.LotManageService;
import com.epam.auction.service.creator.LotsListCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Initializes delete of the lot
 */
public class DeleteLotCommand implements ActionCommand {

    public static final String DELETE_ERROR = "Error! Lot is not deleted";

    private LotDeleteService deleteService;
    private LotManageService manageService;
    private LotsListCreator creator;

    public DeleteLotCommand(LotDeleteService deleteService,LotManageService manageService,LotsListCreator creator){
        this.deleteService = deleteService;
        this.manageService = manageService;
        this.creator = creator;
    }

    /**
     * Tries to delete a lot
     *
     * @param request from client
     * @return path to the lot manage page
     * @throws CommandException when {@link LogicException} occurred
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        LotDto lot = (LotDto) session.getAttribute(Info.ATTRIBUTE_LOT);
        try {
            boolean isDelete = deleteService.deleteLot(lot);
            if (!isDelete) {
                throw new CommandException(DELETE_ERROR);
            }
            List<Lot> lots = manageService.getLots();
            List<LotDto> actualLots = creator.createListDto(lots);
            session.setAttribute(Info.ATTRIBUTE_LIST, actualLots);
        } catch (LogicException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
        String page = ConfigurationManager.getProperty(Info.LOT_MANAGE_PAGE);
        return page;
    }
}
