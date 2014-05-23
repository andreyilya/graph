package com.vseostroyke.upload.util;

import java.util.ResourceBundle;

/**
 * User: a.radkov
 * Date: 23.05.2014
 */
public final class ResourceUtil {

    private static final String PROPERTY = "mebel";
    private static ResourceBundle bundle = ResourceBundle.getBundle(PROPERTY);

    private ResourceUtil() {
    }

    public static String getMessage(String messageKey) {
        if (bundle.containsKey(messageKey)) {
            return bundle.getString(messageKey);
        }

        return messageKey;
    }
}
