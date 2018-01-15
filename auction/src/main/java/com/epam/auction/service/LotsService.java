package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class responsible for actual information about personal user's lots
 */
public class LotsService {

    private static final Logger LOGGER = LogManager.getLogger(LotsService.class);

    private LotDao lotDao;

    public LotsService(LotDao lotDao){
        this.lotDao = lotDao;
    }

    /**
     * The method tries to return all personal user's lots
     * @param userId user id
     * @return list of the personal user's lots
     * @throws LogicException when {@link DAOException} occurred
     */

    public List<Lot> getSelfLotList(Integer userId) throws LogicException{
        List<Lot> result = null;
        try {
            result = lotDao.findAllPersonalLots(userId);
        }catch (DAOException exception){
            LOGGER.error(exception.getMessage()+exception);
            throw new LogicException(exception.getMessage(),exception);
        }
        return result;
    }

}
