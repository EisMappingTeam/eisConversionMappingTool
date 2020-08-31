package com.eis.conv.mapping.srcHandler.startup.actions;

import com.eis.conv.mapping.srcHandler.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserDownloadRepo;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupAction;

import java.io.IOException;

public final class UserActionRunnerDownloadRepo {
    public static void run(AppStartupParameters appParameters, UserStartupAction action, String user, String password) throws IOException {
        System.out.println("Startup action: " + action.getActionName());

        //Actions selector
        UserDownloadRepo userDownloadRepo = action.getDownloadRepo();
        if (userDownloadRepo != null) {
            AppCommandRunner.runCommandDownloadRepo(
                    user,
                    password,
                    appParameters,
                    userDownloadRepo.getProject(),
                    userDownloadRepo.getProduct(),
                    userDownloadRepo.getVersion(),
                    userDownloadRepo.getSourceAlias());


        }
    }

}
