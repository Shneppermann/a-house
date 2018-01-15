package com.epam.auction.command;

import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.DirectService;
import com.epam.auction.service.creator.BaseListCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class is responsible for initializing direct auction page.
 */

public class DirectCommand implements ActionCommand {

    private DirectService service;
    private BaseListCreator<LotDto, Lot> creator;

    public DirectCommand(DirectService service, BaseListCreator<LotDto, Lot> creator) {
        this.service = service;
        this.creator = creator;
    }

    /**
     * The method tries to set correct session attributes.
     *
     * @param request from user
     * @return path of the direct auction page
     * @throws CommandException when any {@link LogicException} occurred.
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);
        session.setAttribute(Info.ATTRIBUTE_NOT_MONEY,null);

        String page = null;

        try {
            int idUser = user.getId();
            List<Lot> lots = service.getDirectLotList(idUser);
            List<LotDto> actualLots = creator.createListDto(lots);

            session.setAttribute(Info.ATTRIBUTE_USER, user);
            session.setAttribute(Info.ATTRIBUTE_LIST, actualLots);

        } catch (LogicException exception) {
            throw new CommandException(exception.getMessage(), exception);
        }
        page = ConfigurationManager.getProperty(Info.DIRECT_PAGE);
        return page;
    }
}
