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

import static org.mockito.ArgumentMatchers.any;

public class RegistrationServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    private List<User> userList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userList.add(user);
    }


    @Test
    public void shouldCreateNewUserWhenLoginAndPasswordCorrect() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenReturn(userList);
        Mockito.when(user.getLogin()).thenReturn(TestInfo.EMPTY_STRING);
        Mockito.when(userDao.create(any())).thenReturn(true);

        RegistrationService service = new RegistrationService(userDao);
        boolean actual = service.createNewUser(TestInfo.STRING_RESULT,TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT,TestInfo.STRING_RESULT);

        Assert.assertEquals(true,actual);
    }

    @Test
    public void shouldReturnFalseWhenLoginAndPasswordNotCorrect() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenReturn(userList);
        Mockito.when(user.getLogin()).thenReturn(TestInfo.STRING_RESULT);
        Mockito.when(userDao.create(any())).thenReturn(true);

        RegistrationService service = new RegistrationService(userDao);
        boolean actual = service.createNewUser(TestInfo.STRING_RESULT,TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT,TestInfo.STRING_RESULT);

        Assert.assertEquals(false,actual);
    }

    @Test
    public void shouldReturnFalseWhenUserNotCreated() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenReturn(userList);
        Mockito.when(user.getLogin()).thenReturn(TestInfo.EMPTY_STRING);
        Mockito.when(userDao.create(any())).thenReturn(false);

        RegistrationService service = new RegistrationService(userDao);
        boolean actual = service.createNewUser(TestInfo.STRING_RESULT,TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT,TestInfo.STRING_RESULT);

        Assert.assertEquals(false,actual);
    }

    @Test(expected = LogicException.class)
    public void shouldThrowLogicExceptionWhenUserDaoThrowsDAOException() throws LogicException,DAOException{

        Mockito.when(userDao.findAll()).thenThrow(new DAOException());


        RegistrationService service = new RegistrationService(userDao);
        service.createNewUser(TestInfo.STRING_RESULT,TestInfo.STRING_RESULT,
                TestInfo.STRING_RESULT,TestInfo.STRING_RESULT);

    }
}
