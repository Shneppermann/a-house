package com.epam.auction.command.checkCommand;


import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.AddLotService;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import static org.mockito.Mockito.*;

public class OfferLotCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private AddLotService service;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectPageWhenCorrectParameters() throws LogicException,CommandException{


        Mockito.when(session.getAttribute( anyString())).thenReturn(user);
        Mockito.when(request.getParameter( anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(service.addLot( anyInt(), anyString(), anyString(), anyString(),anyString())).thenReturn(true);

        OfferLotCommand command = new OfferLotCommand(service);
        String actual = command.execute(request);

        Assert.assertEquals(actual,TestInfo.TEST_PAGE_ADD_LOT);


        Mockito.verify(request, times(4)).getParameter(any(String.class));
        Mockito.verify(request,times(1)).getSession(true);
        Mockito.verify(user,times(1)).getId();
        Mockito.verify(service,times(1)).addLot(anyInt(), anyString(), anyString(), anyString(),anyString());
        Mockito.verify(session,times(1)).getAttribute(anyString());

    }

    @Test
    public void shouldNotCallMethodsWhenParametersEmpty()  throws LogicException,CommandException {


        Mockito.when(session.getAttribute( anyString())).thenReturn(user);
        Mockito.when(request.getParameter( anyString())).thenReturn(TestInfo.EMPTY_STRING);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(service.addLot( anyInt(), anyString(), anyString(), anyString(),anyString())).thenReturn(true);

        OfferLotCommand command = new OfferLotCommand(service);
        String actual = command.execute(request);

        Assert.assertEquals(actual,TestInfo.TEST_PAGE_ADD_LOT);

        Mockito.verify(request, times(4)).getParameter(any(String.class));
        Mockito.verify(request,times(1)).getSession(true);
        Mockito.verify(user,never()).getId();
        Mockito.verify(service,never()).addLot(anyInt(), anyString(), anyString(), anyString(),anyString());
        Mockito.verify(session,times(1)).getAttribute(anyString());


    }


    @Test (expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenLogicExceptionCatch() throws LogicException,CommandException{

        Mockito.when(session.getAttribute( anyString())).thenReturn(user);
        Mockito.when(request.getParameter( anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(service.addLot( anyInt(), anyString(), anyString(), anyString(),anyString())).thenThrow(new LogicException());

        OfferLotCommand command = new OfferLotCommand(service);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenAddLotReturnFalse() throws LogicException,CommandException{

        Mockito.when(session.getAttribute( anyString())).thenReturn(user);
        Mockito.when(request.getParameter( anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(service.addLot( anyInt(), anyString(), anyString(), anyString(),anyString())).thenReturn(false);

        OfferLotCommand command = new OfferLotCommand(service);
        command.execute(request);
    }
}

