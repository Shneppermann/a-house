package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.LotDtoAssembler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class responsible for searching information about edit lot
 */
public class LotEditPageService {

    private static final Logger LOGGER = LogManager.getLogger(LotEditPageService.class);
    private LotDao lotDao;
    private LotDtoAssembler assembler;

    public LotEditPageService(LotDao lotDao, LotDtoAssembler assembler) {
        this.lotDao = lotDao;
        this.assembler = assembler;
    }

    /**
     * Finds Lot entity by ID and creates LotDto object
     *
     * @param idLot lot id
     * @return {@link LotDto} object
     * @throws LogicException when DAOException catches
     */
    public LotDto getChangedLot(int idLot) throws LogicException {
        Lot lot;
        try {
            lot = lotDao.findEntityById(idLot);
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return assembler.createObjectDTO(lot);
    }
}
