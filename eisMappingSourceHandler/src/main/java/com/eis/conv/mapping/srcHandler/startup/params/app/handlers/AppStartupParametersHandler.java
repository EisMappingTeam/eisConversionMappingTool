package com.eis.conv.mapping.srcHandler.startup.params.app.handlers;

import com.eis.conv.mapping.srcHandler.startup.params.app.AppAllCommands;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppRepoCommand;
import com.eis.conv.mapping.srcHandler.startup.params.app.AppStartupParameters;

public final class AppStartupParametersHandler {

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

}
