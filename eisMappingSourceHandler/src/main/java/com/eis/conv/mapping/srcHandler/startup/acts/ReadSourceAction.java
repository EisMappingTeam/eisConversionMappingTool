package com.eis.conv.mapping.srcHandler.startup.acts;

import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoDir;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public final class ReadSourceAction {
    public static SourceFilesReader readRepo(String project, String product, String version, String rootRepoDir) throws IOException, ParserConfigurationException, SAXException {

        if (project.length() < 1 || product.length() < 1 || version.length() < 1) {
            return new SourceFilesReader();
        }

        List<RepoDir> src = RepoHandler.loadRepo(project, product, version, rootRepoDir);
        SourceFilesReader sourceReader = new SourceFilesReader();
        sourceReader.readRepo(project, product, version, src);
        return sourceReader;
    }
}
