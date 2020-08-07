package com.eis.conv.mapping.srcHandler.source.startup.actions;

import com.eis.conv.mapping.srcHandler.processing.readSource.SourceReader;
import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoFolder;

import java.io.IOException;
import java.util.List;

public final class ReadSourceAction {
    public static SourceReader readRepo(String project, String product, String version, String rootRepoDir) throws IOException {

        if (project.length() < 1 || product.length() < 1 || version.length() < 1) {
            return new SourceReader();
        }

        List<RepoFolder> src = RepoHandler.loadRepo(project, product, version, rootRepoDir);
        SourceReader sourceReader = new SourceReader();
        sourceReader.readRepo(project, product, version, src);
        return sourceReader;
    }
}
