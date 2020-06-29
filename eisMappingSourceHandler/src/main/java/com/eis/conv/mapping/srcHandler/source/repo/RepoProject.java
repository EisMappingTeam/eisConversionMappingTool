package com.eis.conv.mapping.srcHandler.source.repo;

import java.util.ArrayList;
import java.util.List;

public class RepoProject {
    private String projectName;
    private List<String> versions = new ArrayList<String>();

    public RepoProject() {
    }

    public RepoProject(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }
}
