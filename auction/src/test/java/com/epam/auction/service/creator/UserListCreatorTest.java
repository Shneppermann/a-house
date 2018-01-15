package com.epam.auction.service.creator;

import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.UserDtoAssembler;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class UserListCreatorTest {
    @Mock
    private UserDtoAssembler assembler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnDtoList() throws LogicException {

        Mockito.when(assembler.createObjectDTO(any())).thenReturn(TestInfo.USER_DTO);

        UserListCreator creator = new UserListCreator(assembler);
        List<UserDto> actual = creator.createListDto(TestInfo.USER_LIST);

        Assert.assertEquals(TestInfo.USER_DTO_LIST,actual);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenAssemblerThrowsLogicException() throws LogicException{

        Mockito.when(assembler.createObjectDTO(any())).thenThrow(new LogicException());

        UserListCreator creator = new UserListCreator(assembler);
        creator.createListDto(TestInfo.USER_LIST);
    }
}
