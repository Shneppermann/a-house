package com.epam.auction.service.creator;

import com.epam.auction.entity.Lot;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.AbstractAssembler;

import java.util.List;

/**
 * Creates list of lot dto objects
 */
public class LotsListCreator extends AbstractListCreator<LotDto, Lot> {

    private AbstractAssembler<Lot, LotDto> assembler;

    public LotsListCreator(AbstractAssembler<Lot, LotDto> assembler) {
        this.assembler = assembler;
    }

    /**
     * Creates list of lot dto objects
     *
     * @param lots list of the lots
     * @return list of the dto lots
     * @throws LogicException when {@link LogicException} occurred
     */
    @Override
    public List<LotDto> createListDto(List<Lot> lots) throws LogicException {
        return createListDto(lots, assembler);
    }

}

