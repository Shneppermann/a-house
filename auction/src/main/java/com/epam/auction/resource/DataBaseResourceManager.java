package com.epam.auction.resource;

import java.util.ResourceBundle;

/**
 * Data base resource manager
 */
public class DataBaseResourceManager {

    private static final DataBaseResourceManager instance = new DataBaseResourceManager();
    private final static String RESOURCE = "dataBase";
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE);

    public static DataBaseResourceManager getInstance() {
        return instance;
    }

    /**
     * Returns value from resource bundle "dataBase"
     *
     * @param key of the string
     * @return string
     */
    public static String getValue(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }


}
