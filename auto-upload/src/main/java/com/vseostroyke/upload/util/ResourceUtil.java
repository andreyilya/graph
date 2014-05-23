package com.vseostroyke.upload.util;

import java.util.ArrayList;
import java.util.List;
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

    public static List<String> getDynamicProperties() {
        List<String> properties = new ArrayList<>();
        for (String key : bundle.keySet()) {
            if (key.split("\\.")[0].equals("dynamic")) {
                properties.add(key);
            }
        }
        return properties;
    }
}
