package com.eis.conv.mapping.srcHandler.source.startup.param.usr;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "MappingTool")
public class UserStartupParameters {
    private String fileName;

    @JacksonXmlProperty(localName = "user")
    private String user;
    @JacksonXmlProperty(localName = "password")
    private String password;
    @JacksonXmlProperty(localName = "applicationSettingsFile")
    private String applicationSettingsFile;

    @JacksonXmlElementWrapper(localName = "actions", useWrapping = false)
    private UserStartupActions actions;


    public UserStartupParameters() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplicationSettingsFile() {
        return applicationSettingsFile;
    }

    public void setApplicationSettingsFile(String applicationSettingsFile) {
        this.applicationSettingsFile = applicationSettingsFile;
    }

    public UserStartupActions getActions() {
        return actions;
    }

    public void setActions(UserStartupActions actions) {
        this.actions = actions;
    }
}
