package com.eis.conv.mapping.srcHandler.source.startup;

import com.eis.conv.mapping.srcHandler.source.startup.parameters.ParametersReader;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.application.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupAction;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupActions;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupParameters;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class ParametersReaderTest {
    private final  String DIR = "src/test/resources/parameters";

    @Test
    public void readUserParametersTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve("userParametersTest.xml");
        //String content = Files.readString(file);
        UserStartupParameters parameters = ParametersReader.readUserParameters(file.toString());

        assertThat(parameters.getUser()).isEqualTo("AAA");
        assertThat(parameters.getPassword()).isEqualTo("VVV");
        assertThat(parameters.getApplicationSettingsFile()).isEqualTo("");

        assertThat(parameters.getActions()).isNotEqualTo(null);
        UserStartupActions userStartupActions = parameters.getActions();

        assertThat(userStartupActions.getAction().size()).isEqualTo(2);
        UserStartupAction action0 = userStartupActions.getAction().get(0);
        UserStartupAction action1 = userStartupActions.getAction().get(1);

        assertThat(action0.getActionName()).isEqualTo("DOWNLOAD_REPO");
        assertThat(action1.getActionName()).isEqualTo("LOAD_SOURCE");
    }

    @Test
    void readAppParametersTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve("appParametersTest.xml");
        //String content = Files.readString(file);
        AppStartupParameters parameters = ParametersReader.readAppParameters(file.toString());

        assertThat(parameters.getRepoRootDir()).isEqualTo("repoRoot");
    }
}
