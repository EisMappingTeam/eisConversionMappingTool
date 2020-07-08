package com.eis.conv.mapping.srcHandler.source.repo;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoProduct;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoProject;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoRoot;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoVersion;

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


    public static RepoProject loadRepoProject(String rootPath, String project) throws IOException {
        String dir = FileHelper.extendPath(rootPath, project);
        RepoProject repoProject = new RepoProject(project, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            RepoProduct product = loadRepoProduct(dir, item);
            repoProject.getProducts().add(product);
        }
        return repoProject;
    }

    public static RepoProduct loadRepoProduct(String rootPath, String product) throws IOException {
        String dir = FileHelper.extendPath(rootPath, product);
        RepoProduct repoProduct = new RepoProduct(product, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            RepoVersion repoVersion = loadRepoVersion(dir, item);
            repoProduct.getVersions().add(repoVersion);
        }
        return repoProduct;
    }


    public static RepoVersion loadRepoVersion(String productPath, String version) throws IOException {
        String dir = FileHelper.extendPath(productPath, version);
        RepoVersion repoVersion = new RepoVersion(version, dir);

        return repoVersion;
    }


}
