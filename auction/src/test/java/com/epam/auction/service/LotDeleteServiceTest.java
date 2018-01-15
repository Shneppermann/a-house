package com.epam.auction.service;

import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class LotDeleteServiceTest {

    private static final BigDecimal BALANCE = new BigDecimal(5);

    @Mock
    private LotDao lotDao;
    @Mock
    private BidDao bidDao;
    @Mock
    private UserDao userDao;
    @Mock
    private LotDto lot;
    @Mock
    private User user;
    @Mock
    private Bid bid;

    private List<Bid> bids = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bids.add(bid);
    }

    @Test
    public void shouldReturnTrueWhenCorrectWork() throws LogicException,DAOException{

        Mockito.when(lot.getBidOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getBid()).thenReturn(BALANCE);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(userDao.update(any())).thenReturn(user);
        Mockito.when(bidDao.findAll()).thenReturn(bids);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.delete(anyInt())).thenReturn(true);
        Mockito.when(lotDao.delete(anyInt())).thenReturn(true);


        LotDeleteService service = new LotDeleteService(lotDao,bidDao,userDao);
        boolean actual = service.deleteLot(lot);

        Assert.assertEquals(true,actual);
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(lot.getBidOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getBid()).thenReturn(BALANCE);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(anyInt())).thenThrow(new DAOException());

        LotDeleteService service = new LotDeleteService(lotDao,bidDao,userDao);
        service.deleteLot(lot);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoReturnsNull() throws LogicException,DAOException{

        Mockito.when(lot.getBidOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getBid()).thenReturn(BALANCE);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(userDao.update(any())).thenReturn(null);

        LotDeleteService service = new LotDeleteService(lotDao,bidDao,userDao);
        service.deleteLot(lot);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenBidDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(lot.getBidOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getBid()).thenReturn(BALANCE);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(userDao.update(any())).thenReturn(user);
        Mockito.when(bidDao.findAll()).thenThrow(new DAOException());

        LotDeleteService service = new LotDeleteService(lotDao,bidDao,userDao);
        service.deleteLot(lot);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(lot.getBidOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getBid()).thenReturn(BALANCE);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(userDao.update(any())).thenReturn(user);
        Mockito.when(bidDao.findAll()).thenReturn(bids);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.delete(anyInt())).thenReturn(true);
        Mockito.when(lotDao.delete(anyInt())).thenThrow(new DAOException());


        LotDeleteService service = new LotDeleteService(lotDao,bidDao,userDao);
        service.deleteLot(lot);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenBidDaoDeleteReturnsFalse() throws LogicException,DAOException{

        Mockito.when(lot.getBidOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getBid()).thenReturn(BALANCE);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(userDao.update(any())).thenReturn(user);
        Mockito.when(bidDao.findAll()).thenReturn(bids);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.delete(anyInt())).thenReturn(false);


        LotDeleteService service = new LotDeleteService(lotDao,bidDao,userDao);
        service.deleteLot(lot);

    }

    @Test
    public void shouldReturnFalseWhenLotDaoDeleteReturnsFalse() throws LogicException,DAOException{

        Mockito.when(lot.getBidOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getBid()).thenReturn(BALANCE);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(userDao.update(any())).thenReturn(user);
        Mockito.when(bidDao.findAll()).thenReturn(bids);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.delete(anyInt())).thenReturn(true);
        Mockito.when(lotDao.delete(anyInt())).thenReturn(false);


        LotDeleteService service = new LotDeleteService(lotDao,bidDao,userDao);
        boolean actual = service.deleteLot(lot);

        Assert.assertEquals(false,actual);

    }

}
