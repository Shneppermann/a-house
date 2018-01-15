package com.epam.auction.service;

import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.entity.Bid;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

public class ReverseServiceTest {
    private static final int ANOTHER_USER_ID = 2;

    @Mock
    private LotDao lotDao;

    @Mock
    private BidDao bidDao;

    @Mock
    private Lot lot;

    @Mock
    private Bid bid;

    private List<Lot> expected = new ArrayList<>();
    private List<Lot> emptyList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expected.add(lot);
    }

    @Test
    public void shouldReturnCorrectListWhenUserNotOwnerAndHighBidNotEquals() throws LogicException,DAOException {

        Mockito.when(lotDao.findAllReverseBiddingLots()).thenReturn(expected);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMinBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getOwnerId()).thenReturn(ANOTHER_USER_ID);
        Mockito.when(lot.getOwnerId()).thenReturn(ANOTHER_USER_ID);

        ReverseService service = new ReverseService(lotDao,bidDao);
        List<Lot> actual = service.getReverseLotList(TestInfo.INT_RESULT);

        Assert.assertEquals(expected,actual);

        Mockito.verify(lot,Mockito.times(1)).getId();
        Mockito.verify(lot,Mockito.times(1)).getOwnerId();
        Mockito.verify(bid,Mockito.times(1)).getOwnerId();
        Mockito.verify(lotDao,Mockito.times(1)).findAllReverseBiddingLots();
        Mockito.verify(bidDao,Mockito.times(1)).findMinBidByLotId(anyInt());

    }

    @Test
    public void shouldReturnCorrectListWhenUserNotOwnerAndHighBidNotExist() throws LogicException,DAOException{

        Mockito.when(lotDao.findAllReverseBiddingLots()).thenReturn(expected);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMinBidByLotId(anyInt())).thenReturn(null);
        Mockito.when(lot.getOwnerId()).thenReturn(ANOTHER_USER_ID);

        ReverseService service = new ReverseService(lotDao,bidDao);
        List<Lot> actual = service.getReverseLotList(TestInfo.INT_RESULT);

        Assert.assertEquals(expected,actual);

        Mockito.verify(lot,Mockito.times(1)).getId();
        Mockito.verify(lot,Mockito.times(1)).getOwnerId();
        Mockito.verify(bid,Mockito.never()).getOwnerId();
        Mockito.verify(lotDao,Mockito.times(1)).findAllReverseBiddingLots();
        Mockito.verify(bidDao,Mockito.times(1)).findMinBidByLotId(anyInt());

    }

    @Test
    public void shouldReturnCorrectListWhenUserIsOwner() throws LogicException,DAOException{

        Mockito.when(lotDao.findAllReverseBiddingLots()).thenReturn(expected);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMinBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getOwnerId()).thenReturn(ANOTHER_USER_ID);
        Mockito.when(lot.getOwnerId()).thenReturn(TestInfo.INT_RESULT);

        ReverseService service = new ReverseService(lotDao,bidDao);
        List<Lot> actual = service.getReverseLotList(TestInfo.INT_RESULT);

        Assert.assertEquals(emptyList,actual);

        Mockito.verify(lot,Mockito.times(1)).getId();
        Mockito.verify(lot,Mockito.times(1)).getOwnerId();
        Mockito.verify(bid,Mockito.times(1)).getOwnerId();
        Mockito.verify(lotDao,Mockito.times(1)).findAllReverseBiddingLots();
        Mockito.verify(bidDao,Mockito.times(1)).findMinBidByLotId(anyInt());

    }

    @Test
    public void shouldReturnCorrectListWhenUsersHighBid() throws LogicException,DAOException{

        Mockito.when(lotDao.findAllReverseBiddingLots()).thenReturn(expected);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMinBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getOwnerId()).thenReturn(ANOTHER_USER_ID);

        ReverseService service = new ReverseService(lotDao,bidDao);
        List<Lot> actual = service.getReverseLotList(TestInfo.INT_RESULT);

        Assert.assertEquals(emptyList,actual);

        Mockito.verify(lot,Mockito.times(1)).getId();
        Mockito.verify(lot,Mockito.times(1)).getOwnerId();
        Mockito.verify(bid,Mockito.times(1)).getOwnerId();
        Mockito.verify(lotDao,Mockito.times(1)).findAllReverseBiddingLots();
        Mockito.verify(bidDao,Mockito.times(1)).findMinBidByLotId(anyInt());

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(lotDao.findAllReverseBiddingLots()).thenThrow(new DAOException());

        ReverseService service = new ReverseService(lotDao,bidDao);
        service.getReverseLotList(TestInfo.INT_RESULT);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenBidDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(lotDao.findAllReverseBiddingLots()).thenReturn(expected);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMinBidByLotId(anyInt())).thenThrow(new DAOException());
        Mockito.when(lot.getOwnerId()).thenReturn(ANOTHER_USER_ID);

        ReverseService service = new ReverseService(lotDao,bidDao);
        service.getReverseLotList(TestInfo.INT_RESULT);

    }
}
