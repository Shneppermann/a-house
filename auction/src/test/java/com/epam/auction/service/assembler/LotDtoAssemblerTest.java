package com.epam.auction.service.assembler;

import com.epam.auction.dao.AuctionTypeDao;
import com.epam.auction.dao.BidDao;
import com.epam.auction.dao.LotStateDao;
import com.epam.auction.entity.AuctionType;
import com.epam.auction.entity.Bid;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.LotState;
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

import static org.mockito.ArgumentMatchers.anyInt;

public class LotDtoAssemblerTest {
    private static final BigDecimal BID =new BigDecimal(1);

    @Mock
    private AuctionTypeDao typeDao;
    @Mock
    private LotStateDao stateDao;
    @Mock
    private BidDao bidDao;

    @Mock
    private Lot lot;

    @Mock
    private LotState state;

    @Mock
    private AuctionType type;

    @Mock
    private Bid bid;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateLotDtoWhenActualBidDirectType() throws LogicException,DAOException {

        LotDto expected = new  LotDto(TestInfo.INT_RESULT,TestInfo.INT_RESULT, TestInfo.STRING_RESULT,
                TestInfo.INT_RESULT, BID, TestInfo.INT_RESULT, BID, TestInfo.STRING_RESULT, TestInfo.STRING_RESULT);

        Mockito.when(lot.getLotState()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(stateDao.findEntityById(anyInt())).thenReturn(state);
        Mockito.when(state.getLotState()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getAuctionType()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when( typeDao.findEntityById(anyInt())).thenReturn(type);
        Mockito.when(type.getAuctionType()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bid.getBid()).thenReturn(BID);
        Mockito.when(lot.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getLotName()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getStep()).thenReturn(BID);


        LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
        LotDto actual = assembler.createObjectDTO(lot);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldCreateLotDtoWhenActualBidReverseType() throws LogicException,DAOException {

        LotDto expected = new  LotDto(TestInfo.INT_RESULT,TestInfo.INT_RESULT, TestInfo.STRING_RESULT,
                TestInfo.INT_RESULT, BID, TestInfo.INT_RESULT, BID, TestInfo.STRING_RESULT, TestInfo.STRING_RESULT);

        Mockito.when(lot.getLotState()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(stateDao.findEntityById(anyInt())).thenReturn(state);
        Mockito.when(state.getLotState()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getAuctionType()).thenReturn(2);
        Mockito.when(typeDao.findEntityById(anyInt())).thenReturn(type);
        Mockito.when(type.getAuctionType()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMinBidByLotId(anyInt())).thenReturn(bid);
        Mockito.when(bid.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bid.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bid.getBid()).thenReturn(BID);
        Mockito.when(lot.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getLotName()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getStep()).thenReturn(BID);


        LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
        LotDto actual = assembler.createObjectDTO(lot);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldCreateLotDtoWhenActualBidNull() throws LogicException,DAOException {

        LotDto expected = new  LotDto(TestInfo.INT_RESULT,0, TestInfo.STRING_RESULT,
                TestInfo.INT_RESULT, BID, 0, new BigDecimal(0),
                TestInfo.STRING_RESULT, TestInfo.STRING_RESULT);

        Mockito.when(lot.getLotState()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(stateDao.findEntityById(anyInt())).thenReturn(state);
        Mockito.when(state.getLotState()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getAuctionType()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(typeDao.findEntityById(anyInt())).thenReturn(type);
        Mockito.when(type.getAuctionType()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenReturn(null);
        Mockito.when(lot.getOwnerId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lot.getLotName()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getStep()).thenReturn(BID);
        Mockito.when(lot.getStartPrice()).thenReturn(new BigDecimal(0));



        LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
        LotDto actual = assembler.createObjectDTO(lot);

        Assert.assertEquals(expected,actual);
    }

    @Test (expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenStateDaoThrowDaoException() throws LogicException,DAOException {


        Mockito.when(lot.getLotState()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(stateDao.findEntityById(anyInt())).thenThrow(new DAOException());

        LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
        assembler.createObjectDTO(lot);

    }

    @Test (expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenTypeDaoThrowDaoException() throws LogicException,DAOException {


        Mockito.when(lot.getLotState()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(stateDao.findEntityById(anyInt())).thenReturn(state);
        Mockito.when(state.getLotState()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getAuctionType()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(typeDao.findEntityById(anyInt())).thenThrow(new DAOException());

        LotDtoAssembler assembler = new LotDtoAssembler(typeDao, stateDao, bidDao);
        assembler.createObjectDTO(lot);
    }

    @Test (expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenBidDaoThrowDaoException() throws LogicException,DAOException {


        Mockito.when(lot.getLotState()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(stateDao.findEntityById(anyInt())).thenReturn(state);
        Mockito.when(state.getLotState()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getAuctionType()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(typeDao.findEntityById(anyInt())).thenReturn(type);
        Mockito.when(type.getAuctionType()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lot.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(bidDao.findMaxBidByLotId(anyInt())).thenThrow(new DAOException());


        LotDtoAssembler assembler = new LotDtoAssembler(typeDao,stateDao,bidDao);
        assembler.createObjectDTO(lot);

    }

}
