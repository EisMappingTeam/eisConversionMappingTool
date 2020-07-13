package com.eis.conv.mapping.srcHandler.source.startup.parameters.user;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class UserLoadSource {
    @JacksonXmlProperty(localName = "project")
    private String project;

    @JacksonXmlProperty(localName = "product")
    private String product;

    @JacksonXmlProperty(localName = "version")
    private String version;

}
