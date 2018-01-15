package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.entity.Lot;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible for the actual information about all lots
 */
public class LotManageService {

    private static final Logger LOGGER = LogManager.getLogger(LotManageService.class);
    private LotDao lotDao;

    public LotManageService(LotDao lotDao) {
        this.lotDao = lotDao;
    }

    /**
     * The method tries to return all lots
     *
     * @return list of lots
     * @throws LogicException when {@link DAOException} occurred
     */
    public List<Lot> getLots() throws LogicException {
        List<Lot> lots = new ArrayList<>();
        try {
            lots = lotDao.findAll();

        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage()+exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return lots;
    }

}
