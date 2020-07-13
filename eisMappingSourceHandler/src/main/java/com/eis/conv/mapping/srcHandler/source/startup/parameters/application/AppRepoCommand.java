package com.eis.conv.mapping.srcHandler.source.startup.parameters.application;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AppRepoCommand {

    @JacksonXmlProperty(localName = "commandName")
    private String commandName;

    @JacksonXmlProperty(localName = "project")
    private String project;

    @JacksonXmlProperty(localName = "product")
    private String product;

    @JacksonXmlProperty(localName = "version")
    private String version;

    @JacksonXmlProperty(localName = "userPattern")
    private String userPattern;

    @JacksonXmlProperty(localName = "passwordPattern")
    private String passwordPattern;

    @JacksonXmlProperty(localName = "resultDirPattern")
    private String resultDirPattern;

    @JacksonXmlProperty(localName = "command")
    private String command;

}
