package com.epam.auction.command.checkCommand;

import com.epam.auction.resource.Info;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.AddLotService;
import com.epam.auction.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class {@link OfferLotCommand} is responsible for adding new lots to the database.
 * It reads and check all parameters, and if they are correct, initializes adding a new lot.
 */
public class OfferLotCommand extends BaseCheckCommand {

    private static final String ERROR_STRING = "Error! Lot not added";

    private AddLotService service;

    public OfferLotCommand(AddLotService service) {
        this.service = service;
    }

    /**
     *
     * The method takes and checks all of the needed parameters. Then it tries to add
     * a new lot using {@link AddLotService}.
     * @param request from client
     * @return page name
     * @throws CommandException when any {@link LogicException} occurred,
     *                          or if new lot did not added
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Info.ATTRIBUTE_USER);

        String lotName = getParam(request, Info.PARAM_LOT_NAME);
        String startPrice = getParam(request, Info.PARAM_START_PRICE);
        String step = getParam(request, Info.PARAM_STEP);
        String type = getParam(request, Info.PARAM_AUCTION_TYPE);

        boolean isCorrectParam = (checkCorrectParameters(lotName) && checkCorrectParameters(startPrice)
                && checkCorrectParameters(step) && checkCorrectParameters(type));

        if (isCorrectParam) {
            int userId = user.getId();
            boolean isCreate;
            try {
                isCreate = service.addLot(userId, lotName, startPrice, step, type);
            } catch (LogicException exception) {
                throw new CommandException(exception.getMessage(), exception);
            }
            if (!isCreate){
                throw new CommandException(ERROR_STRING);
            }
        }

        return ConfigurationManager.getProperty(Info.ADD_LOT_PAGE);
    }

}
