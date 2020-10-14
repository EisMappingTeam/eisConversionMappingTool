package com.eis.conv.mapping.srcHandler.startup.params.app;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AppRepoCommand {

    @JacksonXmlProperty(localName = "commandName")
    private String commandName;

    @JacksonXmlProperty(localName = "project")
    private String project;

    @JacksonXmlProperty(localName = "product")
    private String product;


    @JacksonXmlProperty(localName = "userPattern")
    private String userPattern;

    @JacksonXmlProperty(localName = "passwordPattern")
    private String passwordPattern;

    @JacksonXmlProperty(localName = "resultDirPattern")
    private String resultDirPattern;

    @JacksonXmlProperty(localName = "command")
    private String command;

    @JacksonXmlProperty(localName = "ignoreAnnotationList")
    private String ignoreAnnotationList;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUserPattern() {
        return userPattern;
    }

    public void setUserPattern(String userPattern) {
        this.userPattern = userPattern;
    }

    public String getPasswordPattern() {
        return passwordPattern;
    }

    public void setPasswordPattern(String passwordPattern) {
        this.passwordPattern = passwordPattern;
    }

    public String getResultDirPattern() {
        return resultDirPattern;
    }

    public void setResultDirPattern(String resultDirPattern) {
        this.resultDirPattern = resultDirPattern;
    }

    public String getCommand() {
        return StringHelper.notNullString(command);
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getIgnoreAnnotationList() {
        return StringHelper.notNullString(ignoreAnnotationList);
    }

    public void setIgnoreAnnotationList(String ignoreAnnotationList) {
        this.ignoreAnnotationList = ignoreAnnotationList;
    }
}
