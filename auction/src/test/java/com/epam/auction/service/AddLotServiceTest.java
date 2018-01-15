package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class AddLotServiceTest {

    @Mock
    private LotDao lotDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnTrueWhenCorrectParameter() throws LogicException,DAOException{

        Mockito.when(lotDao.create(any())).thenReturn(true);

        AddLotService service = new AddLotService(lotDao);
        boolean actual = service.addLot(TestInfo.INT_RESULT,TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT_FOR_INTEGER_PARS,TestInfo.STRING_RESULT_FOR_INTEGER_PARS, TestInfo.STRING_RESULT);

        Assert.assertEquals(true,actual);

        Mockito.verify(lotDao,Mockito.times(1)).create(any());
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenNumberFormatExceptionOccurred() throws LogicException,DAOException{

        Mockito.when(lotDao.create(any())).thenReturn(true);

        AddLotService service = new AddLotService(lotDao);
        service.addLot(TestInfo.INT_RESULT,TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT,TestInfo.STRING_RESULT_FOR_INTEGER_PARS, TestInfo.STRING_RESULT);
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowsDaoException()throws LogicException,DAOException{
        Mockito.when(lotDao.create(any())).thenThrow(new DAOException());

        AddLotService service = new AddLotService(lotDao);
        service.addLot(TestInfo.INT_RESULT,TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT_FOR_INTEGER_PARS,TestInfo.STRING_RESULT_FOR_INTEGER_PARS, TestInfo.STRING_RESULT);
    }
}
