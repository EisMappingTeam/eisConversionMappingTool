package com.eis.conv.mapping.srcHandler.source.startup;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupParameters;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public final class ParametersReader {
    public static UserStartupParameters read(String filePath) throws IOException {

        String xmlData = FileHelper.getFileAsSting(filePath);
        XmlMapper xmlMapper = new XmlMapper();
        UserStartupParameters parameters = xmlMapper.readValue(xmlData, UserStartupParameters.class);

        return parameters;
    }
}
