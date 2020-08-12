package com.eis.conv.mapping.srcHandler.startup.param.usr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserStartupActions {
    @JacksonXmlElementWrapper(localName = "action", useWrapping = false)
    private UserStartupAction[] action;


    public List<UserStartupAction> getAction() {
        if (action == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(action);
    }

    public void setAction(List<UserStartupAction> _action) {
        this.action = _action.stream().toArray(UserStartupAction[]::new);
    }

    public UserStartupAction getActionByName(String actionName) {
        List<UserStartupAction> act = getAction();
        return act.stream().filter(item -> item.getActionName().equalsIgnoreCase(actionName)).findFirst().orElse(new UserStartupAction());
    }
}
