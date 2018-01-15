package com.epam.auction.command;

import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.LotEditService;
import com.epam.auction.service.LotManageService;
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
import static org.mockito.ArgumentMatchers.anyString;

public class LotEditCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private LotManageService manageService;

    @Mock
    private LotEditService editService;

    @Mock
    private LotsListCreator creator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPageWhenCorrectParameters() throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(editService.changeLot(any(), anyString())).thenReturn(true);
        Mockito.when(manageService.getLots()).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(any())).thenReturn(TestInfo.LOT_DTO_LIST);

        LotEditCommand command = new LotEditCommand(manageService, editService, creator);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_LOT_MANAGE);

        Mockito.verify(request, Mockito.times(1)).getParameter(anyString());
        Mockito.verify(request, Mockito.times(1)).getSession(true);
        Mockito.verify(session, Mockito.times(1)).getAttribute(anyString());
        Mockito.verify(editService, Mockito.times(1)).changeLot(any(), anyString());
        Mockito.verify(manageService, Mockito.times(1)).getLots();
        Mockito.verify(creator, Mockito.times(1)).createListDto(any());

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenEditServiceFalse() throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(editService.changeLot(any(), anyString())).thenReturn(false);


        LotEditCommand command = new LotEditCommand(manageService, editService, creator);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenEditServiceThrowsCommandException()
            throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(editService.changeLot(any(), anyString())).thenThrow(new LogicException());


        LotEditCommand command = new LotEditCommand(manageService, editService, creator);
        command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenManageServiceThrowsLogicException()
            throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(editService.changeLot(any(), anyString())).thenReturn(true);
        Mockito.when(manageService.getLots()).thenThrow(new LogicException());


        LotEditCommand command = new LotEditCommand(manageService, editService, creator);
        String actual = command.execute(request);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowsLogicException()
            throws CommandException, LogicException {

        Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(editService.changeLot(any(), anyString())).thenReturn(true);
        Mockito.when(manageService.getLots()).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(any())).thenThrow(new LogicException());

        LotEditCommand command = new LotEditCommand(manageService, editService, creator);
        command.execute(request);
    }
}
