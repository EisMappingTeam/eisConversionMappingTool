package com.eis.conv.mapping.srcHandler.startup;

import com.eis.conv.mapping.srcHandler.startup.actions.UserActionRunnerDownloadRepo;
import com.eis.conv.mapping.srcHandler.startup.actions.UserActionRunnerLoadSource;
import com.eis.conv.mapping.srcHandler.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserAllActions;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupAction;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupActions;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupParameters;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class UserActionRunner {

    public static void runActions(UserStartupParameters userParameters, AppStartupParameters appParameters) throws IOException, ParserConfigurationException, SAXException {
        UserStartupActions actions = userParameters.getActions() != null ? userParameters.getActions() : new UserStartupActions();

        for (UserStartupAction action : actions.getAction()) {

            if (action.getActionName().equalsIgnoreCase(UserAllActions.DOWNLOAD_REPO.getAction())) { //Download repo
                UserActionRunnerDownloadRepo.run(appParameters, action, userParameters.getUser(), userParameters.getPassword());


            } else if (action.getActionName().equalsIgnoreCase(UserAllActions.LOAD_SOURCE.getAction())) { //Read folders and sources
                UserActionRunnerLoadSource.run(appParameters, action);
            }

        }
    }


}
