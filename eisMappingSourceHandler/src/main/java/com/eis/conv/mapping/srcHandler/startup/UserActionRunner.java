package com.eis.conv.mapping.srcHandler.startup;

import com.eis.conv.mapping.srcHandler.startup.acts.UserActionRunnerDownloadRepo;
import com.eis.conv.mapping.srcHandler.startup.acts.UserActionRunnerLoadSource;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserAllActions;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserStartupAction;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserStartupActions;
import com.eis.conv.mapping.srcHandler.startup.params.usr.UserStartupParameters;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class UserActionRunner {

    public static void runActions(UserStartupParameters userParameters, AppStartupParameters appParameters) throws IOException, ParserConfigurationException, SAXException {
        UserStartupActions actions = userParameters.getActions() != null ? userParameters.getActions() : new UserStartupActions();

        for (UserStartupAction action : actions.getAction()) {
            if (action.getActionName().equalsIgnoreCase(UserAllActions.DOWNLOAD_REPO.getAction())) { //Download repo
                System.out.println("Startup action: " + action.getActionName());
                UserActionRunnerDownloadRepo.run(appParameters, action, userParameters.getUser(), userParameters.getPassword());


            } else if (action.getActionName().equalsIgnoreCase(UserAllActions.LOAD_SOURCE.getAction())) { //Read folders and sources
                System.out.println("Startup action: " + action.getActionName());
                UserActionRunnerLoadSource.run(appParameters, action);
            }

        }
    }

}
