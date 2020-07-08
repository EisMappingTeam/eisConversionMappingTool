package com.eis.conv.mapping.srcHandler.source.repo.repoObjects;

import java.util.ArrayList;
import java.util.List;

public class RepoProduct {

    private String productName = "";
    private String path = "";
    private List<RepoVersion> versions = new ArrayList<>();

    public RepoProduct() {
    }

    public RepoProduct(String productName, String path) {
        this.productName = productName;
        this.path = path;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<RepoVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<RepoVersion> versions) {
        this.versions = versions;
    }

    public RepoVersion getVersion(String versionName) {
        RepoVersion result = versions.stream().filter(ver -> ver.getVersionName().equalsIgnoreCase(versionName)).findFirst().orElse(null);
        return result;
    }

    public boolean isPresentVersion(String versionName) {
        return versions.stream().filter(ver -> ver.getVersionName().equalsIgnoreCase(versionName)).findFirst().isPresent();
    }

}
