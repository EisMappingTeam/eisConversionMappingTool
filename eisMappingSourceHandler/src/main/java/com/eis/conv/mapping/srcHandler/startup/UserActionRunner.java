package com.eis.conv.mapping.srcHandler.startup;

import com.eis.conv.mapping.srcHandler.processing.readSource.SourceFilesReader;
import com.eis.conv.mapping.srcHandler.startup.actions.ReadSourceAction;
import com.eis.conv.mapping.srcHandler.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupActions;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupAction;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserAllActions;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserLoadSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class UserActionRunner {

    public static void runActions(UserStartupParameters userParameters, AppStartupParameters appParameters) throws IOException, ParserConfigurationException, SAXException {
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
                SourceFilesReader sourceFilesReader = ReadSourceAction.readRepo(userLoadSource.getProject(), userLoadSource.getProduct(), userLoadSource.getVersion(), appParameters.getRepoRootDir());
                System.out.println("Java files: " + sourceFilesReader.getJavaFiles().size());
                System.out.println("XML files: " + sourceFilesReader.getXmlFiles().size());
                System.out.println("Properties files: " + sourceFilesReader.getPropertyFiles().size());
                System.out.println("Unknown files: " + sourceFilesReader.getUnknownFiles().size());
                System.out.println("Error files: " + sourceFilesReader.getErrorFiles().size());
            }

        }
    }


}
