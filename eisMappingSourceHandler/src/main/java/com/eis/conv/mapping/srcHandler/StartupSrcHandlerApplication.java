package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.srcHandler.startup.UserActionRunner;
import com.eis.conv.mapping.srcHandler.startup.param.ParametersFileHelper;
import com.eis.conv.mapping.srcHandler.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.startup.param.usr.UserStartupParameters;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class StartupSrcHandlerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StartupSrcHandlerApplication.class);
        app.setBannerMode(Banner.Mode.OFF);

        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Parameters
        UserStartupParameters userParameters = ParametersFileHelper.getUserParametersFromArgs(args);
        AppStartupParameters appParameters = ParametersFileHelper.getAppParameters(userParameters.getApplicationSettingsFile());
        //Run
        UserActionRunner.runActions(userParameters, appParameters);

        exit(0);

    }


}
