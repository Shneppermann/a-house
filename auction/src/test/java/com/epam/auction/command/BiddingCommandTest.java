package com.epam.auction.command;

import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.BiddingService;
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


public class BiddingCommandTest {

    @Mock
    private BiddingService service;

    @Mock
    private LotsListCreator creator;

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectPageWhenCorrectAttributes()throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(service.getSelfBidList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(TestInfo.LOT_LIST)).thenReturn(TestInfo.LOT_DTO_LIST);

        BiddingCommand command = new BiddingCommand(service,creator);
        String actual = command.execute(request);

        Assert.assertEquals(actual,TestInfo.TEST_PAGE_BIDDING);

        Mockito.verify(request, times(1)).getSession(true);
        Mockito.verify(session,times(1)).getAttribute(anyString());
        Mockito.verify(service,times(1)).getSelfBidList(anyInt());
        Mockito.verify(creator,times(1)).createListDto(TestInfo.LOT_LIST);
        Mockito.verify(session,times(2)).setAttribute(anyString(),any());
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenServiceThrowsLogicException()throws CommandException,LogicException{
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(service.getSelfBidList(anyInt())).thenThrow(new LogicException());

        BiddingCommand command = new BiddingCommand(service,creator);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowsLogicException()throws CommandException,LogicException{
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(service.getSelfBidList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(TestInfo.LOT_LIST)).thenThrow(new LogicException());

        BiddingCommand command = new BiddingCommand(service,creator);
        command.execute(request);
    }

}
