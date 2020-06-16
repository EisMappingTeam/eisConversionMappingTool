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
}

