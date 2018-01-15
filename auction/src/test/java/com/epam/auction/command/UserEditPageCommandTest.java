package com.epam.auction.command;

import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.UserEditPageService;
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

public class UserEditPageCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserEditPageService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnEditPageWhenParameterNotNull()throws CommandException,LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(service.getChangedUser(anyInt())).thenReturn(TestInfo.USER_DTO);

        ActionCommand command = new UserEditPageCommand(service);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_USER_EDIT);

        Mockito.verify(request,Mockito.times(1)).getParameter(anyString());
        Mockito.verify(request,Mockito.times(1)).getSession(true);
        Mockito.verify(service,Mockito.times(1)).getChangedUser(anyInt());
        Mockito.verify(session,Mockito.times(1)).setAttribute(anyString(),any());
    }

    @Test
    public void shouldReturnManagePageWhenParameterNull() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(null);

        ActionCommand command = new UserEditPageCommand(service);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_USER_MANAGE);

        Mockito.verify(request,Mockito.times(1)).getParameter(anyString());
        Mockito.verify(request,Mockito.never()).getSession(true);
        Mockito.verify(service,Mockito.never()).getChangedUser(anyInt());
        Mockito.verify(session,Mockito.never()).setAttribute(anyString(),any());
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenServiceThrowLogicException() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT_FOR_INTEGER_PARS);
        Mockito.when(service.getChangedUser(anyInt())).thenThrow(new LogicException());

        ActionCommand command = new UserEditPageCommand(service);
        command.execute(request);

    }
}
