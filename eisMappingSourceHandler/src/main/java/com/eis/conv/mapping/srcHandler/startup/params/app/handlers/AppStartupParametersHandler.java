package com.eis.conv.mapping.srcHandler.startup.params.app.handlers;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppAllCommands;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppRepoCommand;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;

import java.util.ArrayList;
import java.util.List;

public final class AppStartupParametersHandler {

    private static final String LIST_SPLITTER = ";";

    public static AppRepoCommand getAppRepoCommand(AppStartupParameters appParameters, AppAllCommands cmd, String project, String product) {
        if (appParameters.getRepoCommand() != null) {

            for (AppRepoCommand item : appParameters.getRepoCommand()) {
                if (item.getCommandName().equalsIgnoreCase(cmd.getCommang())) {
                    if (project != null && product != null && item.getProject() != null && item.getProduct() != null) {
                        if (project.equalsIgnoreCase(item.getProject()) && product.equalsIgnoreCase(item.getProduct())) {
                            return item;
                        }
                    }
                }

            }
        }
        return null;
    }

    public static AppRepoCommand getAppRepoCommand(AppStartupParameters appParameters, AppAllCommands cmd) {
        if (appParameters.getRepoCommand() != null) {

            for (AppRepoCommand item : appParameters.getRepoCommand()) {
                if (item.getCommandName().equalsIgnoreCase(cmd.getCommang())) {
                    return item;
                }
            }

        }
        return null;
    }

    public static List<String> getIgnoredAnnotations(AppStartupParameters appParameters, AppAllCommands cmd) {
        if (appParameters.getRepoCommand() == null) {
            return new ArrayList<>();
        }
        AppRepoCommand appRepoCommand = getAppRepoCommand(appParameters, cmd);
        if (appRepoCommand == null) {
            return new ArrayList<>();
        }
        if (appRepoCommand.getIgnoreAnnotationList() == null) {
            return new ArrayList<>();
        }

        return StringHelper.splitToListAndTrim(appRepoCommand.getIgnoreAnnotationList(), LIST_SPLITTER);
    }

}
