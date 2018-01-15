package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;


import static org.mockito.ArgumentMatchers.anyInt;

public class LotsServiceTest {
    @Mock
    private LotDao lotDao;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectLotList() throws LogicException,DAOException {

        Mockito.when(lotDao.findAllPersonalLots(anyInt())).thenReturn(TestInfo.LOT_LIST);


        LotsService service = new LotsService(lotDao);
        List<Lot> actual = service.getSelfLotList(TestInfo.INT_RESULT);

        Assert.assertEquals(TestInfo.LOT_LIST,actual);

        Mockito.verify(lotDao,Mockito.times(1)).findAllPersonalLots(anyInt());


    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(lotDao.findAllPersonalLots(anyInt())).thenThrow(new DAOException());


        LotsService service = new LotsService(lotDao);
        service.getSelfLotList(TestInfo.INT_RESULT);
    }
}
