package com.eis.conv.mapping.srcHandler.source.startup;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.application.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupParameters;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public final class ParametersReader {
    public static UserStartupParameters readUserParameters(String filePath) throws IOException {

        String xmlData = FileHelper.getFileAsSting(filePath);
        XmlMapper xmlMapper = new XmlMapper();
        UserStartupParameters parameters = xmlMapper.readValue(xmlData, UserStartupParameters.class);

        return parameters;
    }

    public static AppStartupParameters readAppParameters(String filePath) throws IOException {

        String xmlData = FileHelper.getFileAsSting(filePath);
        XmlMapper xmlMapper = new XmlMapper();
        AppStartupParameters parameters = xmlMapper.readValue(xmlData, AppStartupParameters.class);

        return parameters;
    }
}
