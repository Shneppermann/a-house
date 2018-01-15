package com.epam.auction.command;

import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.resource.ConfigurationManager;
import com.epam.auction.resource.Info;
import com.epam.auction.service.LotsService;
import com.epam.auction.service.creator.BaseListCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class is responsible for initializing user personal lots page.
 */
public class LotsCommand implements ActionCommand {


    private LotsService service;
    private BaseListCreator<LotDto,Lot> creator;

    public LotsCommand(LotsService service, BaseListCreator<LotDto,Lot> creator){
        this.service = service;
        this.creator = creator;
    }

    /**
     * The method tries to set correct session attributes.
     *
     * @param request from client
     * @return path to the user personal lots page
     * @throws CommandException when any {@link LogicException} occurred.
     */
    @Override
    public String execute (HttpServletRequest request)throws CommandException{

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);

        String page = null;

        try {
            int idUser = user.getId();
            List<Lot> lots = service.getSelfLotList(idUser);
            List<LotDto> actualLots = creator.createListDto(lots);

            session.setAttribute(Info.ATTRIBUTE_USER,user);
            session.setAttribute(Info.ATTRIBUTE_LIST,actualLots);

        } catch (LogicException exception){
            throw new CommandException(exception.getMessage(),exception);
        }
        page = ConfigurationManager.getProperty(Info.LOTS_PAGE);
        return page;
    }
}
