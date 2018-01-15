package com.epam.auction.service;

import com.epam.auction.dao.RoleDao;
import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.Role;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.encoding.ShaStringCreator;
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

public class LoginServiceTest {

    private static final String PASSWORD_STRING = "password";
    private final static String ADMIN = "Admin";

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private User user;

    @Mock
    private Role role;

    private List<User> userList = new ArrayList<>();
    private String shaPass;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        shaPass = ShaStringCreator.sha(PASSWORD_STRING);
        userList.add(user);
    }

    @Test
    public void shouldReturnCorrectUserWhenCorrectPasswordAndLogin() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenReturn(userList);
        Mockito.when(user.getLogin()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getPassword()).thenReturn(shaPass);

        LoginService service = new LoginService(userDao,roleDao);
        User actual = service.getUser(TestInfo.STRING_RESULT,PASSWORD_STRING);

        Assert.assertEquals(user,actual);

        Mockito.verify(userDao,Mockito.times(1)).findAll();
        Mockito.verify(user,Mockito.times(1)).getLogin();
        Mockito.verify(user,Mockito.times(1)).getPassword();

    }

    @Test
    public void shouldReturnNullWhenIncorrectPassword() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenReturn(userList);
        Mockito.when(user.getLogin()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(user.getPassword()).thenReturn(null);

        LoginService service = new LoginService(userDao,roleDao);
        User actual = service.getUser(TestInfo.STRING_RESULT,PASSWORD_STRING);

        Assert.assertEquals(null,actual);

        Mockito.verify(userDao,Mockito.times(1)).findAll();
        Mockito.verify(user,Mockito.times(1)).getLogin();
        Mockito.verify(user,Mockito.times(1)).getPassword();

    }

    @Test
    public void shouldReturnNullWhenIncorrectLogin() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenReturn(userList);
        Mockito.when(user.getLogin()).thenReturn(null);
        Mockito.when(user.getPassword()).thenReturn(shaPass);

        LoginService service = new LoginService(userDao,roleDao);
        User actual = service.getUser(TestInfo.STRING_RESULT,PASSWORD_STRING);

        Assert.assertEquals(null,actual);

        Mockito.verify(userDao,Mockito.times(1)).findAll();
        Mockito.verify(user,Mockito.times(1)).getLogin();
        Mockito.verify(user,Mockito.times(1)).getPassword();

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenThrow(new DAOException());

        LoginService service = new LoginService(userDao,roleDao);
        service.getUser(TestInfo.STRING_RESULT,PASSWORD_STRING);

    }

    @Test
    public void shouldReturnTrueWhenAdminRole() throws LogicException,DAOException{

        Mockito.when(user.getIdRole()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(roleDao.findEntityById(anyInt())).thenReturn(role);
        Mockito.when(role.getRoleName()).thenReturn(ADMIN);

        LoginService service = new LoginService(userDao,roleDao);
        boolean actual = service.isAdmin(user);

        Assert.assertEquals(true,actual);

        Mockito.verify(user,Mockito.times(1)).getIdRole();
        Mockito.verify(roleDao,Mockito.times(1)).findEntityById(anyInt());
        Mockito.verify(role,Mockito.times(1)).getRoleName();
    }

    @Test
    public void shouldReturnFalseWhenNotAdminRole() throws LogicException,DAOException{

        Mockito.when(user.getIdRole()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(roleDao.findEntityById(anyInt())).thenReturn(role);
        Mockito.when(role.getRoleName()).thenReturn(TestInfo.STRING_RESULT);

        LoginService service = new LoginService(userDao,roleDao);
        boolean actual = service.isAdmin(user);

        Assert.assertEquals(false,actual);

        Mockito.verify(user,Mockito.times(1)).getIdRole();
        Mockito.verify(roleDao,Mockito.times(1)).findEntityById(anyInt());
        Mockito.verify(role,Mockito.times(1)).getRoleName();
    }

    @Test
    public void shouldReturnFalseWhenRoleNull() throws LogicException,DAOException{

        Mockito.when(user.getIdRole()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(roleDao.findEntityById(anyInt())).thenReturn(null);
        Mockito.when(role.getRoleName()).thenReturn(TestInfo.STRING_RESULT);

        LoginService service = new LoginService(userDao,roleDao);
        boolean actual = service.isAdmin(user);

        Assert.assertEquals(false,actual);

        Mockito.verify(user,Mockito.times(1)).getIdRole();
        Mockito.verify(roleDao,Mockito.times(1)).findEntityById(anyInt());
        Mockito.verify(role,Mockito.never()).getRoleName();
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenRoleDaoThrowsDaoException() throws LogicException,DAOException{

        Mockito.when(user.getIdRole()).thenReturn(TestInfo.INT_RESULT);
        Mockito.when(roleDao.findEntityById(anyInt())).thenThrow(new DAOException());


        LoginService service = new LoginService(userDao,roleDao);
        service.isAdmin(user);

    }


}
