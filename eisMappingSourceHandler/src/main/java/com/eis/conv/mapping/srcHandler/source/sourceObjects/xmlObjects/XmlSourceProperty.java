package com.eis.conv.mapping.srcHandler.source.sourceObjects.xmlObjects;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class XmlSourceProperty {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String value;

}
