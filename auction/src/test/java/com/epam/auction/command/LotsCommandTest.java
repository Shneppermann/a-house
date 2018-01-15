package com.epam.auction.command;

import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.LotsService;
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

public class LotsCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private LotsService service;

    @Mock
    private LotsListCreator creator;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectPageWhenCorrectAttributes() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(service.getSelfLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(any())).thenReturn(TestInfo.LOT_DTO_LIST);

        ActionCommand command = new LotsCommand(service,creator);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_LOTS);

        Mockito.verify(request,Mockito.times(1)).getSession(true);
        Mockito.verify(session,Mockito.times(1)).getAttribute(anyString());
        Mockito.verify(service,Mockito.times(1)).getSelfLotList(any());
        Mockito.verify(creator,Mockito.times(1)).createListDto(any());
        Mockito.verify(session,Mockito.times(2)).setAttribute(anyString(),any());

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenServiceThrowsLogicException() throws CommandException,LogicException{
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(service.getSelfLotList(anyInt())).thenThrow(new LogicException());


        ActionCommand command = new LotsCommand(service,creator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowsLogicException() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(service.getSelfLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(any())).thenThrow(new LogicException());

        ActionCommand command = new LotsCommand(service,creator);
        command.execute(request);

    }
}
