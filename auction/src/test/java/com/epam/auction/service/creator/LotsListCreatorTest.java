package com.epam.auction.service.creator;

import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.LotDtoAssembler;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class LotsListCreatorTest {
    @Mock
    private LotDtoAssembler assembler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnDtoList() throws LogicException{

        Mockito.when(assembler.createObjectDTO(any())).thenReturn(TestInfo.LOT_DTO);

        LotsListCreator creator = new LotsListCreator(assembler);
        List<LotDto> actual = creator.createListDto(TestInfo.LOT_LIST);

        Assert.assertEquals(TestInfo.LOT_DTO_LIST,actual);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenAssemblerThrowsLogicException() throws LogicException{

        Mockito.when(assembler.createObjectDTO(any())).thenThrow(new LogicException());

        LotsListCreator creator = new LotsListCreator(assembler);
        creator.createListDto(TestInfo.LOT_LIST);
    }
}
