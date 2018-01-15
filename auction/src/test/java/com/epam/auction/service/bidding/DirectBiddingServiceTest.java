package com.epam.auction.service.bidding;

import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


public class DirectBiddingServiceTest {

    private static final BigDecimal USER_BALANCE = new BigDecimal(10);
    private static final BigDecimal STEP_AND_BID_MIN = new BigDecimal(1);
    private static final BigDecimal STEP_AND_BID_MAX = new BigDecimal(100);

    @Mock
    private UserDao userDao;
    @Mock
    private LotDao lotDao;
    @Mock
    private BidDao bidDao;
    @Mock
    private User user;
    @Mock
    private Lot lot;
    @Mock
    private Bid bid;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnTrueWhenBidAndStepLessBalance()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(anyInt())).thenReturn(lot);
        Mockito.when(lot.getStep()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(bid.getBid()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(null);
        Mockito.when(bidDao.create(any())).thenReturn(true);


        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        boolean actual = service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

        Assert.assertEquals(true,actual);

        Mockito.verify(userDao,Mockito.times(3)).findEntityById(anyInt());
        Mockito.verify(userDao,Mockito.times(2)).update(any());
        Mockito.verify(bidDao,Mockito.times(1)).findMaxBidByLotId(anyInt());
        Mockito.verify(lotDao,Mockito.times(2)).findEntityById(anyInt());
    }

    @Test
    public void shouldReturnTrueWhenActualBidIsNullAndStepLessBalance()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(null);
        Mockito.when(lotDao.findEntityById(anyInt())).thenReturn(lot);
        Mockito.when(lot.getStep()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(lot.getStartPrice()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(null);
        Mockito.when(bidDao.create(any())).thenReturn(true);

        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        boolean actual = service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

        Assert.assertEquals(true,actual);

        Mockito.verify(userDao,Mockito.times(2)).findEntityById(anyInt());
        Mockito.verify(userDao,Mockito.times(1)).update(any());
        Mockito.verify(bidDao,Mockito.times(1)).findMaxBidByLotId(anyInt());
        Mockito.verify(lotDao,Mockito.times(2)).findEntityById(anyInt());
    }

    @Test
    public void shouldReturnFalseWhenActualBidIsNullAndStepGreaterBalance()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(null);
        Mockito.when(lotDao.findEntityById(anyInt())).thenReturn(lot);
        Mockito.when(lot.getStep()).thenReturn(STEP_AND_BID_MAX);
        Mockito.when(lot.getStartPrice()).thenReturn(STEP_AND_BID_MAX);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(null);
        Mockito.when(bidDao.create(any())).thenReturn(true);

        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        boolean actual = service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

        Assert.assertEquals(false,actual);

        Mockito.verify(userDao,Mockito.times(1)).findEntityById(anyInt());
        Mockito.verify(userDao,Mockito.never()).update(any());
        Mockito.verify(bidDao,Mockito.times(1)).findMaxBidByLotId(anyInt());
        Mockito.verify(lotDao,Mockito.times(1)).findEntityById(anyInt());
    }

    @Test
    public void shouldReturnFalseWhenBidAndStepGreaterBalance()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(anyInt())).thenReturn(lot);
        Mockito.when(lot.getStep()).thenReturn(STEP_AND_BID_MAX);
        Mockito.when(bid.getBid()).thenReturn(STEP_AND_BID_MAX);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(null);
        Mockito.when(bidDao.create(any())).thenReturn(true);


        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        boolean actual = service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

        Assert.assertEquals(false,actual);

        Mockito.verify(userDao,Mockito.times(1)).findEntityById(anyInt());
        Mockito.verify(userDao,Mockito.never()).update(any());
        Mockito.verify(bidDao,Mockito.times(1)).findMaxBidByLotId(anyInt());
        Mockito.verify(lotDao,Mockito.times(1)).findEntityById(anyInt());
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowDaoException()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(anyInt())).thenThrow(new DAOException());

        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenBidDaoThrowDaoException()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenThrow(new DAOException());

        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowDaoException()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(anyInt())).thenReturn(lot);
        Mockito.when(lot.getStep()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(bid.getBid()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenThrow(new DAOException());


        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenBidDaoCreateThrowDaoException()throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(user);
        Mockito.when(user.getBalance()).thenReturn(USER_BALANCE);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getLotId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(anyInt())).thenReturn(lot);
        Mockito.when(lot.getStep()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(bid.getBid()).thenReturn(STEP_AND_BID_MIN);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(null);
        Mockito.when(bidDao.create(any())).thenThrow(new DAOException());


        DirectBiddingService service = new DirectBiddingService(userDao,lotDao,bidDao);
        service.doBid(TestInfo.INT_RESULT,TestInfo.INT_RESULT);

    }

}
