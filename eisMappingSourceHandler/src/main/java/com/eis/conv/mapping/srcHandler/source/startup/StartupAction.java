package com.eis.conv.mapping.srcHandler.source.startup;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class StartupAction {
    @JacksonXmlProperty(localName = "actionName")
    private String actionName;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
}
