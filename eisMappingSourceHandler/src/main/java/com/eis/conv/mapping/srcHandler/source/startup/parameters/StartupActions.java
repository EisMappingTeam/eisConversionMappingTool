package com.eis.conv.mapping.srcHandler.source.startup.parameters;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;


public class StartupActions {
    @JacksonXmlElementWrapper(localName = "action", useWrapping = false)
    private StartupAction[] action;


    public StartupAction[] getAction() {
        return action;
    }

    public void setAction(StartupAction[] action) {
        this.action = action;
    }
}
