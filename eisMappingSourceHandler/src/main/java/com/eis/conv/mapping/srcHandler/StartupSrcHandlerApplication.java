package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoProduct;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoProject;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoRoot;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoVersion;
import com.eis.conv.mapping.srcHandler.source.repo.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.JFileHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.jObjects.JFileAnnotations;
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
        if (args.length < 1) {
            System.out.println("No parameters found");

            String fileName = "C:\\111\\222\\fl.txt";
            JFileAnnotations jFileAnnotations = JFileHandler.loadFromFile(fileName);
        }

        //
        RepoRoot rr = RepoHandler.loadRepoRoot("C:\\111");
        RepoProject rp = rr.getProject("222");
        RepoProduct rProd = rp.getProduct("2_CCC");
        RepoVersion rv = rProd.getVersion("S02");
        rv.loadFilesList();

        exit(0);
    }
}
