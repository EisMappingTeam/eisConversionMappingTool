package com.eis.conv.mapping.srcHandler.source.repo;

import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoFolder;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepoHandlerTest {
    private final String DIR = "src/test/resources/repo";
    private final String PRJ_01 = "proj01";
    private final String PRJ_02 = "proj02";
    private final String PRODUCT_01 = "PROD_01";
    private final String PRODUCT_02 = "PROD_02";
    private final String VERSION_01 = "S01";
    private final String VERSION_02 = "S02";
    private final String SRCPART_BASE = "base";
    private final String SRCPART_CONV = "conv";

    @Test
    public void loadFolders() {
        Path workingDir = Path.of("", DIR);
        RepoFolder rf = RepoHandler.loadFolders(workingDir.toString(), "");

        assertThat(rf.getName()).isEqualTo("");
        assertThat(rf.getPath()).isEqualTo(workingDir.toString());
        assertThat(rf.getSubFolders().size()).isEqualTo(2);
        assertThat(rf.isPresentSubFolder(PRJ_01)).isEqualTo(true);
        assertThat(rf.isPresentSubFolder(PRJ_02)).isEqualTo(true);
    }

    @Test
    public void loadRepo() throws IOException {
        Path workingDir = Path.of("", DIR);

        List<RepoFolder> src = RepoHandler.loadRepo(PRJ_01, PRODUCT_01, VERSION_01, workingDir.toString());
        assertThat(src.size()).isEqualTo(2);

        RepoFolder srcPartBase = src.stream().filter(item -> item.getName().equalsIgnoreCase(SRCPART_BASE)).findFirst().orElse(null);
        RepoFolder srcPartConv = src.stream().filter(item -> item.getName().equalsIgnoreCase(SRCPART_CONV)).findFirst().orElse(null);
        assertThat(srcPartBase).isNotEqualTo(null);
        assertThat(srcPartBase.getName()).isEqualTo(SRCPART_BASE);
        assertThat(srcPartConv).isNotEqualTo(null);
        assertThat(srcPartConv.getName()).isEqualTo(SRCPART_CONV);

        srcPartConv.loadFilesAll();
        assertThat(srcPartConv.getFilesAll().size()).isEqualTo(2);

    }
}