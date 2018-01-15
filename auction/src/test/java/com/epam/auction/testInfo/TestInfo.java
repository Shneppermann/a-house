package com.epam.auction.testInfo;

import com.epam.auction.entity.Lot;
import com.epam.auction.entity.User;
import com.epam.auction.entity.dto.LotDto;
import com.epam.auction.entity.dto.UserDto;

import java.util.Collections;
import java.util.List;

public class TestInfo {

    private TestInfo(){}

    /**
     * Pages
     */
    public static final String TEST_PAGE_ADD_LOT = "/add_lot";
    public static final String TEST_PAGE_DIRECT_AUCTION = "/direct_auction";
    public static final String TEST_PAGE_REVERSE_AUCTION = "/reverse_auction";
    public static final String TEST_PAGE_BIDDING = "/bidding";
    public static final String TEST_PAGE_INDEX = "/index.jsp";
    public static final String TEST_PAGE_LOT_MANAGE = "/lot_manage";
    public static final String TEST_PAGE_LOT_EDIT = "/lot_edit";
    public static final String TEST_PAGE_LOTS = "/lots";
    public static final String TEST_PAGE_USER_MANAGE ="/user_manage";
    public static final String TEST_PAGE_USER_EDIT = "/user_edit";
    public static final String TEST_PAGE_LOGIN="/login.jsp";
    public static final String TEST_PAGE_REGISTRATE ="/registration_page.jsp";

    /**
     * Returns items
     */
    public static final String STRING_RESULT = "result" ;
    public static final String STRING_RESULT_FOR_INTEGER_PARS = "2";
    public static final String EMPTY_STRING ="";
    public static final int INT_RESULT = 1 ;

    /**
     * Entities
     */
    public static final Lot LOT = new Lot();
    public static final LotDto LOT_DTO = new LotDto();
    public static final User USER= new User();
    public static final UserDto USER_DTO = new UserDto();
    public static final List<Lot> LOT_LIST =  Collections.singletonList(LOT);
    public static final List<LotDto> LOT_DTO_LIST = Collections.singletonList(LOT_DTO);
    public static final List<User> USER_LIST = Collections.singletonList(USER);
    public static final List<UserDto> USER_DTO_LIST = Collections.singletonList(USER_DTO);

    /**
     * Parameters and attributes
     */
    public static final String USER_EDIT_ATTR =  "userEdit";
    public static final String USER_ATTR = "user";

}
