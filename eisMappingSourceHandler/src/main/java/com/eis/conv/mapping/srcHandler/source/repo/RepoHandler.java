package com.eis.conv.mapping.srcHandler.source.repo;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.*;

import java.util.ArrayList;
import java.util.List;

public final class RepoHandler {


    public static RepoDir loadFolders(String rootPath, String folderName) {
        String dir = FileHelper.extendPath(rootPath, folderName);
        RepoDir result = new RepoDir(folderName, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            result.getSubFolders().add(item);
        }
        return result;
    }


    public static List<RepoDir> loadRepo(String projectName, String productName, String versionName, String repoRootPath) {
        List<RepoDir> result = new ArrayList<>();

        RepoDir project = RepoHandler.loadFolders(repoRootPath, projectName);
        RepoDir product = RepoHandler.loadFolders(project.getPath(), productName);
        RepoDir version = RepoHandler.loadFolders(product.getPath(), versionName);

        for (String sourcePartDir : version.getSubFolders()) {
            RepoDir sourcePart = RepoHandler.loadFolders(version.getPath(), sourcePartDir);
            result.add(sourcePart);
        }
        return result;
    }

}
