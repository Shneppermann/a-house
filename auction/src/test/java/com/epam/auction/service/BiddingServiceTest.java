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

public class BiddingServiceTest {

    private static final int DIRECT_TYPE = 1;
    private static final int REVERSE_TYPE = 2;

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
    public void shouldReturnCorrectListWhenReverseMaxBid() throws LogicException,DAOException{

        Mockito.when(lotDao.findAll()).thenReturn(expected);
        Mockito.when(lot.getAuctionType()).thenReturn(REVERSE_TYPE );
        Mockito.when(bidDao.findMinBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);

        BiddingService service = new BiddingService(lotDao,bidDao);
        List<Lot> actual = service.getSelfBidList(TestInfo.INT_RESULT);

        Assert.assertEquals(expected,actual);

        Mockito.verify(lotDao,Mockito.times(1)).findAll();
        Mockito.verify(lot, Mockito.times(1)).getAuctionType();
        Mockito.verify(bidDao,Mockito.times(1)).findMinBidByLotId(anyInt());
        Mockito.verify(bid,Mockito.times(1)).getOwnerId();
        Mockito.verify(bidDao,Mockito.never()).findMaxBidByLotId(anyInt());

    }


    @Test
    public void shouldReturnCorrectListWhenDirectMaxBid() throws LogicException,DAOException{

        Mockito.when(lotDao.findAll()).thenReturn(expected);
        Mockito.when(lot.getAuctionType()).thenReturn(DIRECT_TYPE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);

        BiddingService service = new BiddingService(lotDao,bidDao);
        List<Lot> actual = service.getSelfBidList(TestInfo.INT_RESULT);

        Assert.assertEquals(expected,actual);

        Mockito.verify(lotDao,Mockito.times(1)).findAll();
        Mockito.verify(lot, Mockito.times(1)).getAuctionType();
        Mockito.verify(bidDao,Mockito.never()).findMinBidByLotId(anyInt());
        Mockito.verify(bidDao,Mockito.times(1)).findMaxBidByLotId(anyInt());
        Mockito.verify(bid,Mockito.times(1)).getOwnerId();
    }

    @Test
    public void shouldReturnCorrectListWhenClientHasNoBids() throws LogicException,DAOException{

        Mockito.when(lotDao.findAll()).thenReturn(expected);
        Mockito.when(lot.getAuctionType()).thenReturn(DIRECT_TYPE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getOwnerId()).thenReturn(5);

        BiddingService service = new BiddingService(lotDao,bidDao);
        List<Lot> actual = service.getSelfBidList(TestInfo.INT_RESULT);

        Assert.assertEquals(emptyList,actual);

        Mockito.verify(lotDao,Mockito.times(1)).findAll();
        Mockito.verify(lot, Mockito.times(1)).getAuctionType();
        Mockito.verify(bidDao,Mockito.never()).findMinBidByLotId(anyInt());
        Mockito.verify(bidDao,Mockito.times(1)).findMaxBidByLotId(anyInt());
        Mockito.verify(bid,Mockito.times(1)).getOwnerId();
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenBidDaoThrowDaoExceprion() throws LogicException,DAOException{

        Mockito.when(lotDao.findAll()).thenReturn(expected);
        Mockito.when(lot.getAuctionType()).thenReturn(DIRECT_TYPE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenThrow(new DAOException());

        BiddingService service = new BiddingService(lotDao,bidDao);
        service.getSelfBidList(TestInfo.INT_RESULT);
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowDaoExceprion() throws LogicException,DAOException{

        Mockito.when(lotDao.findAll()).thenThrow(new DAOException());

        BiddingService service = new BiddingService(lotDao,bidDao);
        service.getSelfBidList(TestInfo.INT_RESULT);
    }
}
