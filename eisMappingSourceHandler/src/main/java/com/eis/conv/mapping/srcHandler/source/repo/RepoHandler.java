package com.eis.conv.mapping.srcHandler.source.repo;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.*;

import java.io.IOException;
import java.util.List;

import static com.eis.conv.mapping.core.files.FileHelper.getDirs;


public final class RepoHandler {

    public static RepoRoot loadRepoRoot(String rootPath) throws IOException {
        RepoRoot repoRoot = new RepoRoot(rootPath);

        List<String> folders = getDirs(rootPath);
        folders.forEach(item -> {
            try {
                RepoProject rp = RepoHandler.loadRepoProject(rootPath, item);
                repoRoot.getProjects().add(rp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return repoRoot;
    }


    public static RepoProject loadRepoProject(String rootPath, String projectDirName) throws IOException {
        String dir = FileHelper.extendPath(rootPath, projectDirName);
        RepoProject repoProject = new RepoProject(projectDirName, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            RepoProduct product = loadRepoProduct(dir, item);
            repoProject.getProducts().add(product);
        }
        return repoProject;
    }

    public static RepoProduct loadRepoProduct(String rootPath, String productDirName) throws IOException {
        String dir = FileHelper.extendPath(rootPath, productDirName);
        RepoProduct repoProduct = new RepoProduct(productDirName, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            RepoVersion repoVersion = loadRepoVersion(dir, item);
            repoProduct.getVersions().add(repoVersion);
        }
        return repoProduct;
    }

    public static RepoVersion loadRepoVersion(String rootPath, String versionDirName) throws IOException {
        String dir = FileHelper.extendPath(rootPath, versionDirName);
        RepoVersion repoVersion = new RepoVersion(versionDirName, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            RepoProductItem repoProductItem = loadRepoProductItem(dir, item);
            repoVersion.getProductItems().add(repoProductItem);
        }
        return repoVersion;
    }


    public static RepoProductItem loadRepoProductItem(String productPath, String productItemDirName) throws IOException {
        String dir = FileHelper.extendPath(productPath, productItemDirName);
        RepoProductItem repoProductItem = new RepoProductItem(productItemDirName, dir);
        return repoProductItem;
    }

    public static void loadRepoProductItemFiles(RepoProductItem repoProductItem) throws IOException {
        repoProductItem.setFileNames(FileHelper.getFileNamesAll(repoProductItem.getPath()));
        return;
    }

}