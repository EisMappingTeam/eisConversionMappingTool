package com.eis.conv.mapping.srcHandler.source.repo.repoObjects;

import java.util.ArrayList;
import java.util.List;


public class RepoVersion {
    private String versionName = "";
    private String path = "";

    private List<RepoProductItem> productItems = new ArrayList<>();

//    List<String> fileNames = new ArrayList<>();


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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<RepoProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<RepoProductItem> productItems) {
        this.productItems = productItems;
    }

    public RepoProductItem getProductItem(String productItemName) {
        RepoProductItem result = productItems.stream().filter(prodItm -> prodItm.getProductItemName().equalsIgnoreCase(productItemName)).findFirst().orElse(null);
        return result;
    }

    public boolean isPresentProductItem(String productItemName) {
        return productItems.stream().filter(productItm -> productItm.getProductItemName().equalsIgnoreCase(productItemName)).findFirst().isPresent();
    }


//    public void loadFilesList() throws IOException {
//        setFileNames(FileHelper.getFileNamesAll(this.path));
//    }

}
