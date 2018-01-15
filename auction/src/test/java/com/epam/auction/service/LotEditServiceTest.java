package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.LotStateDao;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class LotEditServiceTest {

    private static final String ANOTHER_STATE = "ANOTHER_STATE";

    @Mock
    private LotDao lotDao;

    @Mock
    private LotStateDao stateDao;

    @Mock
    private LotState lotState;

    @Mock
    private Lot lot;

    @Mock
    private LotDto lotDto;

    private List<LotState> lotStateList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        lotStateList.add(lotState);
    }

    @Test
    public void shouldReturnTrueWhenCorrectChange() throws LogicException,DAOException{

        Mockito.when(lotDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(any())).thenReturn(lot);
        Mockito.when(stateDao.findAll()).thenReturn(lotStateList);
        Mockito.when(lotState.getLotState()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lotState.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.update(any())).thenReturn(lot);

        LotEditService service = new LotEditService(lotDao,stateDao);
        boolean actual = service.changeLot(lotDto, TestInfo.STRING_RESULT);

        Assert.assertEquals(true,actual);

        Mockito.verify(lotDto,Mockito.times(1)).getId();
        Mockito.verify(lotDao,Mockito.times(1)).findEntityById(any());
        Mockito.verify(stateDao,Mockito.times(1)).findAll();
        Mockito.verify(lotState,Mockito.times(1)).getLotState();
        Mockito.verify(lotState,Mockito.times(1)).getId();
        Mockito.verify(lotDao,Mockito.times(1)).update(any());

    }

    @Test
    public void shouldReturnFalseWhenEmptyState() throws LogicException,DAOException{

        Mockito.when(lotDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(any())).thenReturn(lot);
        Mockito.when(stateDao.findAll()).thenReturn(lotStateList);
        Mockito.when(lotState.getLotState()).thenReturn(ANOTHER_STATE);
        Mockito.when(lotState.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.update(any())).thenReturn(lot);

        LotEditService service = new LotEditService(lotDao,stateDao);
        boolean actual = service.changeLot(lotDto, TestInfo.STRING_RESULT);

        Assert.assertEquals(false,actual);

        Mockito.verify(lotDto,Mockito.times(1)).getId();
        Mockito.verify(lotDao,Mockito.times(1)).findEntityById(any());
        Mockito.verify(stateDao,Mockito.times(1)).findAll();
        Mockito.verify(lotState,Mockito.times(1)).getLotState();
        Mockito.verify(lotState,Mockito.never()).getId();
        Mockito.verify(lotDao,Mockito.never()).update(any());

    }

    @Test
    public void shouldReturnFalseWhenLotDaoReturnsNull() throws LogicException,DAOException{

        Mockito.when(lotDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(any())).thenReturn(lot);
        Mockito.when(stateDao.findAll()).thenReturn(lotStateList);
        Mockito.when(lotState.getLotState()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(lotState.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.update(any())).thenReturn(null);

        LotEditService service = new LotEditService(lotDao,stateDao);
        boolean actual = service.changeLot(lotDto, TestInfo.STRING_RESULT);

        Assert.assertEquals(false,actual);

        Mockito.verify(lotDto,Mockito.times(1)).getId();
        Mockito.verify(lotDao,Mockito.times(1)).findEntityById(any());
        Mockito.verify(stateDao,Mockito.times(1)).findAll();
        Mockito.verify(lotState,Mockito.times(1)).getLotState();
        Mockito.verify(lotState,Mockito.times(1)).getId();
        Mockito.verify(lotDao,Mockito.times(1)).update(any());

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowDaoException() throws LogicException,DAOException{

        Mockito.when(lotDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(any())).thenThrow(new DAOException());

        LotEditService service = new LotEditService(lotDao,stateDao);
        service.changeLot(lotDto, TestInfo.STRING_RESULT);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenStateDaoThrowDaoException() throws LogicException,DAOException{

        Mockito.when(lotDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(lotDao.findEntityById(any())).thenReturn(lot);
        Mockito.when(stateDao.findAll()).thenThrow(new DAOException());

        LotEditService service = new LotEditService(lotDao,stateDao);
        service.changeLot(lotDto, TestInfo.STRING_RESULT);

    }


}
