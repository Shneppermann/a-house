package com.epam.auction.service;

import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.User;
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

public class UserManageServiceTest {

    private static final int ANOTHER_ID = 2;

    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    private List<User> expected = new ArrayList<>();
    private List<User> emptyList= new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        expected.add(user);
    }

    @Test
    public void shouldReturnCorrectUserListWhenAnotherUserId() throws LogicException,DAOException {

        Mockito.when(userDao.findAll()).thenReturn(expected);
        Mockito.when(user.getId()).thenReturn(ANOTHER_ID);

        UserManageService service = new UserManageService(userDao);
        List<User> actual = service.getUserList(TestInfo.INT_RESULT);

        Assert.assertEquals(expected,actual);

        Mockito.verify(userDao,Mockito.times(1)).findAll();

    }

    @Test
    public void shouldReturnCorrectUserListWhenSameUserId() throws LogicException,DAOException {

        Mockito.when(userDao.findAll()).thenReturn(expected);
        Mockito.when(user.getId()).thenReturn(TestInfo.INT_RESULT);

        UserManageService service = new UserManageService(userDao);
        List<User> actual = service.getUserList(TestInfo.INT_RESULT);

        Assert.assertEquals(emptyList,actual);

        Mockito.verify(userDao,Mockito.times(1)).findAll();

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowDaoException() throws LogicException,DAOException {

        Mockito.when(userDao.findAll()).thenThrow(new DAOException());

        UserManageService service = new UserManageService(userDao);
        service.getUserList(TestInfo.INT_RESULT);

    }
}
