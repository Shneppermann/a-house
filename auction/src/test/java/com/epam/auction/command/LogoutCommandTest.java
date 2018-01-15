package com.epam.auction.command;

import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInvalidateSessionAndReturnIndexPage(){

        Mockito.when(request.getSession()).thenReturn(session);

        LogoutCommand command = new LogoutCommand();
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_INDEX);

        Mockito.verify(request,Mockito.times(1)).getSession();
        Mockito.verify(session,Mockito.times(1)).invalidate();

    }
}
