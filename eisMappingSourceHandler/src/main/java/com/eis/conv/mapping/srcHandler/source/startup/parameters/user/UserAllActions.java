package com.eis.conv.mapping.srcHandler.source.startup.parameters.user;

public enum UserAllActions {
    LOAD_REPO("LOAD_REPO"),
    LOAD_SOURCE("LOAD_SOURCE");

    private final String action;

    UserAllActions(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
