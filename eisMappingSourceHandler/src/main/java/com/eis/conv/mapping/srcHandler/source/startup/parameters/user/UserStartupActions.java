package com.eis.conv.mapping.srcHandler.source.startup.parameters.user;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;


public class UserStartupActions {
    @JacksonXmlElementWrapper(localName = "action", useWrapping = false)
    private UserStartupAction[] action;


    public UserStartupAction[] getAction() {
        return action;
    }

    public void setAction(UserStartupAction[] action) {
        this.action = action;
    }
}
