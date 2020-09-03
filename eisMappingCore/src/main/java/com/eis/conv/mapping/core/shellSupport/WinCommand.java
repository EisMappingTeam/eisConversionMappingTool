package com.eis.conv.mapping.core.shellSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class WinCommand {

    public static void run(String command) throws IOException {
        Process process = Runtime.getRuntime()
                .exec(command, null);     //.exec("sh -c ls", null, new File("Pathname")); for non-Windows users
        printResults(process);
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
