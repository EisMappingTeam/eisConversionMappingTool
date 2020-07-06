package com.eis.conv.mapping.srcHandler.source.repo;

import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoProject;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoRoot;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoVersion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        RepoProject repoProject = new RepoProject(project, addToPath(rootPath, project));

        List<String> folders = getDirs(addToPath(rootPath, project));
        folders.forEach(item -> repoProject.getVersions().add(new RepoVersion(item, addToPath(repoProject.getPath(), item))));

        return repoProject;
    }


    public static List<String> getAllFileNames(String rootPath) throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(rootPath));
        List<String> result = walk.filter(Files::isRegularFile)
                .map(x -> x.toString()).collect(Collectors.toList());
        return result;
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
