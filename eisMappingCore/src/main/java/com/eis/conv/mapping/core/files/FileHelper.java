package com.eis.conv.mapping.core.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileHelper {

    public static List<String> getDirs(String rootPath) {
        List<String> result = new ArrayList<>();
        File f = new File(rootPath);
        for (String itm : f.list()) {
            if (new File(extendPath(rootPath, itm)).isDirectory()) {
                result.add(itm);
            }
        }
        return result;
    }

    public static String getFileExtension(String filename) {   //Guava
        return com.google.common.io.Files.getFileExtension(filename);
    }

    public static List<String> getFileNamesAll(String path) throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(path));
        return walk.filter(Files::isRegularFile)
                .map(Path::toString).collect(Collectors.toList());
    }

    public static List<String> getFileAllLines(String filePath) throws IOException {
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            result.add(scanner.nextLine());
        }
        return result;
    }

    public static String getFileAsSting(String fileName) throws IOException {
        Path pth = Path.of(fileName);
        return Files.readString(pth);
    }

    public static String extendPath(String rootPath, String appendValue) {
        if (appendValue.length() < 1) {
            return rootPath;
        } else {
            return rootPath + "\\" + appendValue;
        }
    }
}

