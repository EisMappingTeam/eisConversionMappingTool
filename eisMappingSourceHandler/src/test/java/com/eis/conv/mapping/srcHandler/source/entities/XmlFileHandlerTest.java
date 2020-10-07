package com.eis.conv.mapping.srcHandler.source.entities;


import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.SourceXmlConstraintFile;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlFileHandlerTest {
    private final String DIR = "src/test/resources/source";
    private final String FILE_NAME = "TestCrm-validation-constraints.xml";

    @Test
    public void loadFromFileTest() throws IOException, ParserConfigurationException, SAXException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(FILE_NAME);

        SourceXmlConstraintFile xmlFile = XmlFileHandler.loadFromFile(file.toString());
        assertThat("").isEqualTo("");
    }


}