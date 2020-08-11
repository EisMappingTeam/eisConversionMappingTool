package com.eis.conv.mapping.srcHandler.source.startup;

import com.eis.conv.mapping.srcHandler.source.startup.actions.ReadSourceAction;
import com.eis.conv.mapping.srcHandler.source.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.param.usr.UserStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.param.usr.UserStartupActions;
import com.eis.conv.mapping.srcHandler.source.startup.param.usr.UserStartupAction;
import com.eis.conv.mapping.srcHandler.source.startup.param.usr.UserAllActions;
import com.eis.conv.mapping.srcHandler.source.startup.param.usr.UserLoadSource;

import java.io.IOException;

public final class UserActionRunner {

    public static void runActions(UserStartupParameters userParameters, AppStartupParameters appParameters) throws IOException {
        UserStartupActions actions = userParameters.getActions() != null ? userParameters.getActions() : new UserStartupActions();

        for (UserStartupAction action : actions.getAction()) {
            System.out.println("Startup action: " + action.getActionName());

            //Actions selector
            if (action.getActionName().equalsIgnoreCase(UserAllActions.DOWNLOAD_REPO.getAction())) {

//
//                UserDownloadRepo userDownloadRepo = action.getDownloadRepo();
//                if (userDownloadRepo != null) {
//                    AppCommandRunner.runCommandDownloadRepo(userParameters.getUser(), userParameters.getPassword(), appParameters, userDownloadRepo.getProject(), userDownloadRepo.getProduct(), userDownloadRepo.getVersion());
//                }


            } else if (action.getActionName().equalsIgnoreCase(UserAllActions.LOAD_SOURCE.getAction())) {
                //Read folders and sources
                UserLoadSource userLoadSource = action.getLoadSource() != null ? action.getLoadSource() : new UserLoadSource();
                ReadSourceAction.readRepo(userLoadSource.getProject(), userLoadSource.getProduct(), userLoadSource.getVersion(), appParameters.getRepoRootDir());
            }

        }
    }


}
