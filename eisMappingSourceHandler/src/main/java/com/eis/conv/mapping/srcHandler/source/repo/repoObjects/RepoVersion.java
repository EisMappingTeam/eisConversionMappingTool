package com.eis.conv.mapping.srcHandler.source.repo.repoObjects;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepoVersion {
    private String versionName = "";
    private String path = "";
    List<String> fileNames = new ArrayList<>();


    public RepoVersion() {

    }

    public RepoVersion(String versionName, String path) {
        this.versionName = versionName;
        this.path = path;
    }


    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void loadFilesList() throws IOException {
        setFileNames(FileHelper.getFileNamesAll(this.path));
    }

}
