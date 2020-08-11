package com.eis.conv.mapping.srcHandler.source.startup.param;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.core.xml.XmlHandler;
import com.eis.conv.mapping.srcHandler.source.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.param.usr.UserStartupParameters;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.IOException;

public final class ParametersReader {


    public static UserStartupParameters readUserParameters(String filePath) throws IOException {
        String xmlData = FileHelper.getFileAsSting(filePath);
        UserStartupParameters parameters = XmlHandler.xmlToObject(xmlData, new TypeReference<UserStartupParameters>() {
        });
        parameters.setFileName(filePath);
        return parameters;
    }

    public static AppStartupParameters readAppParameters(String filePath) throws IOException {
        String xmlData = FileHelper.getFileAsSting(filePath);
        AppStartupParameters parameters = XmlHandler.xmlToObject(xmlData, new TypeReference<AppStartupParameters>() {
        });
        parameters.setFileName(filePath);
        return parameters;
    }
}
