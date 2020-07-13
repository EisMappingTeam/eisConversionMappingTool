package com.eis.conv.mapping.srcHandler.source.startup.parameters;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DownloadRepo {

    @JacksonXmlProperty(localName = "project")
    private String project;

    @JacksonXmlProperty(localName = "product")
    private String product;

    @JacksonXmlProperty(localName = "version")
    private String version;


}
