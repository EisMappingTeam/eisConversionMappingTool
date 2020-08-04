package com.eis.conv.mapping.srcHandler.source.repo.repoObjects;

import java.util.ArrayList;
import java.util.List;

public class RepoProductItem {
    private String productItemName = "";
    private String path = "";

    List<String> fileNames = new ArrayList<>();


    public RepoProductItem() {
    }

    public RepoProductItem(String productItemName, String path) {
        this.productItemName = productItemName;
        this.path = path;
    }

    public String getProductItemName() {
        return productItemName;
    }

    public void setProductItemName(String productItemName) {
        this.productItemName = productItemName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}
