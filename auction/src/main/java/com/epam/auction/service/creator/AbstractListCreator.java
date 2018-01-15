package com.epam.auction.service.creator;

import com.epam.auction.entity.Entity;
import com.epam.auction.entity.dto.AbstractDto;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.AbstractAssembler;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates list of dto objects
 *
 * @param <K> dto object
 * @param <R> simple entity object
 */
public abstract class AbstractListCreator<K extends AbstractDto, R extends Entity> implements BaseListCreator<K, R> {

    /**
     * Creates list of dto objects
     *
     * @param list      of the simple entities
     * @param assembler dto assembler
     * @return list of dto objects
     * @throws LogicException when {@link LogicException} occurred
     */
    public List<K> createListDto(List<R> list, AbstractAssembler<R, K> assembler) throws LogicException {
        List<K> createdList = new ArrayList<>();
        try {
            for (R element : list) {
                K actualLot = assembler.createObjectDTO(element);
                createdList.add(actualLot);
            }

        } catch (LogicException exception) {
            throw new LogicException(exception.getMessage(), exception);
        }
        return createdList;
    }
}
