package com.epam.auction.command.bidCommand;

import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.ActualBalanceService;
import com.epam.auction.service.DirectService;
import com.epam.auction.service.bidding.DirectBiddingService;
import com.epam.auction.service.creator.LotsListCreator;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.times;



public class DirectBidCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private DirectService directService;

    @Mock
    private DirectBiddingService biddingService;

    @Mock
    private LotsListCreator creator;

    @Mock
    private ActualBalanceService balanceService;

    @Mock
    private User user;

    @Mock
    private Lot lot;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectPageWhenCorrectLotParameter() throws CommandException, LogicException {

        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.getBiddingLot(anyInt())).thenReturn(lot);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.doBid(anyInt(), anyInt())).thenReturn(true);
        Mockito.when(directService.getDirectLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(TestInfo.LOT_LIST)).thenReturn(TestInfo.LOT_DTO_LIST);
        Mockito.when(balanceService.getActualUserInfo(anyInt())).thenReturn(user);

        DirectBidCommand command = new DirectBidCommand(directService, biddingService, creator, balanceService);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_DIRECT_AUCTION);

        Mockito.verify(request, times(1)).getParameter(any(String.class));
        Mockito.verify(request, times(1)).getSession(true);
        Mockito.verify(user, times(1)).getId();
        Mockito.verify(biddingService, times(1)).doBid(anyInt(), anyInt());
        Mockito.verify(directService, times(1)).getDirectLotList(anyInt());
        Mockito.verify(creator, times(1)).createListDto(TestInfo.LOT_LIST);
        Mockito.verify(balanceService, times(1)).getActualUserInfo(anyInt());
        Mockito.verify(lot, times(1)).getId();
        Mockito.verify(biddingService, times(1)).getBiddingLot(anyInt());
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenBiddingServiceThrowLogicException() throws CommandException, LogicException {
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.getBiddingLot(anyInt())).thenReturn(lot);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.doBid(anyInt(), anyInt())).thenThrow(new LogicException());

        DirectBidCommand command = new DirectBidCommand(directService, biddingService, creator, balanceService);
        String actual = command.execute(request);

    }


    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenGetBiddingLotThrowLogicException() throws CommandException, LogicException {
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(biddingService.getBiddingLot(anyInt())).thenThrow(new LogicException());

        DirectBidCommand command = new DirectBidCommand(directService, biddingService, creator, balanceService);
        String actual = command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenDirectServiceThrowLogicException() throws CommandException, LogicException {
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.getBiddingLot(anyInt())).thenReturn(lot);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.doBid(anyInt(), anyInt())).thenReturn(true);
        Mockito.when(directService.getDirectLotList(anyInt())).thenThrow(new LogicException());

        DirectBidCommand command = new DirectBidCommand(directService, biddingService, creator, balanceService);
        String actual = command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowLogicException() throws CommandException, LogicException {
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.getBiddingLot(anyInt())).thenReturn(lot);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.doBid(anyInt(), anyInt())).thenReturn(true);
        Mockito.when(directService.getDirectLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(TestInfo.LOT_LIST)).thenThrow(new LogicException());

        DirectBidCommand command = new DirectBidCommand(directService, biddingService, creator, balanceService);
        String actual = command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenBalanceServiceThrowLogicException() throws CommandException, LogicException {
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.getBiddingLot(anyInt())).thenReturn(lot);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(biddingService.doBid(anyInt(), anyInt())).thenReturn(true);
        Mockito.when(directService.getDirectLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(TestInfo.LOT_LIST)).thenReturn(TestInfo.LOT_DTO_LIST);
        Mockito.when(balanceService.getActualUserInfo(anyInt())).thenThrow(new LogicException());

        DirectBidCommand command = new DirectBidCommand(directService, biddingService, creator, balanceService);
        String actual = command.execute(request);

    }
}
