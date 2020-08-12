package com.eis.conv.mapping.srcHandler.startup.param;

import com.eis.conv.mapping.srcHandler.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class ParametersReaderTest {
    private final String DIR = "src/test/resources/parameters";
    private final String FILE_NAME_USER = "userParametersTest.xml";
    private final String FILE_NAME_APP = "appParametersTest.xml";

    //User
    private final String USRCONTENT_MAIN_USER = "someone";
    private final String USRCONTENT_MAIN_PASSW = "xxx001";
    private final String USRCONTENT_MAIN_APPSETTINGFILE = "some_file_path";

    private final String USRCONTENT_DOWNLOAD_REPO_PRJ = "BASE";
    private final String USRCONTENT_DOWNLOAD_REPO_PROD = "AC";
    private final String USRCONTENT_DOWNLOAD_REPO_VER = "S12.4";
    private final String USRCONTENT_DOWNLOAD_REPO_ALIAS = "conv";

    private final String USRCONTENT_LOADSRC_PRJ = "MAS";
    private final String USRCONTENT_LOADSRC_PROD = "CR";
    private final String USRCONTENT_LOADSRC_VER = "S2.1.1";
    private final String USRCONTENT_LOADSRC_RESULTFILE = "some_result_path";

    //App
    private final String APPCONTENT_MAIN_REPO_DIR = "repo_root_folder";

    @Test
    public void readUserParametersTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(FILE_NAME_USER);


        UserStartupParameters parameters = ParametersReader.readUserParameters(file.toString());

        //Test data
        assertThat(parameters.getUser()).isEqualTo(USRCONTENT_MAIN_USER);
        assertThat(parameters.getPassword()).isEqualTo(USRCONTENT_MAIN_PASSW);
        assertThat(parameters.getApplicationSettingsFile()).isEqualTo(USRCONTENT_MAIN_APPSETTINGFILE);

        assertThat(parameters.getActions()).isNotEqualTo(null);
        UserStartupActions userStartupActions = parameters.getActions();

        assertThat(userStartupActions.getAction().size()).isEqualTo(2);
        UserStartupAction actionDownloadRepo = userStartupActions.getActionByName(UserAllActions.DOWNLOAD_REPO.getAction());
        UserStartupAction actionLoadSourceFiles = userStartupActions.getActionByName(UserAllActions.LOAD_SOURCE.getAction());

        assertThat(actionDownloadRepo.getActionName()).isEqualTo(UserAllActions.DOWNLOAD_REPO.getAction());
        assertThat(actionLoadSourceFiles.getActionName()).isEqualTo(UserAllActions.LOAD_SOURCE.getAction());

        //Download repo
        UserDownloadRepo userDownloadRepo = actionDownloadRepo.getDownloadRepo();
        assertThat(userDownloadRepo.getProject()).isEqualTo(USRCONTENT_DOWNLOAD_REPO_PRJ);
        assertThat(userDownloadRepo.getProduct()).isEqualTo(USRCONTENT_DOWNLOAD_REPO_PROD);
        assertThat(userDownloadRepo.getVersion()).isEqualTo(USRCONTENT_DOWNLOAD_REPO_VER);
        assertThat(userDownloadRepo.getSourceAlias()).isEqualTo(USRCONTENT_DOWNLOAD_REPO_ALIAS);

        //Load source files
        UserLoadSource userLoadSource = actionLoadSourceFiles.getLoadSource();
        assertThat(userLoadSource.getProject()).isEqualTo(USRCONTENT_LOADSRC_PRJ);
        assertThat(userLoadSource.getProduct()).isEqualTo(USRCONTENT_LOADSRC_PROD);
        assertThat(userLoadSource.getVersion()).isEqualTo(USRCONTENT_LOADSRC_VER);
        assertThat(userLoadSource.getResultDir()).isEqualTo(USRCONTENT_LOADSRC_RESULTFILE);

    }

    @Test
    void readAppParametersTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(FILE_NAME_APP);

        AppStartupParameters parameters = ParametersReader.readAppParameters(file.toString());

        assertThat(parameters.getRepoRootDir()).isEqualTo(APPCONTENT_MAIN_REPO_DIR);
    }
}
