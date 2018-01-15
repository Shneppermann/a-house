package com.epam.auction.service;


import com.epam.auction.dao.UserDao;
import com.epam.auction.entity.User;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the actual information about all users
 */

public class UserManageService {

    private static final Logger LOGGER = LogManager.getLogger(UserManageService.class);

    private UserDao userDao;

    public UserManageService(UserDao userDao){
        this.userDao = userDao;
    }


    /**
     * The method tries to return all users
     *
     * @return list of users
     * @throws LogicException when {@link DAOException} occurred
     */

    public List<User> getUserList(int idUser) throws LogicException{

        List<User> allUserList = null;
        try {
            allUserList = userDao.findAll();
        } catch (DAOException exception){
            LOGGER.error(exception.getMessage()+exception);
            throw new LogicException(exception.getMessage(),exception);
        }

        List<User> resultList = new ArrayList<>();
        if (allUserList!=null){
            for (User user: allUserList){
                int idNextUser = user.getId();
                if(idUser!=idNextUser){
                    resultList.add(user);
                }
            }
        }
        return resultList;
    }
}
