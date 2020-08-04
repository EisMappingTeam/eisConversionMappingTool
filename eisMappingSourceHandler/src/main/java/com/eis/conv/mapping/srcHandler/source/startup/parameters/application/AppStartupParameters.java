package com.eis.conv.mapping.srcHandler.source.startup.parameters.application;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "MappingToolApplication")
public class AppStartupParameters {

    private String fileName;

    @JacksonXmlProperty(localName = "repoRootDir")
    private String repoRootDir;

    @JacksonXmlElementWrapper(localName = "repoCommand", useWrapping = false)
    private AppRepoCommand[] repoCommand;

    public String getRepoRootDir() {
        return repoRootDir;
    }

    public void setRepoRootDir(String repoRootDir) {
        this.repoRootDir = repoRootDir;
    }

    public AppRepoCommand[] getRepoCommand() {
        return repoCommand;
    }

    public void setRepoCommand(AppRepoCommand[] repoCommand) {
        this.repoCommand = repoCommand;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public AppRepoCommand getAppRepoCommand(AppAllCommands cmd, String project, String product) {
        for (AppRepoCommand item : this.repoCommand) {
            if (item != null) {
                if (item.getCommandName().equalsIgnoreCase(cmd.getCommang())) {
                    if (project != null && product != null && item.getProject() != null && item.getProduct() != null) {
                        if (project.equalsIgnoreCase(item.getProject()) && product.equalsIgnoreCase(item.getProduct())) {
                            return item;
                        }
                    }
                }
            }
        }
        return null;
    }

}
