package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoProduct;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoProject;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoRoot;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoVersion;
import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.JFileHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects.JFileAnnotations;
import com.eis.conv.mapping.srcHandler.source.startup.ParametersReader;
import com.eis.conv.mapping.srcHandler.source.startup.UserActionRunner;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.ParameterFilesHelper;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupParameters;
import com.fasterxml.jackson.core.type.TypeReference;
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
        ParameterFilesHelper parameterFilesHelper = new ParameterFilesHelper();
        String userParamFileName;

        if (args.length < 1) {
            System.out.println("No parameters found");
            userParamFileName = parameterFilesHelper.getUserSettingsFileName("");
            System.out.println("User settings load from resources: " + userParamFileName);

        } else {
            userParamFileName = parameterFilesHelper.getUserSettingsFileName(args[0]);
        }

        UserStartupParameters parameters;
        parameters = ParametersReader.readUserParameters(userParamFileName);

        UserActionRunner.runActions(parameters);

        //Load java
        String fileName = "C:\\111\\222\\fl.txt";
        JFileAnnotations jFileAnnotations = JFileHandler.loadFromFile(fileName);

        //Load REPO: Project-Product-Versions
        RepoRoot rr = RepoHandler.loadRepoRoot("C:\\111");
        RepoProject rp = rr.getProject("222");
        RepoProduct rProd = rp.getProduct("2_CCC");
        RepoVersion rv = rProd.getVersion("S02");
        rv.loadFilesList();


        //XML
        String xml = FileHelper.getFileAsSting("C:\\111\\ipb-import-validation-beans.xml");
        XmlDOMParser mp = new XmlDOMParser();
        mp.parseXml(xml);
        exit(0);
    }
}
