package com.eis.conv.mapping.srcHandler;

import com.eis.conv.mapping.core.files.FileHelper;
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
        if (args.length <1) {
            System.out.println("No parameters found");

            FileHelper.getFileNamesAll("C:\\111").forEach(System.out::println) ;
            FileHelper.getFileAllLines("C:\\111\\222\\fl.txt").forEach(System.out::println) ;
            return;
        }
        exit(0);
    }
}