package com.eis.conv.mapping.srcHandler.source.repo;

import java.util.ArrayList;
import java.util.List;

public class RepoProject {
    private String projectName = "";
    private String path = "";
    private List<RepoVersion> versions = new ArrayList<>();

    public RepoProject() {
    }

    public RepoProject(String projectName, String rootPath) {
        this.projectName = projectName;
        this.path = rootPath;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<RepoVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<RepoVersion> versions) {
        this.versions = versions;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RepoVersion getVersion(String versionName) {
        RepoVersion result = versions.stream().filter(ver -> ver.getVersionName().equalsIgnoreCase(versionName)).findFirst().orElse(null);
        return result;
    }

    public boolean isPresentVersion(String versionName) {
        return versions.stream().filter(ver -> ver.getVersionName().equalsIgnoreCase(versionName)).findFirst().isPresent();
    }
}
