package com.eis.conv.mapping.srcHandler.startup.param;

import java.io.File;

public class ParametersFileNameHelper {

    private static final String USER_PARAMETER_FILE = "parameters/userParameters.xml";
    private static final String APP_PARAMETER_FILE = "parameters/appParameters.xml";

    public String getUserSettingsFileName(String userSettingsFileName) {
        if (userSettingsFileName.length() <1) {
            File fl = new File(getClass().getClassLoader().getResource(USER_PARAMETER_FILE).getFile());
            return fl.getAbsolutePath();
        }
        return userSettingsFileName;
    }


    public String getAppSettingsFileName(String appSettingsFileName) {
        if (appSettingsFileName.length() <1) {
            File fl = new File(getClass().getClassLoader().getResource(APP_PARAMETER_FILE).getFile());
            return fl.getAbsolutePath();
        }
        return appSettingsFileName;
    }

}
