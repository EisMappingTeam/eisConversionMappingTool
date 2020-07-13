package com.eis.conv.mapping.srcHandler.source.startup.parameters;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class StartupAction {
    @JacksonXmlProperty(localName = "actionName")
    private String actionName;

    @JacksonXmlElementWrapper(localName = "downloadRepo", useWrapping = false)
    private  DownloadRepo downloadRepo;

    @JacksonXmlElementWrapper(localName = "loadSource", useWrapping = false)
    private  LoadSource loadSource;


    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public DownloadRepo getDownloadRepo() {
        return downloadRepo;
    }

    public void setDownloadRepo(DownloadRepo downloadRepo) {
        this.downloadRepo = downloadRepo;
    }

    public LoadSource getLoadSource() {
        return loadSource;
    }

    public void setLoadSource(LoadSource loadSource) {
        this.loadSource = loadSource;
    }
}
