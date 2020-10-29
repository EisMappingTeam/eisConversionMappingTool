package com.eis.conv.mapping.srcHandler.startup.params.app;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "MappingToolApplication")
public class AppStartupParameters {

    private String fileName;

    @JacksonXmlProperty(localName = "repoRootDir")
    private String repoRootDir;

    @JacksonXmlProperty(localName = "repoBlackList")
    private String repoBlackList;


    @JacksonXmlElementWrapper(localName = "repoCommand", useWrapping = false)
    private AppRepoCommand[] repoCommand;

    public String getRepoRootDir() {
        return StringHelper.notNullString(repoRootDir);
    }

    public void setRepoRootDir(String repoRootDir) {
        this.repoRootDir = repoRootDir;
    }

    public String getRepoBlackList() {
        return repoBlackList;
    }

    public void setRepoBlackList(String repoBlackList) {
        this.repoBlackList = repoBlackList;
    }

    public AppRepoCommand[] getRepoCommand() {
        return repoCommand;
    }

    public void setRepoCommand(AppRepoCommand[] repoCommand) {
        this.repoCommand = repoCommand;
    }

    public String getFileName() {
        return StringHelper.notNullString(fileName);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
