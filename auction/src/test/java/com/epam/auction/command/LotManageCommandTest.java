package com.epam.auction.command;

import com.epam.auction.exceptions.CommandException;
import com.epam.auction.exceptions.LogicException;
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

public class LotManageCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private LotManageService manageService;

    @Mock
    private LotsListCreator creator;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnLotManagePageWhenCorrectWork() throws CommandException,LogicException{

        Mockito.when(manageService.getLots()).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(any())).thenReturn(TestInfo.LOT_DTO_LIST);
        Mockito.when(request.getSession(true)).thenReturn(session);


        ActionCommand command = new LotManageCommand(manageService,creator);
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_LOT_MANAGE);

        Mockito.verify(manageService,Mockito.times(1)).getLots();
        Mockito.verify(creator,Mockito.times(1)).createListDto(any());
        Mockito.verify(request,Mockito.times(1)).getSession(true);
        Mockito.verify(session,Mockito.times(1)).setAttribute(anyString(),any());

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenManageServiceThrowsLogicException()
            throws CommandException,LogicException{
        Mockito.when(manageService.getLots()).thenThrow(new LogicException());

        ActionCommand command = new LotManageCommand(manageService,creator);
        command.execute(request);

    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenCreatorThrowsLogicException()
            throws CommandException,LogicException{

        Mockito.when(manageService.getLots()).thenReturn(TestInfo.LOT_LIST);
        Mockito.when(creator.createListDto(any())).thenThrow(new LogicException());


        ActionCommand command = new LotManageCommand(manageService,creator);
        command.execute(request);
    }
}
