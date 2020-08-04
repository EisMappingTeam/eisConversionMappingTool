package com.eis.conv.mapping.srcHandler.source.startup;

import com.eis.conv.mapping.srcHandler.source.startup.parameters.ParameterFilesHelper;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.*;

import java.io.IOException;

public final class UserActionRunner {

    public static void runActions(UserStartupParameters parameters) throws IOException {

        UserStartupActions actions = parameters.getActions();

        if (actions == null) {
            return;
        }

        for (UserStartupAction action : actions.getAction()) {

            if (action.getActionName().equalsIgnoreCase(UserAllActions.LOAD_REPO.getAction())) {
                System.out.println(UserAllActions.LOAD_REPO.getAction());

                String appParamFile = new ParameterFilesHelper().getAppSettingsFileName(parameters.getApplicationSettingsFile());
                UserDownloadRepo userDownloadRepo = action.getDownloadRepo();
                if (userDownloadRepo != null) {
                    AppCommandRunner.runCommandDownloadRepo(parameters.getUser(), parameters.getPassword(), appParamFile, userDownloadRepo.getProject(), userDownloadRepo.getProduct(), userDownloadRepo.getVersion());
                }

            } else if (action.getActionName().equalsIgnoreCase(UserAllActions.LOAD_SOURCE.getAction())) {
                System.out.println(UserAllActions.LOAD_SOURCE.getAction());
            }

        }
    }



}
