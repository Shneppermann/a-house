package com.epam.auction.service;

import com.epam.auction.dao.LotDao;
import com.epam.auction.dao.LotStateDao;
import com.epam.auction.entity.Lot;
import com.epam.auction.entity.LotState;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.DAOException;
import com.epam.auction.exceptions.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class responsible for editing the lot
 */
public class LotEditService {

    private static final Logger LOGGER = LogManager.getLogger(LotEditService.class);
    private static final String STATE_CHANGE ="The state of the lot was changed ";
    private static final String SPACE =" ";
    private static final int EMPTY_STATE = 0;
    private LotDao lotDao;
    private LotStateDao stateDao;

    public LotEditService(LotDao lotDao, LotStateDao stateDao) {
        this.lotDao = lotDao;
        this.stateDao = stateDao;
    }

    /**
     * The method tries to change lot state
     *
     * @param lotDto   edited lot
     * @param newState new state of the lot
     * @return result of the editing
     * @throws LogicException when {@link DAOException} occurred
     */
    public boolean changeLot(LotDto lotDto, String newState) throws LogicException {
        int lotId = lotDto.getId();
        boolean isChanged = false;
        try {
            Lot lot = lotDao.findEntityById(lotId);
            int changedLotState = getLotStateId(newState);
            if (changedLotState != EMPTY_STATE) {
                lot.setLotState(changedLotState);
                lot = lotDao.update(lot);
                if (lot != null) {
                    isChanged = true;
                    LOGGER.info(STATE_CHANGE+lotId +SPACE+newState);
                }
            }
        } catch (DAOException exception) {
            LOGGER.error(exception.getMessage()+exception);
            throw new LogicException(exception.getMessage(), exception);
        }
        return isChanged;
    }

    /**
     * Searching id of the new lot state
     *
     * @param newState new state of the lot
     * @return id of the new lot state
     * @throws DAOException when {@link DAOException} occurred
     */
    private int getLotStateId(String newState) throws DAOException {
        List<LotState> states = stateDao.findAll();
        int stateId = EMPTY_STATE;
        for (LotState lotState : states) {
            String stateName = lotState.getLotState();
            if (stateName.equals(newState)) {
                stateId = lotState.getId();
            }
        }
        return stateId;
    }
}
