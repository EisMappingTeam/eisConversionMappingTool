package com.eis.conv.mapping.srcHandler.source.startup.parameters.application;

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
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
