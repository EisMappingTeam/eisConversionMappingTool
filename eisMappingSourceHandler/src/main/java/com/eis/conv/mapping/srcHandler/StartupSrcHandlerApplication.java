package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.*;
import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.JFileHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.startup.ParametersReader;
import com.eis.conv.mapping.srcHandler.source.startup.UserActionRunner;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.ParameterFilesHelper;
import com.eis.conv.mapping.srcHandler.source.startup.parameters.user.UserStartupParameters;
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

        UserStartupParameters parameters= ParametersReader.readUserParameters(userParamFileName);


        UserActionRunner.runActions(parameters);

        //Load java
        String fileName = "C:\\111\\hotai\\fl.txt";
        SourceJavaFile jFileAnnotations = JFileHandler.loadFromFile(fileName);
        jFileAnnotations.setProject("prj");
        jFileAnnotations.setProduct("Prod");

        //Load REPO: Project-Product-Versions
        RepoRoot rr = RepoHandler.loadRepoRoot("C:\\111");
        RepoProject rp = rr.getProject("hotai");
        RepoProduct rProd = rp.getProduct("AC");
        RepoVersion rv = rProd.getVersion("S02");
        RepoProductItem rpi =rv.getProductItem("BASE");
        RepoHandler.loadRepoProductItemFiles(rpi);


        //XML
        String xml = FileHelper.getFileAsSting("C:\\111\\hotai\\rules.xml");
        XmlDOMParser mp = new XmlDOMParser();
        mp.parseXml(xml);
        exit(0);

    }
}
