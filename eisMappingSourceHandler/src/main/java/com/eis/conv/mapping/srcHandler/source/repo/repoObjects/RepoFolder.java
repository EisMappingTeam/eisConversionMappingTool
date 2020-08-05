package com.eis.conv.mapping.srcHandler.source.repo.repoObjects;

import com.eis.conv.mapping.core.files.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepoFolder {
    private String name = "";
    private String path = "";
    private List<String> subFolders = new ArrayList<String>();
    private List<String> files = new ArrayList<>();

    public RepoFolder() {
    }

    public RepoFolder(String name) {
        this.name = name;
    }

    public RepoFolder(String name, String path) {
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

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public boolean isPresentSubFolder(String subFolderName) {
        return subFolders.stream().filter(dir -> dir.equalsIgnoreCase(subFolderName)).findFirst().isPresent();
    }

    public void loadFiles() throws IOException {
        files = FileHelper.getFileNamesAll(path);
        return;
    }


}
