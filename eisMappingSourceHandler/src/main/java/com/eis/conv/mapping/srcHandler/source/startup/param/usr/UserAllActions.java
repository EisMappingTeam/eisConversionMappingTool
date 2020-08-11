package com.eis.conv.mapping.srcHandler.source.startup.param.usr;

public enum UserAllActions {
    DOWNLOAD_REPO("DOWNLOAD_REPO"), //download from Mercurial
    LOAD_SOURCE("LOAD_SOURCE");     //load folders structure and load java-xml-properties

    private final String action;

    UserAllActions(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
