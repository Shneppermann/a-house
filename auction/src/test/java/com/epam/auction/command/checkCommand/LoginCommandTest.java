package com.epam.auction.command.checkCommand;

import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.DirectService;
import com.epam.auction.service.LoginService;
import com.epam.auction.service.UserManageService;
import com.epam.auction.service.creator.LotsListCreator;
import com.epam.auction.service.creator.UserListCreator;
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

public class LoginCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private DirectService directService;

    @Mock
    private UserManageService manageService;

    @Mock
    private LoginService loginService;

    @Mock
    private UserListCreator userCreator;

    @Mock
    private LotsListCreator lotsCreator;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnUserManagePathWhenUserIsAdmin() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenReturn(user);
        Mockito.when(loginService.isAdmin(any())).thenReturn(true);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(manageService.getUserList(anyInt())).thenReturn(TestInfo.USER_LIST);
        Mockito.when(userCreator.createListDto(any())).thenReturn(TestInfo.USER_DTO_LIST);


        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        String actual = command.execute(request);

        Assert.assertEquals(TestInfo.TEST_PAGE_USER_MANAGE,actual);

        Mockito.verify(session,Mockito.times(3)).setAttribute(anyString(),any());
    }

    @Test
    public void shouldReturnDirectAuctionPathWhenUserIsNotAdmin() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenReturn(user);
        Mockito.when(loginService.isAdmin(any())).thenReturn(false);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(directService.getDirectLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(lotsCreator.createListDto(any())).thenReturn(TestInfo.LOT_DTO_LIST);


        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        String actual = command.execute(request);

        Assert.assertEquals(TestInfo.TEST_PAGE_DIRECT_AUCTION,actual);

        Mockito.verify(session,Mockito.times(3)).setAttribute(anyString(),any());
    }

    @Test
    public void shouldReturnLoginPagePathWhenEmptyLogin() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.EMPTY_STRING);
        Mockito.when(request.getSession(true)).thenReturn(session);

        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        String actual = command.execute(request);

        Assert.assertEquals(TestInfo.TEST_PAGE_LOGIN,actual);

        Mockito.verify(session,Mockito.times(2)).setAttribute(anyString(),any());
    }

    @Test
    public void shouldReturnLoginPagePathWhenLoginServiceReturnsNull() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenReturn(null);

        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        String actual = command.execute(request);

        Assert.assertEquals(TestInfo.TEST_PAGE_LOGIN,actual);

        Mockito.verify(session,Mockito.times(2)).setAttribute(anyString(),any());
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenLoginServiceThrowsLogicException() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenThrow(new LogicException());

        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenManageServiceThrowsLogicException() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenReturn(user);
        Mockito.when(loginService.isAdmin(any())).thenReturn(true);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(manageService.getUserList(anyInt())).thenThrow(new LogicException());


        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenUserCreatorThrowsLogicException() throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenReturn(user);
        Mockito.when(loginService.isAdmin(any())).thenReturn(true);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(manageService.getUserList(anyInt())).thenReturn(TestInfo.USER_LIST);
        Mockito.when(userCreator.createListDto(any())).thenThrow(new LogicException());


        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenDirectServiceThrowsLogicException()
            throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenReturn(user);
        Mockito.when(loginService.isAdmin(any())).thenReturn(false);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(directService.getDirectLotList(anyInt())).thenThrow(new LogicException());


        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenLotsServiceThrowsLogicException()
            throws CommandException,LogicException{

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(loginService.getUser(anyString(), anyString())).thenReturn(user);
        Mockito.when(loginService.isAdmin(any())).thenReturn(false);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(directService.getDirectLotList(anyInt())).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(lotsCreator.createListDto(any())).thenThrow(new LogicException());


        LoginCommand command = new LoginCommand(directService,manageService,loginService,userCreator,lotsCreator);
        command.execute(request);

    }

}
