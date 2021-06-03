package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.files.handlers.XmlFileHandler;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlBeanFile;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlFile;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;


public class XmlFileParserBeanValidationTest {
    private final String DIR = "src/test/resources/source";
    private final String FILE_NAME = "TestIpb-import-policy-gb-validation-beans.xml";


    @Test
    public void parse() throws ParserConfigurationException, SAXException, IOException {
        XmlNode root = getRootNode(DIR, FILE_NAME);
        SourceXmlBeanFile result = XmlFileParserBeanValidation.parse(root);

        assertThat(1).isEqualTo(1);
    }

//    @Test
//    public void getBeanByAttribute() {
//    }



    private XmlNode getRootNode(String _dir, String _file) throws ParserConfigurationException, SAXException, IOException {
        Path workingDir = Path.of("", _dir);
        Path file = workingDir.resolve(_file);
        String fileContent = FileHelper.getFileAsSting(file.toString());

        XmlDOMParser mp = new XmlDOMParser();
        XmlNode result = mp.parseXml(StringHelper.encodingToUTF8(fileContent));
        return result;
    }
}