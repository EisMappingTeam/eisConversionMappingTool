package com.eis.conv.mapping.srcHandler.source.startup.actions;


import com.eis.conv.mapping.srcHandler.source.startup.param.app.AppAllCommands;
import com.eis.conv.mapping.srcHandler.source.startup.param.app.AppRepoCommand;
import com.eis.conv.mapping.srcHandler.source.startup.param.app.AppStartupParameters;


import java.io.IOException;

public final class AppCommandRunner {

    public static void runCommandDownloadRepo(String user, String password, AppStartupParameters appParameters, String project, String product, String version) throws IOException {

        AppRepoCommand appRepoCommand = appParameters.getAppRepoCommand(AppAllCommands.DOWNLOAD_REPO, project, product);
        if (appRepoCommand == null) {
            System.out.println("Tag with 'commandName' = " + AppAllCommands.DOWNLOAD_REPO.toString() + "\n 'project' = " + project + "\n 'product' = " + product + "\nNot found in " + appParameters.getFileName());
        }
    }

}
