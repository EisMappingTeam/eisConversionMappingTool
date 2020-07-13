package com.eis.conv.mapping.srcHandler.source.startup;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.StartupParameters;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class ParametersReader {
    public static StartupParameters read(String filePath) throws IOException {

        String xmlData = FileHelper.getFileAsSting(filePath);
        XmlMapper xmlMapper = new XmlMapper();
        StartupParameters parameters = xmlMapper.readValue(xmlData, StartupParameters.class);

        return parameters;
    }
}
