package com.epam.auction.command.checkCommand;

import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.RegistrationService;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;

public class RegistrationCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private RegistrationService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnLoginPageWhenCorrectParametersAndUserCreate() throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(null);
        Mockito.when(service.createNewUser(anyString(),anyString(),anyString(),anyString())).thenReturn(true);

        RegistrationCommand command = new RegistrationCommand(service);
        String actual = command.execute(request);

        Assert.assertEquals(TestInfo.TEST_PAGE_LOGIN,actual);
    }

    @Test
    public void shouldReturnRegistrationPageWhenCorrectParametersAndUserNotCreate() throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(null);
        Mockito.when(service.createNewUser(anyString(),anyString(),anyString(),anyString())).thenReturn(false);

        RegistrationCommand command = new RegistrationCommand(service);
        String actual = command.execute(request);

        Assert.assertEquals(TestInfo.TEST_PAGE_REGISTRATE,actual);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenServiceThrowsLogicException() throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(null);
        Mockito.when(service.createNewUser(anyString(),anyString(),anyString(),anyString())).thenThrow(new LogicException());

        RegistrationCommand command = new RegistrationCommand(service);
        command.execute(request);

    }
}
