package com.eis.conv.mapping.core.stringsSupport;

import com.google.common.base.Splitter;

import java.util.List;

public final class StringHelper {

    public static String getLastDotSplitted(String str) {
        String[] arr = str.split("\\.");
        return arr[arr.length - 1];
    }

    public static String notNullString(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getLeft(String str, int len) {
        if (str == null || len < 1) {
            return "";
        }
        if (str.length() < len) {
            return str;
        }
        return str.substring(0, len);
    }

    public static String getRight(String str, int len) {
        if (str == null) {
            return "";
        }
        if (str.length() < len) {
            return str;
        }
        return str.substring(str.length() - len);
    }


    public static String lowerFirst(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() < 1) {
            return str;
        }
        return getLeft(str, 1).toLowerCase() + getRight(str, str.length() - 1);
    }

    public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static List<String> splitToListAndTrim(String source, String splitter) {
        List<String> convertedCountriesList = Splitter.on(splitter)
                .trimResults()
                .splitToList(source);
        return convertedCountriesList;
    }
}
