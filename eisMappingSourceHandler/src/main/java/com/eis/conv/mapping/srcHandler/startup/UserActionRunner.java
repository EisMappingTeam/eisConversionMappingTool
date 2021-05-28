package com.eis.conv.mapping.srcHandler.startup;

import com.eis.conv.mapping.srcHandler.startup.acts.UserActionRunnerCompareXmlImportExport;
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
            //Download repo from git | Mercurial | etc
            if (action.getActionName().equalsIgnoreCase(UserAllActions.DOWNLOAD_REPO.getAction())) {
                System.out.println("Startup action: " + action.getActionName());
                UserActionRunnerDownloadRepo.run(appParameters, action, userParameters.getUser(), userParameters.getPassword());

                //Read folders and sources from java files | XML | properties | etc
            } else if (action.getActionName().equalsIgnoreCase(UserAllActions.LOAD_SOURCE.getAction())) {
                System.out.println("Startup action: " + action.getActionName());
                UserActionRunnerLoadSource.run(appParameters, action);

            } else if (action.getActionName().equalsIgnoreCase(UserAllActions.XML_COMPARE_IMPORT_VS_EXPORT.getAction())) {
                System.out.println("Startup action: " + action.getActionName());
                UserActionRunnerCompareXmlImportExport.run(appParameters, action);
            }

        }
    }

}
