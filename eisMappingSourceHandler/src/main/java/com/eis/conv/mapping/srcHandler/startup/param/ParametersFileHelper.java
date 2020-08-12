package com.eis.conv.mapping.srcHandler.startup.param;

import com.eis.conv.mapping.srcHandler.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupParameters;

import java.io.IOException;

public final class ParametersFileHelper {

    public static UserStartupParameters getUserParametersFromArgs(String... args) throws IOException {
        ParametersFileNameHelper parameterFilesHelper = new ParametersFileNameHelper();
        String userParamFileName;
        if (args.length < 1) {
            userParamFileName = parameterFilesHelper.getUserSettingsFileName("");
            //System.out.println("User settings load from resources: " + userParamFileName);
        } else {
            userParamFileName = parameterFilesHelper.getUserSettingsFileName(args[0]);
            //System.out.println("User settings load from " + userParamFileName);
        }
        return ParametersReader.readUserParameters(userParamFileName);
    }

    public static AppStartupParameters getAppParameters(String filePath) throws IOException {
        ParametersFileNameHelper parameterFilesHelper = new ParametersFileNameHelper();
        String appSettingsFileName;
        if (filePath.length() < 1) {
            appSettingsFileName = parameterFilesHelper.getAppSettingsFileName("");
            //System.out.println("Application settings load from resources: " + appSettingsFileName);
        } else {
            appSettingsFileName = parameterFilesHelper.getAppSettingsFileName(filePath);
            //System.out.println("User settings load from " + appSettingsFileName);
        }
        return ParametersReader.readAppParameters(appSettingsFileName);
    }

}
