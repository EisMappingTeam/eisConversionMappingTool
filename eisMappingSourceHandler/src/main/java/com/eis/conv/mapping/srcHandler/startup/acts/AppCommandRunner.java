package com.eis.conv.mapping.srcHandler.startup.acts;


import com.eis.conv.mapping.core.shellSupport.WinCommand;
import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppAllCommands;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppRepoCommand;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.params.app.handlers.AppStartupParametersHandler;


import java.io.IOException;

public final class AppCommandRunner {

    public static boolean runCommandDownloadRepo(String user, String password, AppStartupParameters appParameters, String project, String product, String version, String sourceAlias) throws IOException {

        //AppRepoCommand appRepoCommand =  appParameters.getAppRepoCommand(AppAllCommands.DOWNLOAD_REPO, project, product);
        AppRepoCommand appRepoCommand = AppStartupParametersHandler.getAppRepoCommand(appParameters, AppAllCommands.DOWNLOAD_REPO, project, product);

        if (appRepoCommand == null) {
            System.out.println("Tag with 'commandName' = " + AppAllCommands.DOWNLOAD_REPO.toString() + "\n 'project' = " + project + "\n 'product' = " + product + "\nNot found in " + appParameters.getFileName());
            return false;
        }


        String cmd = StringHelper.notNullString(appRepoCommand.getCommand());
        //Add to command user
        if (StringHelper.notNullString(appRepoCommand.getUserPattern()) != "" && StringHelper.notNullString(user) != "") {
            cmd = cmd.replace(appRepoCommand.getUserPattern(), user);
        }
        //Add to command passw
        if (StringHelper.notNullString(appRepoCommand.getPasswordPattern()) != "" && StringHelper.notNullString(password) != "") {
            cmd = cmd.replace(appRepoCommand.getPasswordPattern(), password);
        }


        if (cmd.length() < 1) {
            return false;
        }

        try {
            WinCommand.run(cmd);
        } catch (IOException e) {
            System.out.println("Command execution error\n");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
