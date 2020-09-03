package com.eis.conv.mapping.srcHandler.source.repo.repoObjects;

import com.eis.conv.mapping.core.filesSupport.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepoDir {
    private String name = "";
    private String path = "";
    private List<String> subFolders = new ArrayList<String>();
    private List<String> filesAll = new ArrayList<>();

    public RepoDir() {
    }

    public RepoDir(String name) {
        this.name = name;
    }

    public RepoDir(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<String> subFolders) {
        this.subFolders = subFolders;
    }

    public List<String> getFilesAll() {
        return filesAll;
    }

    public void setFilesAll(List<String> filesAll) {
        this.filesAll = filesAll;
    }

    public boolean isPresentSubFolder(String subFolderName) {
        return subFolders.stream().filter(dir -> dir.equalsIgnoreCase(subFolderName)).findFirst().isPresent();
    }

    public void loadFilesAll() throws IOException {
        filesAll = FileHelper.getFileNamesAll(path);
        return;
    }


}
