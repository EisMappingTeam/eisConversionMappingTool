package com.eis.conv.mapping.srcHandler.source.startup;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "MappingTool")
public class StartupParameters {

    @JacksonXmlProperty(localName = "user")
    private String user;
    @JacksonXmlProperty(localName = "password")
    private String password;
    @JacksonXmlProperty(localName = "applicationSettingsFile")
    private String applicationSettingsFile;

    @JacksonXmlElementWrapper(localName = "actions", useWrapping = false)
    private StartupAction[] actions;

    public StartupAction[] getActions() {
        return actions;
    }

}
