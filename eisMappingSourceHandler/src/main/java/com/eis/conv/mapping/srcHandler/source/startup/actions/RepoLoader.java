package com.eis.conv.mapping.srcHandler.source.startup.actions;

import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.*;

import java.io.IOException;

public final class RepoLoader {

    public static void loadRepoSource(String project, String product, String version, String repoRootPath) throws IOException {

        if (project.length() < 1 || product.length() < 1 || version.length() < 1) {
            return;
        }

//        //Load REPO: Project-Product-Versions
//        RepoRoot repoRoot = RepoHandler.loadRepoRoot(repoRootPath);
//        if (!repoRoot.isPresentProject(project)) {
//            return;
//        }
//        RepoProject repoProject = repoRoot.getProject(project);
//        if (!repoProject.isPresentProduct(product)) {
//            return;
//        }
//        RepoProduct repoProduct = repoProject.getProduct(product);
//        if
//        RepoVersion rv = rProd.getVersion("S02");
//        RepoProductItem rpi = rv.getProductItem("BASE");
//        RepoHandler.loadRepoProductItemFiles(rpi);


    }


//
//    public static RepoRoot  loadRepoDirs(String project, String product, String version, String repoRootPath) throws IOException {
//        if (project.length() < 1 || product.length() < 1 || version.length() < 1) {
//            return new RepoRoot();
//        }
//
//        //Load REPO: Project-Product-Versions
//        RepoRoot repoRoot = RepoHandler.loadRepoRoot(repoRootPath);
//        if (!repoRoot.isPresentProject(project)) {
//            return new RepoRoot();
//        }
//        RepoProject repoProject = repoRoot.getProject(project);
//        if (!repoProject.isPresentProduct(product)) {
//            return;
//        }
//        RepoProduct repoProduct = repoProject.getProduct(product);
//        if
//        RepoVersion rv = rProd.getVersion("S02");
//        RepoProductItem rpi = rv.getProductItem("BASE");
//        RepoHandler.loadRepoProductItemFiles(rpi);
//
//
//    }


}
