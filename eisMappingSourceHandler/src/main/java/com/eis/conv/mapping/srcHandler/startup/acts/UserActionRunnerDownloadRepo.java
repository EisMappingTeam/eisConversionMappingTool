package com.eis.conv.mapping.srcHandler.startup.acts;

import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserDownloadRepo;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserStartupAction;

import java.io.IOException;

public final class UserActionRunnerDownloadRepo {
    public static void run(AppStartupParameters appParameters, UserStartupAction action, String user, String password) throws IOException {
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
