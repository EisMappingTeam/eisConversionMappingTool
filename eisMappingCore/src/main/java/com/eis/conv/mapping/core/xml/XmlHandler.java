package com.eis.conv.mapping.core.xml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public class XmlHandler {

    public static <T> T xmlToObject(String xmlData, TypeReference<T> tRef) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(xmlData, tRef);
    }
}
