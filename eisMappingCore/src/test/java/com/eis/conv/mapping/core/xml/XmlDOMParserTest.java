package com.eis.conv.mapping.core.xml;

import com.eis.conv.mapping.core.files.FileHelper;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

public class XmlDOMParserTest {

    private final  String DIR = "src/test/resources/xml/rules";
    private final String testFile = "Test_crm-validation-constraints.xml";

    @Test
    public void parseXmlTest() throws ParserConfigurationException, SAXException, IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(testFile);
        String xml = FileHelper.getFileAsSting(file.toString());

        XmlNode result = XmlDOMParser.parseXml(xml);

//
//        assertThat(parameters.getUser()).isEqualTo("AAA");
//        assertThat(parameters.getPassword()).isEqualTo("VVV");
//        assertThat(parameters.getApplicationSettingsFile()).isEqualTo("");
//
//        assertThat(parameters.getActions()).isNotEqualTo(null);
//        UserStartupActions userStartupActions = parameters.getActions();
//
//        assertThat(userStartupActions.getAction().length).isEqualTo(2);
//        UserStartupAction action0 = userStartupActions.getAction()[0];
//        UserStartupAction action1 = userStartupActions.getAction()[1];
//
//        assertThat(action0.getActionName()).isEqualTo("LOAD_REPO");
//        assertThat(action1.getActionName()).isEqualTo("LOAD_SOURCE");
    }


}