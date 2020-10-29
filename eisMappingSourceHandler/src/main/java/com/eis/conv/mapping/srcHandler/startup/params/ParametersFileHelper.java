package com.eis.conv.mapping.srcHandler.startup.params;

import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserStartupParameters;

import java.io.IOException;

public final class ParametersFileHelper {

    public static UserStartupParameters getUserParametersFromArgs(String... args) {
        ParametersFileNameHelper parameterFilesHelper = new ParametersFileNameHelper();
        String userParamFileName;
        if (args.length < 1) {
            userParamFileName = parameterFilesHelper.getUserSettingsFileName("");
            System.out.println("User settings load from resources: " + userParamFileName);
        } else {
            userParamFileName = parameterFilesHelper.getUserSettingsFileName(args[0]);
            System.out.println("User settings load from " + userParamFileName);
        }
        try {
            return ParametersReader.readUserParameters(userParamFileName);
        } catch (IOException e) {
            System.out.println("User settings load FAILED. File: " + userParamFileName);
            return new UserStartupParameters();
        }

    }

    public static AppStartupParameters getAppParameters(String filePath) throws IOException {
        ParametersFileNameHelper parameterFilesHelper = new ParametersFileNameHelper();
        String appSettingsFileName;
        if (filePath.length() < 1) {
            appSettingsFileName = parameterFilesHelper.getAppSettingsFileName("");
            System.out.println("Application settings load from resources: " + appSettingsFileName);
        } else {
            appSettingsFileName = parameterFilesHelper.getAppSettingsFileName(filePath);
            System.out.println("Application settings load from " + appSettingsFileName);
        }
        try {
            return ParametersReader.readAppParameters(appSettingsFileName);
        } catch (IOException e) {
            System.out.println("Application settings load FAILED. File: " + appSettingsFileName);
            return new AppStartupParameters();
        }
    }

}
