package com.epam.auction.resource;

public class Info {
    private Info(){}
    /**
     * Pages
     */
    public static final String INDEX_PAGE= "path.page.index";
    public static final String LOGIN_PAGE = "path.page.login";
    public static final String BIDDING_PAGE ="path.page.bidding";
    public static final String DIRECT_PAGE = "path.page.direct";
    public static final String REVERSE_PAGE = "path.page.reverse";
    public static final String LOTS_PAGE ="path.page.lots";
    public static final String CUSTOMER_PAGE = "path.page.direct";
    public static final String USER_MANAGE_PAGE ="path.page.usermanage";
    public static final String LOT_MANAGE_PAGE ="path.page.lotmanage";
    public static final String ADD_LOT_PAGE = "path.page.addlot";
    public static final String LOT_EDIT_PAGE = "path.page.lotedit";
    public static final String USER_EDIT_PAGE = "path.page.useredit";
    public static final String REGISTRATION_PAGE = "path.page.registration";
    public static final String ERROR_PAGE = "path.page.errorpage";
    /**
     * Parameters
     */
    public static final String PARAM_NAME_LOGIN = "login";
    public static final String PARAM_NAME_PASSWORD = "password";
    public static final String PARAM_USER_NAME ="name";
    public static final String PARAM_USER_SURNAME ="surname";
    public static final String PARAM_BID = "bid";
    public static final String PARAM_LOT_NAME = "lotName";
    public static final String PARAM_START_PRICE="startPrice";
    public static final String PARAM_STEP ="step";
    public static final String PARAM_AUCTION_TYPE = "auctionType";
    public static final String PARAM_USER_ID ="userId";
    public static final String PARAM_LOT_ID ="lotId";
    public static final String PARAM_NEW_LOT_STATE ="newState";
    public static final String PARAM_NEW_USER_ROLE = "newRole";
    public static final String PARAM_REDIRECT_PAGE ="redirectPage";
    /**
     * Session attributes
     */
    public static final String LOCAL = "local";
    public static final String CONTEXT_PATH = "contextPath";
    public static final String ATTRIBUTE_USER_LIST ="userList";
    public static final String ATTRIBUTE_LIST = "lst";
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_LOT = "lot";
    public static final String ATTRIBUTE_EDITED_USER = "userEdit";
    public static final String ATTRIBUTE_NOT_MONEY ="notEnoughMessage";
    public static final String ATTRIBUTE_BAN = "bannedUserMessage";

    /**
     * Messages
     */
    public static final String MESS_BUNDLE = "message";
    public static final String MESS_NOT_ENOUGH_MONEY="message.not.enough.money";
    public static final String MESS_REGISTRATION_SUCCESS ="message.registration.success";
    public static final String MESS_WRONG_LOGIN = "message.registration.bad.login";
    public static final String MESS_BANNED = "message.login.banned";
    public static final String MESS_INCORRECT_LOGIN_OR_PASS = "message.login.incorrect";

    /**
     * Empty string
     */
    public static final String EMPTY_STRING ="";

}
