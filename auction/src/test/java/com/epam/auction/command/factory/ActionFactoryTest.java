package com.epam.auction.command.factory;

import com.epam.auction.command.ActionCommand;
import com.epam.auction.command.DirectCommand;
import com.epam.auction.command.EmptyCommand;
import com.epam.auction.command.ReverseCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;

public class ActionFactoryTest {

    private static final String DIRECT = "Direct";
    private static final String REVERSE ="Reverse";

    @Mock
    private HttpServletRequest request;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnEmptyCommandWhenActionNull(){

        Mockito.when(request.getParameter(any())).thenReturn(null);

        ActionFactory factory = new ActionFactory();
        ActionCommand command = factory.defineCommand(request);

        Assert.assertThat(command,instanceOf(EmptyCommand.class));
    }

    @Test
    public void shouldReturnEmptyCommandWhenActionIsEmpty(){
        Mockito.when(request.getParameter(any())).thenReturn("");

        ActionFactory factory = new ActionFactory();
        ActionCommand command = factory.defineCommand(request);

        Assert.assertThat(command,instanceOf(EmptyCommand.class));
    }

    @Test
    public void shouldReturnDirectCommandWhenActionIsDirect(){
        Mockito.when(request.getParameter(any())).thenReturn(DIRECT);

        ActionFactory factory = new ActionFactory();
        ActionCommand command = factory.defineCommand(request);

        Assert.assertThat(command,instanceOf(DirectCommand.class));
    }

    @Test
    public void shouldReturnReverseCommandWhenActionIsReverse(){
        Mockito.when(request.getParameter(any())).thenReturn(REVERSE);

        ActionFactory factory = new ActionFactory();
        ActionCommand command = factory.defineCommand(request);

        Assert.assertThat(command,instanceOf(ReverseCommand.class));
    }

}
