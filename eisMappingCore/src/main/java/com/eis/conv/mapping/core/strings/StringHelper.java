package com.eis.conv.mapping.core.strings;

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
}
