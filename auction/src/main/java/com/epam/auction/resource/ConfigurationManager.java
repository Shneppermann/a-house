package com.epam.auction.resource;

import java.util.ResourceBundle;

/**
 * Pages configuration manager
 */
public class ConfigurationManager {

    private final static String RESOURCE ="Pages";
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE);

    private ConfigurationManager() { }

    /**
     * Returns property from resource bundle "Pages"
     * @param key of the string
     * @return property
     */
    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
