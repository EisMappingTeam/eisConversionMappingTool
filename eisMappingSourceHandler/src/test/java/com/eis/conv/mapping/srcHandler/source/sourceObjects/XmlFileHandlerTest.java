package com.eis.conv.mapping.srcHandler.source.sourceObjects;


import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceXmlFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlFileHandlerTest {
    private final String DIR = "src/test/resources/source";
    private final String FILE_NAME = "TestCrm-validation-constraints.xml";

    @Test
    public void loadFromFileTest() throws IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(FILE_NAME);

        SourceXmlFile xmlFile = XmlFileHandler.loadFromFile(file.toString());
        assertThat("").isEqualTo("");
    }


}