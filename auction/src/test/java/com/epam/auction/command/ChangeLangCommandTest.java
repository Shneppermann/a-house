package com.epam.auction.command;

import com.epam.auction.exceptions.CommandException;
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

public class ChangeLangCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCorrectExecuteWhenCorrectAttributes() throws CommandException{

       Mockito.when(request.getSession(true)).thenReturn(session);
       Mockito.when(request.getParameter(anyString())).thenReturn(TestInfo.STRING_RESULT);

       ActionCommand command = new ChangeLangCommand();
       String actual = command.execute(request);

       Assert.assertEquals(actual,TestInfo.STRING_RESULT);

       Mockito.verify(request,Mockito.times(2)).getParameter(anyString());
       Mockito.verify(session,Mockito.times(3)).setAttribute(anyString(),any());
       Mockito.verify(request,Mockito.times(1)).getSession(true);

    }

}
