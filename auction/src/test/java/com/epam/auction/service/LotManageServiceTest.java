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


public class LotManageServiceTest {

    @Mock
    private LotDao lotDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectLotList() throws LogicException,DAOException {

        Mockito.when(lotDao.findAll()).thenReturn(TestInfo.LOT_LIST);

        LotManageService service = new LotManageService(lotDao);
        List<Lot> actual = service.getLots();

        Assert.assertEquals(TestInfo.LOT_LIST,actual);

        Mockito.verify(lotDao,Mockito.times(1)).findAll();

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowDaoException() throws LogicException,DAOException {

        Mockito.when(lotDao.findAll()).thenThrow(new DAOException());

        LotManageService service = new LotManageService(lotDao);
        service.getLots();

    }

}
