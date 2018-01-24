package com.epam.auction.service.assembler;

import com.epam.auction.entity.Entity;
import com.epam.auction.entity.dto.AbstractDto;
import com.epam.auction.exceptions.LogicException;

/**
 * Class creates DTO objects from simple entity
 *
 * @param <T> entity
 * @param <R> abstract DTO
 */
public abstract class AbstractAssembler<T extends Entity, R extends AbstractDto> {

    /**
     * Creates DTO objects
     *
     * @param entity an entity object
     * @return DTO object
     * @throws LogicException when DAOException occurred
     */
    public abstract R createObjectDTO(T entity) throws LogicException;

}
