package com.eis.conv.mapping.srcHandler.source.repo;


import com.eis.conv.mapping.srcHandler.source.repo.repoHandlers.RepoHandler;

import java.util.ArrayList;
import java.util.List;


public class RepoRoot {
    private String rootDir;
    private List<RepoProject> projects = new ArrayList<RepoProject>();

    public RepoRoot() {
    }

    public RepoRoot(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public List<RepoProject> getProjects() {
        return projects;
    }

    public void setProjects(List<RepoProject> projects) {
        this.projects = projects;
    }

    public RepoProject getProject(String projectName) {
        RepoProject result = projects.stream().filter(prj -> prj.getProjectName().equalsIgnoreCase(projectName)).findFirst().orElse(null);
        return result;
    }

    public boolean isPresentProject(String projectName) {
        return projects.stream().filter(prj -> prj.getProjectName().equalsIgnoreCase(projectName)).findFirst().isPresent();

    }

//    public void loadVersionFileList(String project, String version) {
//        if (this.isPresentProject(project)) {
//            RepoProject rp = this.getProject(project);
//            if (rp.isPresentVersion(version)) {
//                RepoVersion ver = rp.getVersion(version);
//                ver.setFileNames(RepoHandler.getAllFileNames());
//            }
//        }
//
//    }
}
