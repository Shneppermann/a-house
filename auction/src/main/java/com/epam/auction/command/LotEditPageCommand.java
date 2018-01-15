package com.epam.auction.command;

import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.LotEditPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class is responsible for initializing lot edit page.
 */

public class LotEditPageCommand implements ActionCommand {

    private LotEditPageService service;

    public LotEditPageCommand(LotEditPageService service) {
        this.service = service;
    }

    /**
     * The method tries to set correct session attributes.
     *
     * @param request from client
     * @return path to the lot edit page if lotId parameter is exist,
     * otherwise return path to the lot manage page
     * @throws CommandException when any {@link LogicException} occurred.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String stringLotId = request.getParameter(Info.PARAM_LOT_ID);
        String page = null;
        if (stringLotId != null) {
            int lotId = Integer.parseInt(stringLotId);
            LotDto lot;
            try {
                lot = service.getChangedLot(lotId);
            } catch (LogicException exception) {
                throw new CommandException(exception.getMessage(), exception);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute(Info.ATTRIBUTE_LOT, lot);
            page = ConfigurationManager.getProperty(Info.LOT_EDIT_PAGE);
        } else {
            page = ConfigurationManager.getProperty(Info.LOT_MANAGE_PAGE);
        }
        return page;
    }

}
