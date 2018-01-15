package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.LotDtoAssembler;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class LotEditPageServiceTest {

    @Mock
    private LotDao lotDao;

    @Mock
    private LotDtoAssembler assembler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectLotDtoObject() throws LogicException,DAOException{

        Mockito.when(lotDao.findEntityById(anyInt())).thenReturn(TestInfo.LOT);
        Mockito.when(assembler.createObjectDTO(any())).thenReturn(TestInfo.LOT_DTO);

        LotEditPageService service = new LotEditPageService(lotDao,assembler);
        LotDto actual = service.getChangedLot(TestInfo.INT_RESULT);

        Assert.assertEquals(TestInfo.LOT_DTO,actual);

        Mockito.verify(lotDao,Mockito.times(1)).findEntityById(anyInt());
        Mockito.verify(assembler,Mockito.times(1)).createObjectDTO(any());

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenLotDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(lotDao.findEntityById(anyInt())).thenThrow(new DAOException());


        LotEditPageService service = new LotEditPageService(lotDao,assembler);
        service.getChangedLot(TestInfo.INT_RESULT);

    }
}
