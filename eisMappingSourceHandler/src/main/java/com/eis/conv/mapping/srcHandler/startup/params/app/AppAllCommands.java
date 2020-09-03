package com.eis.conv.mapping.srcHandler.startup.params.app;


public enum AppAllCommands {
    DOWNLOAD_REPO("REPO_DOWNLOAD");

    private final String commang;

    AppAllCommands(String action) {
        this.commang = action;
    }

    public String getCommang() {
        return commang;
    }
}