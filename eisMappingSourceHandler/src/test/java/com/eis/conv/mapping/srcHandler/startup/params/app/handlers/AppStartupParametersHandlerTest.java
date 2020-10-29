package com.eis.conv.mapping.srcHandler.startup.params.app.handlers;


import com.eis.conv.mapping.srcHandler.startup.params.app.AppAllCommands;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppRepoCommand;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.params.usr.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppStartupParametersHandlerTest {
    private final String FILE_NAME = "file_name";
    private final String ROOT_DIR = "root_dir";

    //Repo command
    private final String RCMD_PROJECT_01 = "project_01";
    private final String RCMD_PROJECT_02 = "project_02";
    private final String RCMD_PROD_01 = "product_01";
    private final String RCMD_PROD_02 = "product_02";
    private final String RCMD_ANNOTATIONS_LIST = "annotations_list01;annotations_list02";


    @Test
    public void getAppRepoCommandTest() {
        AppStartupParameters appStartupParameters = getAppStartupParameters();

        AppRepoCommand res01 = AppStartupParametersHandler.getAppRepoCommand(appStartupParameters, AppAllCommands.DOWNLOAD_REPO, RCMD_PROJECT_02, RCMD_PROD_02);
        assertThat(res01).isNotEqualTo(null);
        assertThat(res01.getCommandName()).isEqualTo(AppAllCommands.DOWNLOAD_REPO.getCommang());
        assertThat(res01.getProject()).isEqualTo(RCMD_PROJECT_02);
        assertThat(res01.getProduct()).isEqualTo(RCMD_PROD_02);

        AppRepoCommand res02 = AppStartupParametersHandler.getAppRepoCommand(appStartupParameters, AppAllCommands.LOAD_SOURCE);
        assertThat(res02).isNotEqualTo(null);
        assertThat(res02.getIgnoreAnnotationList()).isEqualTo(RCMD_ANNOTATIONS_LIST);

        List<String> ignoredAnnotations= AppStartupParametersHandler.getIgnoredAnnotations(appStartupParameters, AppAllCommands.LOAD_SOURCE);
        assertThat(ignoredAnnotations.size()).isEqualTo(2);
    }

    private AppStartupParameters getAppStartupParameters() {
        AppStartupParameters appStartupParameters = new AppStartupParameters();

        appStartupParameters.setFileName(FILE_NAME);
        appStartupParameters.setRepoRootDir(ROOT_DIR);

        int idx = 0;
        AppRepoCommand[] cmds = new AppRepoCommand[3];
        AppRepoCommand appRepoCommand01 = new AppRepoCommand();
        appRepoCommand01.setProduct(RCMD_PROD_01);
        appRepoCommand01.setProject(RCMD_PROJECT_01);
        appRepoCommand01.setCommandName(AppAllCommands.DOWNLOAD_REPO.getCommang());
        cmds[idx] = appRepoCommand01;
        idx++;

        AppRepoCommand appRepoCommand02 = new AppRepoCommand();
        appRepoCommand02.setProduct(RCMD_PROD_02);
        appRepoCommand02.setProject(RCMD_PROJECT_02);
        appRepoCommand02.setCommandName(AppAllCommands.DOWNLOAD_REPO.getCommang());
        cmds[idx] = appRepoCommand02;
        idx++;

        AppRepoCommand appRepoCommand03 = new AppRepoCommand();
        appRepoCommand03.setCommandName(AppAllCommands.LOAD_SOURCE.getCommang());
        appRepoCommand03.setIgnoreAnnotationList(RCMD_ANNOTATIONS_LIST);
        cmds[idx] = appRepoCommand03;
        idx++;

        appStartupParameters.setRepoCommand(cmds);

        return appStartupParameters;

    }
}