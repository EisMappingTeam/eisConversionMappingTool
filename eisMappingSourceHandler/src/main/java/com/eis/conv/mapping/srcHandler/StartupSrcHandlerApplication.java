package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.srcHandler.source.repo.RepoProject;
import com.eis.conv.mapping.srcHandler.source.repo.RepoRoot;
import com.eis.conv.mapping.srcHandler.source.repo.RepoVersion;
import com.eis.conv.mapping.srcHandler.source.repo.repoHandlers.RepoHandler;
import com.eis.conv.mapping.srcHandler.source.sourceParsers.jParser.JavaFileParser;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

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

            //FileHelper.getFileNamesAll("C:\\111").forEach(System.out::println) ;
            //FileHelper.getFileAllLines("C:\\111\\222\\fl.txt").forEach(System.out::println) ;

            File f = new File("C:\\111\\222\\fl.txt");
            JavaFileParser jfp = new JavaFileParser();
            jfp.parse(f);
            //return;
        }

        //
        RepoRoot rr = RepoHandler.getRepoRoot("C:\\111");
        RepoProject rp = rr.getProject("222");
        RepoVersion rv = rp.getVersion("2_CCC");
        rv.loadFilesList();
        exit(0);
    }
}
