package com.epam.auction.service;

import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.UserDtoAssembler;
import com.epam.auction.testInfo.TestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class UserEditPageServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserDtoAssembler assembler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnCorrectUserDtoObject() throws LogicException,DAOException {

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(TestInfo.USER);
        Mockito.when(assembler.createObjectDTO(any())).thenReturn(TestInfo.USER_DTO);

        UserEditPageService service = new UserEditPageService(userDao,assembler);
        UserDto actual = service.getChangedUser(TestInfo.INT_RESULT);

        Assert.assertEquals(TestInfo.USER_DTO,actual);

        Mockito.verify(userDao,Mockito.times(1)).findEntityById(anyInt());
        Mockito.verify(assembler,Mockito.times(1)).createObjectDTO(any());

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(userDao.findEntityById(anyInt())).thenThrow(new DAOException());


        UserEditPageService service = new UserEditPageService(userDao,assembler);
        service.getChangedUser(TestInfo.INT_RESULT);

    }
}
