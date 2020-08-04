package com.eis.conv.mapping.srcHandler.source.startup;

import com.eis.conv.mapping.srcHandler.source.startup.parameters.application.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.*;

import java.io.IOException;

public final class UserActionRunner {

    public static void runActions(UserStartupParameters userParameters, AppStartupParameters appParameters) throws IOException {

        UserStartupActions actions = userParameters.getActions();

        if (actions == null) {
            return;
        }

        for (UserStartupAction action : actions.getAction()) {

            if (action.getActionName().equalsIgnoreCase(UserAllActions.DOWNLOAD_REPO.getAction())) {
                System.out.println(UserAllActions.DOWNLOAD_REPO.getAction());


                UserDownloadRepo userDownloadRepo = action.getDownloadRepo();
                if (userDownloadRepo != null) {
                    AppCommandRunner.runCommandDownloadRepo(userParameters.getUser(), userParameters.getPassword(), appParameters, userDownloadRepo.getProject(), userDownloadRepo.getProduct(), userDownloadRepo.getVersion());
                }


            } else if (action.getActionName().equalsIgnoreCase(UserAllActions.LOAD_SOURCE.getAction())) {
                //Read folders and sources
                System.out.println(UserAllActions.LOAD_SOURCE.getAction());
            }

        }
    }



}
