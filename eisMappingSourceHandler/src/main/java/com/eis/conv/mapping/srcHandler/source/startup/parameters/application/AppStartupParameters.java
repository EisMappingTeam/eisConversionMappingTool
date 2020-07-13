package com.eis.conv.mapping.srcHandler.source.startup.parameters.application;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "MappingToolApplication")
public class AppStartupParameters {

    @JacksonXmlProperty(localName = "repoRootDir")
    private String repoRootDir;

    @JacksonXmlElementWrapper(localName = "appRepoCommand", useWrapping = false)
    private AppRepoCommand[] appRepoCommand;

    public String getRepoRootDir() {
        return repoRootDir;
    }

    public void setRepoRootDir(String repoRootDir) {
        this.repoRootDir = repoRootDir;
    }

    public AppRepoCommand[] getAppRepoCommand() {
        return appRepoCommand;
    }

    public void setAppRepoCommand(AppRepoCommand[] appRepoCommand) {
        this.appRepoCommand = appRepoCommand;
    }
}
