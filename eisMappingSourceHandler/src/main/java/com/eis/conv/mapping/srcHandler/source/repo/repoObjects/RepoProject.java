package com.eis.conv.mapping.srcHandler.source.repo.repoObjects;

import java.util.ArrayList;
import java.util.List;

public class RepoProject {
    private String projectName = "";
    private String path = "";
    private List<RepoProduct> products = new ArrayList<>();

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

    public List<RepoProduct> getProducts() {
        return products;
    }

    public void setProducts(List<RepoProduct> products) {
        this.products = products;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RepoProduct getProduct(String productName) {
        RepoProduct result = products.stream().filter(prod -> prod.getProductName().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return result;
    }

    public boolean isPresentProduct(String productName) {
        return products.stream().filter(product -> product.getProductName().equalsIgnoreCase(productName)).findFirst().isPresent();
    }
}
