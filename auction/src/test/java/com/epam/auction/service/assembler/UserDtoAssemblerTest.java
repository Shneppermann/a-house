package com.epam.auction.service.assembler;

import com.epam.auction.dao.RoleDao;
import com.epam.auction.entity.Role;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.UserDto;
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

public class UserDtoAssemblerTest {

    private static final BigDecimal BALANCE =new BigDecimal(1);
    @Mock
    private RoleDao roleDao;

    @Mock
    private User user;

    @Mock
    private Role role;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateUserDtoWhenCorrectWork() throws LogicException,DAOException{

        UserDto expected = new UserDto(TestInfo.INT_RESULT, TestInfo.STRING_RESULT, TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT, BALANCE, TestInfo.STRING_RESULT);

        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(user.getLogin()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getName()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getSurname()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(user.getIdRole()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(roleDao.findEntityById(anyInt())).thenReturn(role);
        Mockito.when(role.getRoleName()).thenReturn(TestInfo.STRING_RESULT);

        UserDtoAssembler assembler = new UserDtoAssembler(roleDao);
        UserDto actual = assembler.createObjectDTO(user);

        Assert.assertEquals(expected,actual);
    }

    @Test (expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenRoleDaoThrowsDaoException() throws LogicException,DAOException{


        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(user.getLogin()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getName()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getSurname()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getBalance()).thenReturn(BALANCE);
        Mockito.when(user.getIdRole()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(roleDao.findEntityById(anyInt())).thenThrow(new DAOException());

        UserDtoAssembler assembler = new UserDtoAssembler(roleDao);
        assembler.createObjectDTO(user);

    }
}
