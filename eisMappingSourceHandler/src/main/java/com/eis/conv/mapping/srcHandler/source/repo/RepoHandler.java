package com.eis.conv.mapping.srcHandler.source.repo;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.*;

import java.util.ArrayList;
import java.util.List;

public final class RepoHandler {


    public static RepoFolder loadFolders(String rootPath, String folderName) {
        String dir = FileHelper.extendPath(rootPath, folderName);
        RepoFolder result = new RepoFolder(folderName, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            result.getSubFolders().add(item);
        }
        return result;
    }


    public static List<RepoFolder> loadRepo(String projectName, String productName, String versionName, String repoRootPath) {
        List<RepoFolder> result = new ArrayList<>();

        RepoFolder project = RepoHandler.loadFolders(repoRootPath, projectName);
        RepoFolder product = RepoHandler.loadFolders(project.getPath(), productName);
        RepoFolder version = RepoHandler.loadFolders(product.getPath(), versionName);

        for (String sourcePartDir : version.getSubFolders()) {
            RepoFolder sourcePart = RepoHandler.loadFolders(version.getPath(), sourcePartDir);
            result.add(sourcePart);
        }
        return result;
    }

}
