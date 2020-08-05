package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.*;
import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.JFileHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.ParametersReader;
import com.eis.conv.mapping.srcHandler.source.startup.UserActionRunner;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.ParametersFileNameHelper;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.application.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupParameters;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;

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
        UserStartupParameters userParameters = getUserParameters(args);
        AppStartupParameters appParameters = getAppParameters(userParameters.getApplicationSettingsFile());
        //Run
        UserActionRunner.runActions(userParameters, appParameters);

        //*************Samples****************
        //Load java
        String fileName = "C:\\111\\hotai\\fl.txt";
        SourceJavaFile jFileAnnotations = JFileHandler.loadFromFile(fileName);
        jFileAnnotations.setProject("prj");
        jFileAnnotations.setProduct("Prod");

        //Load REPO: Project-Product-Versions
        RepoFolder rrff = RepoHandler.loadRepoFolders("C:\\111","") ;
        rrff.loadFiles();

        //XML
        String xml = FileHelper.getFileAsSting("C:\\111\\hotai\\rules.xml");
        XmlDOMParser mp = new XmlDOMParser();
        mp.parseXml(xml);
        exit(0);

    }


    private UserStartupParameters getUserParameters(String... args) throws IOException {
        ParametersFileNameHelper parameterFilesHelper = new ParametersFileNameHelper();
        String userParamFileName;
        if (args.length < 1) {
            userParamFileName = parameterFilesHelper.getUserSettingsFileName("");
//            System.out.println("User settings load from resources: " + userParamFileName);
        } else {
            userParamFileName = parameterFilesHelper.getUserSettingsFileName(args[0]);
//            System.out.println("User settings load from " + userParamFileName);
        }
        return ParametersReader.readUserParameters(userParamFileName);
    }

    private AppStartupParameters getAppParameters(String filePath) throws IOException {
        ParametersFileNameHelper parameterFilesHelper = new ParametersFileNameHelper();
        String appSettingsFileName;
        if (filePath.length() < 1) {
            appSettingsFileName = parameterFilesHelper.getAppSettingsFileName("");
//            System.out.println("Application settings load from resources: " + appSettingsFileName);
        } else {
            appSettingsFileName = parameterFilesHelper.getAppSettingsFileName(filePath);
//            System.out.println("User settings load from " + appSettingsFileName);
        }
        return ParametersReader.readAppParameters(appSettingsFileName);
    }

}
