package com.eis.conv.mapping.srcHandler.startup.params.usr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class UserStartupAction {
    @JacksonXmlProperty(localName = "actionName")
    private String actionName;

    @JacksonXmlElementWrapper(localName = "downloadRepo", useWrapping = false)
    private UserDownloadRepo downloadRepo;

    @JacksonXmlElementWrapper(localName = "loadSource", useWrapping = false)
    private UserLoadSource loadSource;

    @JacksonXmlElementWrapper(localName = "compareImportExport", useWrapping = false)
    private UserCompareImportExport compareImportExport;


    public UserStartupAction() {
    }

    public UserStartupAction(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public UserDownloadRepo getDownloadRepo() {
        return downloadRepo;
    }

    public void setDownloadRepo(UserDownloadRepo downloadRepo) {
        this.downloadRepo = downloadRepo;
    }

    public UserLoadSource getLoadSource() {
        return loadSource;
    }

    public void setLoadSource(UserLoadSource loadSource) {
        this.loadSource = loadSource;
    }

    public UserCompareImportExport getCompareImportExport() {
        return compareImportExport;
    }

    public void setCompareImportExport(UserCompareImportExport compareImportExport) {
        this.compareImportExport = compareImportExport;
    }

    @Override
    public String toString() {
        return "UserStartupAction{" +
                "actionName='" + actionName + '\'' +
                ", downloadRepo=" + downloadRepo +
                ", loadSource=" + loadSource +
                ", compareImportExport=" + compareImportExport +
                '}';
    }
}
