package com.eis.conv.mapping.srcHandler.startup.params.usr;


import com.eis.conv.mapping.core.stringsSupport.StringHelper;
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

    @JacksonXmlElementWrapper(localName = "acts", useWrapping = false)
    private UserStartupActions actions;


    public UserStartupParameters() {
    }

    public String getFileName() {
        return StringHelper.notNullString(fileName);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUser() {
        return StringHelper.notNullString(user);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return StringHelper.notNullString(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplicationSettingsFile() {
        return StringHelper.notNullString(applicationSettingsFile);
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
