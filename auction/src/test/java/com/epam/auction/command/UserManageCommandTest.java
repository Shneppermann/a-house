package com.epam.auction.command;

import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.UserManageService;
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

public class UserManageCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserManageService manageService;

    @Mock
    private UserListCreator creator;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnLotManagePageWhenCorrectWork() throws CommandException,LogicException {

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(manageService.getUserList(anyInt())).thenReturn(TestInfo.USER_LIST);
        Mockito.when(creator.createListDto(any())).thenReturn(TestInfo.USER_DTO_LIST);



        ActionCommand command = new UserManageCommand(manageService,creator);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_USER_MANAGE);

        Mockito.verify(manageService,Mockito.times(1)).getUserList(anyInt());
        Mockito.verify(creator,Mockito.times(1)).createListDto(any());
        Mockito.verify(request,Mockito.times(1)).getSession(true);
        Mockito.verify(session,Mockito.times(1)).setAttribute(anyString(),any());
        Mockito.verify(session,Mockito.times(1)).getAttribute(anyString());
        Mockito.verify(user,Mockito.times(1)).getId();

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenManageServiceThrowsLogicException()
            throws CommandException,LogicException{
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(manageService.getUserList(anyInt())).thenThrow(new LogicException());

        ActionCommand command = new UserManageCommand(manageService,creator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowsLogicException()
            throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(user);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(manageService.getUserList(anyInt())).thenReturn(TestInfo.USER_LIST);
        Mockito.when(creator.createListDto(any())).thenThrow(new LogicException());


        ActionCommand command = new UserManageCommand(manageService,creator);
        command.execute(request);
    }
}
