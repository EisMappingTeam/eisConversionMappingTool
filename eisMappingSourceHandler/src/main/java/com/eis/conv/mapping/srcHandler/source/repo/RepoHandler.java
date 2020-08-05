package com.eis.conv.mapping.srcHandler.source.repo;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.*;

import java.io.IOException;
import java.util.List;

public final class RepoHandler {


    public static RepoFolder loadRepoFolders(String rootPath, String folderName) throws IOException {
        String dir = FileHelper.extendPath(rootPath, folderName);
        RepoFolder result = new RepoFolder(folderName, dir);

        List<String> folders = FileHelper.getDirs(dir);
        for (String item : folders) {
            result.getSubFolders().add(item);
        }
        return result;
    }


}
