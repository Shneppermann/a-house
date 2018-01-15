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

import static org.mockito.ArgumentMatchers.anyInt;

public class ActualBalanceServiceTest {

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnUserEntityWhenAnyUserId() throws DAOException,LogicException{

        Mockito.when(userDao.findEntityById(anyInt())).thenReturn(TestInfo.USER);

        ActualBalanceService service = new ActualBalanceService(userDao);
        User actual = service.getActualUserInfo(TestInfo.INT_RESULT);

        Assert.assertEquals(actual, TestInfo.USER);

        Mockito.verify(userDao,Mockito.times(1)).findEntityById(anyInt());

    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowsDaoException() throws DAOException,LogicException{
        Mockito.when(userDao.findEntityById(anyInt())).thenThrow(new DAOException());

        ActualBalanceService service = new ActualBalanceService(userDao);
        service.getActualUserInfo(TestInfo.INT_RESULT);
    }

}
