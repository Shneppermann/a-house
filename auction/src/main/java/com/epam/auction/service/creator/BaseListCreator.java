package com.epam.auction.service.creator;

import com.epam.auction.entity.Entity;
import com.epam.auction.entity.dto.AbstractDto;
import com.epam.auction.exceptions.LogicException;

import java.util.List;

/**
 * Creates list of the dto objects
 *
 * @param <K> dto entity
 * @param <R> simple entity
 */
public interface BaseListCreator<K extends AbstractDto, R extends Entity> {

    /**
     * Creates list of the dto objects
     *
     * @param list of the simple entities
     * @return list of the dto objects
     * @throws LogicException when any exception occurred
     */
    List<K> createListDto(List<R> list) throws LogicException;

}
