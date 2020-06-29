package com.eis.conv.mapping.srcHandler.source.repo.repoHandlers;

import com.eis.conv.mapping.srcHandler.source.repo.RepoProject;
import com.eis.conv.mapping.srcHandler.source.repo.RepoRoot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class RepoHandler {
    public static RepoRoot getRepoRoot(String rootPath) throws IOException {
        RepoRoot repoRoot = new RepoRoot(rootPath);

        List<String> folders = getDirs(rootPath);
        folders.forEach(item -> {
            try {
                RepoProject rp = RepoHandler.getRepoProject(rootPath, item);
                repoRoot.getProjects().add(rp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return repoRoot;
    }


    public static RepoProject getRepoProject(String rootPath, String project) throws IOException {
        RepoProject repoProject = new RepoProject(project);

        List<String> folders = getDirs(addToPath(rootPath, project));
        folders.forEach(item -> repoProject.getVersions().add(item));

        return repoProject;
    }


    private static String addToPath(String rootPath, String appendValue) {
        return rootPath + "\\" + appendValue;
    }

    private static List<String> getDirs(String rootPath) {
        List<String> result = new ArrayList<>();
        File f = new File(rootPath);
        for (String itm : f.list()) {
            if (new File(addToPath(rootPath, itm)).isDirectory()) {
                result.add(itm);
            }

        }
        return result;
    }

}
