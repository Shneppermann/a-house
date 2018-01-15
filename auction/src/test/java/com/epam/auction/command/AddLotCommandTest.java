package com.epam.auction.command;

import com.epam.auction.exceptions.CommandException;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

public class AddLotCommandTest {

    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectPage() throws CommandException{
        AddLotCommand command = new AddLotCommand();
        String actual = command.execute(request);

        Assert.assertEquals(actual, TestInfo.TEST_PAGE_ADD_LOT);
    }
}
