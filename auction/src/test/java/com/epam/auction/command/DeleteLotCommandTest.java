package com.epam.auction.command;

import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.LotDeleteService;
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

public class DeleteLotCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private LotDeleteService deleteService;

    @Mock
    private LotManageService manageService;

    @Mock
    private LotsListCreator creator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnManagePageWhenLotDeleted() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(deleteService.deleteLot(any())).thenReturn(true);
        Mockito.when( manageService.getLots()).thenReturn(null);
        Mockito.when( creator.createListDto(any())).thenReturn(null);

        DeleteLotCommand command = new DeleteLotCommand(deleteService,manageService,creator);
        String actual = command.execute(request);

        Assert.assertEquals(TestInfo.TEST_PAGE_LOT_MANAGE,actual);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenLotNotDeleted() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(deleteService.deleteLot(any())).thenReturn(false);
        Mockito.when(manageService.getLots()).thenReturn(null);
        Mockito.when(creator.createListDto(any())).thenReturn(null);

        DeleteLotCommand command = new DeleteLotCommand(deleteService,manageService,creator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenDeleteServiceThrowLogicException() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(deleteService.deleteLot(any())).thenThrow(new LogicException());

        DeleteLotCommand command = new DeleteLotCommand(deleteService,manageService,creator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenManageServiceThrowLogicException() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(deleteService.deleteLot(any())).thenReturn(true);
        Mockito.when(manageService.getLots()).thenThrow(new LogicException());

        DeleteLotCommand command = new DeleteLotCommand(deleteService,manageService,creator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowLogicException() throws CommandException,LogicException{

        Mockito.when(request.getSession(true)).thenReturn(session);
        Mockito.when(session.getAttribute(anyString())).thenReturn(TestInfo.LOT_DTO);
        Mockito.when(deleteService.deleteLot(any())).thenReturn(true);
        Mockito.when(manageService.getLots()).thenReturn(null);
        Mockito.when(creator.createListDto(any())).thenThrow(new LogicException());

        DeleteLotCommand command = new DeleteLotCommand(deleteService,manageService,creator);
        command.execute(request);

    }
}
