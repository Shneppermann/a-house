package com.epam.auction.command;


import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.DirectService;
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

public class DirectCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private DirectService service;

    @Mock
    private LotsListCreator creator;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPageWhenCorrectAttributes() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(service.getDirectLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(TestInfo.LOT_LIST)).thenReturn(TestInfo.LOT_DTO_LIST);

        ActionCommand command = new DirectCommand(service,creator);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_DIRECT_AUCTION);

        Mockito.verify(request,Mockito.times(1)).getSession(true);
        Mockito.verify(session,Mockito.times(1)).getAttribute(anyString());
        Mockito.verify(session,Mockito.times(3)).setAttribute(anyString(),any());
        Mockito.verify(service,Mockito.times(1)).getDirectLotList(anyInt());
        Mockito.verify(creator,Mockito.times(1)).createListDto(TestInfo.LOT_LIST);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenServiceThrowsLogicException() throws CommandException,LogicException{
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(service.getDirectLotList(anyInt())).thenThrow(new LogicException());

        ActionCommand command = new DirectCommand(service,creator);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowsLogicException() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(service.getDirectLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(TestInfo.LOT_LIST)).thenThrow(new LogicException());

        ActionCommand command = new DirectCommand(service,creator);
        command.execute(request);
    }

}
