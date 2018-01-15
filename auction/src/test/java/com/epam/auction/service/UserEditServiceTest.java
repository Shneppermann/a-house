package com.epam.auction.service;

import com.epam.auction.dao.RoleDao;
import com.epam.auction.dao.UserDao;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class UserEditServiceTest {
    private static final String ANOTHER_ROLE = "ANOTHER_ROLE";

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private Role role;

    @Mock
    private User user;

    @Mock
    private UserDto userDto;

    private List<Role> roleList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        roleList.add(role);
    }

    @Test
    public void shouldReturnTrueWhenCorrectChange() throws LogicException,DAOException {

        Mockito.when(userDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(any())).thenReturn(user);
        Mockito.when(roleDao.findAll()).thenReturn(roleList);
        Mockito.when(role.getRoleName()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(role.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(user);

        UserEditService service = new UserEditService(userDao,roleDao);
        boolean actual = service.changeUserRole(userDto, TestInfo.STRING_RESULT);

        Assert.assertEquals(true,actual);

        Mockito.verify(userDto,Mockito.times(1)).getId();
        Mockito.verify(userDao,Mockito.times(1)).findEntityById(any());
        Mockito.verify(roleDao,Mockito.times(1)).findAll();
        Mockito.verify(role,Mockito.times(1)).getRoleName();
        Mockito.verify(role,Mockito.times(1)).getId();
        Mockito.verify(userDao,Mockito.times(1)).update(any());

    }

    @Test
    public void shouldReturnFalseWhenEmptyRole() throws LogicException,DAOException{

        Mockito.when(userDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(any())).thenReturn(user);
        Mockito.when(roleDao.findAll()).thenReturn(roleList);
        Mockito.when(role.getRoleName()).thenReturn(ANOTHER_ROLE);
        Mockito.when(role.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(user);

        UserEditService service = new UserEditService(userDao,roleDao);
        boolean actual = service.changeUserRole(userDto, TestInfo.STRING_RESULT);

        Assert.assertEquals(false,actual);

        Mockito.verify(userDto,Mockito.times(1)).getId();
        Mockito.verify(userDao,Mockito.times(1)).findEntityById(any());
        Mockito.verify(roleDao,Mockito.times(1)).findAll();
        Mockito.verify(role,Mockito.times(1)).getRoleName();
        Mockito.verify(role,Mockito.never()).getId();
        Mockito.verify(userDao,Mockito.never()).update(any());

    }

    @Test
    public void shouldReturnFalseWhenUserDaoReturnsNull() throws LogicException,DAOException{

        Mockito.when(userDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(any())).thenReturn(user);
        Mockito.when(roleDao.findAll()).thenReturn(roleList);
        Mockito.when(role.getRoleName()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(role.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.update(any())).thenReturn(null);

        UserEditService service = new UserEditService(userDao,roleDao);
        boolean actual = service.changeUserRole(userDto, TestInfo.STRING_RESULT);

        Assert.assertEquals(false,actual);

        Mockito.verify(userDto,Mockito.times(1)).getId();
        Mockito.verify(userDao,Mockito.times(1)).findEntityById(any());
        Mockito.verify(roleDao,Mockito.times(1)).findAll();
        Mockito.verify(role,Mockito.times(1)).getRoleName();
        Mockito.verify(role,Mockito.times(1)).getId();
        Mockito.verify(userDao,Mockito.times(1)).update(any());

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowDaoException() throws LogicException,DAOException{

        Mockito.when(userDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(any())).thenThrow(new DAOException());

        UserEditService service = new UserEditService(userDao,roleDao);
        service.changeUserRole(userDto, TestInfo.STRING_RESULT);

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenRoleDaoThrowDaoException() throws LogicException,DAOException{

        Mockito.when(userDto.getId()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(userDao.findEntityById(any())).thenReturn(user);
        Mockito.when(roleDao.findAll()).thenThrow(new DAOException());

        UserEditService service = new UserEditService(userDao,roleDao);
        service.changeUserRole(userDto, TestInfo.STRING_RESULT);

    }


}
