package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.srcHandler.source.startup.UserActionRunner;
import com.eis.conv.mapping.srcHandler.source.startup.param.ParametersFileHelper;
import com.eis.conv.mapping.srcHandler.source.startup.param.app.AppStartupParameters;
import com.eis.conv.mapping.srcHandler.source.startup.param.usr.UserStartupParameters;
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


        //*************Samples****************
        //Load java
//        String fileName = "C:\\111\\hotai\\fl.txt";
//        SourceJavaFile jFileAnnotations = JFileHandler.loadFromFile(fileName);
//        jFileAnnotations.setProject("prj");
//        jFileAnnotations.setProduct("Prod");

        //Load REPO: Project-Product-Versions
//        List<RepoDir> src = RepoHandler.loadRepo( "hotai","AC","S02","C:\\111");

//        //00XML
//        String xml = FileHelper.getFileAsSting("C:\\111\\repoTest\\hotai\\rules.xml");
//        XmlDOMParser mp = new XmlDOMParser();
//        XmlNode rn = mp.parseXml(xml);


        exit(0);

    }


}
