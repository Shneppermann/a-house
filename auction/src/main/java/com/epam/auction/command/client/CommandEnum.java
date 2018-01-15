package com.epam.auction.command.client;

import com.epam.auction.command.*;
import com.epam.auction.command.bidCommand.DirectBidCommand;
import com.epam.auction.command.bidCommand.ReverseBidCommand;
import com.epam.auction.command.checkCommand.LoginCommand;
import com.epam.auction.command.checkCommand.OfferLotCommand;
import com.epam.auction.command.checkCommand.RegistrationCommand;
import com.epam.auction.dao.*;
import com.epam.auction.entity.Lot;
import com.epam.auction.service.*;
import com.epam.auction.service.assembler.LotDtoAssembler;
import com.epam.auction.service.assembler.UserDtoAssembler;
import com.epam.auction.service.bidding.DirectBiddingService;
import com.epam.auction.service.bidding.ReverseBiddingService;
import com.epam.auction.service.creator.LotsListCreator;
import com.epam.auction.service.creator.UserListCreator;

public enum CommandEnum {

    LOGIN {
        {
            LotDao lotDao = new LotDao();
            UserDao userDao = new UserDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            RoleDao roleDao = new RoleDao();
            UserDtoAssembler userAssembler = new UserDtoAssembler(roleDao);
            UserListCreator userListCreator = new UserListCreator(userAssembler);
            LotDtoAssembler lotAssembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator lotsListCreator = new LotsListCreator(lotAssembler);
            DirectService directService = new DirectService(lotDao,bidDao);
            UserManageService manageService = new UserManageService(userDao);
            LoginService loginService = new LoginService(userDao,roleDao);
            this.command = new LoginCommand(directService,manageService, loginService,userListCreator,lotsListCreator);
        }
    },
    LOGIN_PAGE{
        {
            this.command = new LoginPageCommand();
        }
    },
    REGISTRATION_PAGE{
        {
            this.command = new RegistrationPageCommand();
        }
    },
    REGISTRATION{
        {
            UserDao userDao = new UserDao();
            RegistrationService service = new RegistrationService(userDao);
            this.command = new RegistrationCommand(service);
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    DIRECT_BID{
        {
            UserDao userDao = new UserDao();
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            DirectService directService = new DirectService(lotDao,bidDao);
            DirectBiddingService biddingService = new DirectBiddingService(userDao,lotDao,bidDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            ActualBalanceService balanceService = new ActualBalanceService(userDao);
            this.command = new DirectBidCommand(directService,biddingService,creator,balanceService);
        }
    },
    REVERSE_BID{
        {
            UserDao userDao = new UserDao();
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            ReverseService reverseService = new ReverseService(lotDao,bidDao);
            ReverseBiddingService biddingService = new ReverseBiddingService(userDao,lotDao,bidDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            ActualBalanceService balanceService = new ActualBalanceService(userDao);
            this.command = new ReverseBidCommand(reverseService,biddingService,creator,balanceService);
        }
    },
    BACK{
        {
            this.command = new BackCommand();
        }
    },
    DIRECT{
        {
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            DirectService service = new DirectService(lotDao,bidDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            this.command = new DirectCommand(service,creator);
        }

    },
    REVERSE{
        {
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            ReverseService service = new ReverseService(lotDao,bidDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            this.command = new ReverseCommand(service,creator);
        }
    },
    LOTS{
        {
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            LotsService service = new LotsService(lotDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            this.command = new LotsCommand(service,creator);
        }

    },
    BIDDING{
        {
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            BiddingService service = new BiddingService(lotDao,bidDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            this.command= new BiddingCommand(service,creator);
        }

    },
    CHANGELANG{
        {
            this.command = new ChangeLangCommand();
        }

    },
    ADD_LOT{
        {
            this.command = new AddLotCommand();
        }
    },
    OFFER_LOT{
        {
            BaseDao<Lot> dao = new LotDao();
            AddLotService service = new AddLotService(dao);
            this.command = new OfferLotCommand(service);
        }
    },
    USER_MANAGE{
        {
            UserDao userDao = new UserDao();
            RoleDao roleDao = new RoleDao();
            UserManageService service = new UserManageService(userDao);
            UserDtoAssembler assembler = new UserDtoAssembler(roleDao);
            UserListCreator creator = new UserListCreator(assembler);
            this.command = new UserManageCommand(service,creator);
        }
    },
    USER_EDIT_PAGE{
        {
            UserDao userDao = new UserDao();
            RoleDao roleDao = new RoleDao();
            UserDtoAssembler assembler = new UserDtoAssembler(roleDao);
            UserEditPageService service = new UserEditPageService(userDao,assembler);
            this.command = new UserEditPageCommand(service);
        }
    },
    USER_EDIT{
        {
            UserDao userDao = new UserDao();
            RoleDao roleDao = new RoleDao();
            UserDtoAssembler assembler = new  UserDtoAssembler(roleDao);
            UserListCreator creator = new  UserListCreator(assembler);
            UserEditService editService = new UserEditService(userDao,roleDao);
            UserManageService manageService = new UserManageService(userDao);
            this.command = new UserEditCommand(editService,manageService,creator);

        }
    },
    LOT_MANAGE{
        {
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            LotManageService service = new LotManageService(lotDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            this.command = new LotManageCommand(service,creator);
        }
    },
    LOT_EDIT_PAGE{
        {

            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotEditPageService service = new LotEditPageService(lotDao,assembler);
            this.command = new LotEditPageCommand(service);
        }
    },
    LOT_EDIT{
        {
            LotDao lotDao = new LotDao();
            LotStateDao stateDao = new LotStateDao();
            BidDao bidDao = new BidDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            LotEditService editService = new LotEditService(lotDao,stateDao);
            LotManageService manageService = new LotManageService(lotDao);
            this.command = new LotEditCommand(manageService,editService,creator);
        }
    },
    DELETE_LOT{
        {
            LotDao lotDao = new LotDao();
            BidDao bidDao = new BidDao();
            UserDao userDao = new UserDao();
            AuctionTypeDao typeDao =new AuctionTypeDao();
            LotStateDao stateDao = new LotStateDao();
            LotDeleteService deleteService = new LotDeleteService(lotDao,bidDao,userDao);
            LotManageService manageService = new LotManageService(lotDao);
            LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
            LotsListCreator creator = new LotsListCreator(assembler);
            this.command = new DeleteLotCommand(deleteService,manageService,creator);
        }
    };
    private ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
