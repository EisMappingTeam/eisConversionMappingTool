package com.eis.conv.mapping.srcHandler.source.startup;


import com.eis.conv.mapping.srcHandler.source.startup.parameters.application.AppAllCommands;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.application.AppRepoCommand;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.application.AppStartupParameters;


import java.io.IOException;

public final class AppCommandRunner {

    public static void runCommandDownloadRepo(String user, String password, String appSettingsFileName, String project, String product, String version) throws IOException {

        AppStartupParameters appStartupParameters = ParametersReader.readAppParameters(appSettingsFileName);
        AppRepoCommand appRepoCommand = appStartupParameters.getAppRepoCommand(AppAllCommands.DOWNLOAD_REPO, project, product);
        if (appRepoCommand == null) {
            System.out.println("Tag with 'commandName' = " + AppAllCommands.DOWNLOAD_REPO.toString() + "\n 'project' = " + project + "\n 'product' = " + product + "\nNot found in " + appSettingsFileName);
        }
    }

}
