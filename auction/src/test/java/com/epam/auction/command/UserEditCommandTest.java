package com.epam.auction.command;

import com.epam.auction.entity.User;
import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.UserEditService;
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

public class UserEditCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserManageService manageService;

    @Mock
    private UserEditService editService;

    @Mock
    private UserListCreator creator;

    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPageWhenCorrectParameters() throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(TestInfo.USER_EDIT_ATTR)).thenReturn(TestInfo.USER_DTO);
        Mockito.when(session.getAttribute(TestInfo.USER_ATTR)).thenReturn(TestInfo.USER);
        Mockito.when(editService.changeUserRole(any(), anyString())).thenReturn(true);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(manageService.getUserList(anyInt())).thenReturn(TestInfo.USER_LIST);
        Mockito.when(creator.createListDto(any())).thenReturn(TestInfo.USER_DTO_LIST);


        ActionCommand command = new UserEditCommand( editService, manageService, creator);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_USER_MANAGE);

        Mockito.verify(request, Mockito.times(1)).getParameter(anyString());
        Mockito.verify(request, Mockito.times(1)).getSession(true);
        Mockito.verify(session, Mockito.times(2)).getAttribute(anyString());
        Mockito.verify(editService, Mockito.times(1)).changeUserRole(any(), anyString());
        Mockito.verify(manageService, Mockito.times(1)).getUserList(anyInt());
        Mockito.verify(creator, Mockito.times(1)).createListDto(any());

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenEditServiceFalse() throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(TestInfo.USER_EDIT_ATTR)).thenReturn(TestInfo.USER_DTO);
        Mockito.when(session.getAttribute(TestInfo.USER_ATTR)).thenReturn(TestInfo.USER);
        Mockito.when(editService.changeUserRole(any(), anyString())).thenReturn(false);


        ActionCommand command = new UserEditCommand( editService, manageService, creator);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenEditServiceThrowsCommandException()
            throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(TestInfo.USER_EDIT_ATTR)).thenReturn(TestInfo.USER_DTO);
        Mockito.when(session.getAttribute(TestInfo.USER_ATTR)).thenReturn(TestInfo.USER);
        Mockito.when(editService.changeUserRole(any(), anyString())).thenThrow(new LogicException());


        ActionCommand command = new UserEditCommand( editService, manageService, creator);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenManageServiceThrowsLogicException()
            throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(TestInfo.USER_EDIT_ATTR)).thenReturn(TestInfo.USER_DTO);
        Mockito.when(session.getAttribute(TestInfo.USER_ATTR)).thenReturn(TestInfo.USER);
        Mockito.when(editService.changeUserRole(any(), anyString())).thenReturn(true);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(manageService.getUserList(anyInt())).thenThrow(new LogicException());


        ActionCommand command = new UserEditCommand( editService, manageService, creator);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowsLogicException()
            throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(TestInfo.USER_EDIT_ATTR)).thenReturn(TestInfo.USER_DTO);
        Mockito.when(session.getAttribute(TestInfo.USER_ATTR)).thenReturn(TestInfo.USER);
        Mockito.when(editService.changeUserRole(any(), anyString())).thenReturn(true);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(manageService.getUserList(anyInt())).thenReturn(TestInfo.USER_LIST);
        Mockito.when(creator.createListDto(any())).thenThrow(new LogicException());


        ActionCommand command = new UserEditCommand( editService, manageService, creator);
        command.execute(request);
    }
}
