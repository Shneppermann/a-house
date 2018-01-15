package com.epam.auction.service.creator;

import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.UserDto;
import com.epam.auction.exceptions.LogicException;
import com.epam.auction.service.assembler.AbstractAssembler;


import java.util.List;

/**
 * Creates list of user dto objects
 */
public class UserListCreator extends AbstractListCreator<UserDto, User> {

    private AbstractAssembler<User, UserDto> assembler;

    public UserListCreator(AbstractAssembler<User, UserDto> assembler) {
        this.assembler = assembler;
    }

    /**
     * Creates list of user dto objects
     *
     * @param users list of the users
     * @return list of the dto users
     * @throws LogicException when {@link LogicException} occurred
     */
    @Override
    public List<UserDto> createListDto(List<User> users) throws LogicException {
        return createListDto(users, assembler);
    }
}
