package com.eis.conv.mapping.core.xml;

import com.eis.conv.mapping.core.filesSupport.FileHelper;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlAttribute;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

public class XmlDOMParserTest {

    private final String DIR = "src/test/resources/xml/rules";
    private final String testFile = "Test_crm-validation-constraints.xml";

    @Test
    public void parseXmlTest() throws ParserConfigurationException, SAXException, IOException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(testFile);
        String xml = FileHelper.getFileAsSting(file.toString());

        XmlNode result = XmlDOMParser.parseXml(xml);

        assertThat(result.getChildren().size()).isEqualTo(1);
        XmlNode constraintMappings = result.getChildren().get(0);
        assertThat(constraintMappings.getChildren().size()).isEqualTo(5);

        //first bean
        XmlNode bean0 = constraintMappings.getChildren().get(0);
        assertThat(bean0.getName()).isEqualTo("bean");
        assertThat(bean0.getAttributes().size()).isEqualTo(2);
        assertThat(bean0.getChildren().size()).isEqualTo(1);
        XmlAttribute attBean00 = bean0.getAttributeByName("class");
        assertThat(attBean00.getValue()).isEqualTo("com.exigen.ipb.crm.domain.AddressEntity");

        //first bean -> field
        XmlNode field0 = bean0.getChildren().get(0);
        assertThat(field0.getName()).isEqualTo("field");
        assertThat(field0.getChildren().size()).isEqualTo(1);
        assertThat(field0.getAttributes().size()).isEqualTo(2);
        XmlAttribute attField00 = field0.getAttributeByName("ignore-annotations");
        XmlAttribute attField01 = field0.getAttributeByName("name");
        assertThat(attField00.getValue()).isEqualTo("true");
        assertThat(attField01.getValue()).isEqualTo("addressTypeCd");

        //first bean -> field -> constraint
        XmlNode constraint0 = field0.getChildren().get(0);
        assertThat(constraint0.getName()).isEqualTo("constraint");
        assertThat(constraint0.getChildren().size()).isEqualTo(2);
        assertThat(constraint0.getAttributes().size()).isEqualTo(1);
        XmlAttribute attContr0 = constraint0.getAttributeByName("annotation");
        assertThat(attContr0.getValue()).isEqualTo("com.exigen.ipb.crm.domain.validation.NotBlank");
        XmlNode msg0 = constraint0.getChildByName("message");
        XmlNode groups0 = constraint0.getChildByName("groups");
        assertThat(msg0.getValue()).isEqualTo("{crm00079}");
        assertThat(groups0.getValue()).isEqualTo("");
        assertThat(groups0.getChildren().size()).isEqualTo(2);

        //first bean -> field -> constraint -> groups
        XmlNode val01 = groups0.getChildren().get(0);
        XmlNode val02 = groups0.getChildren().get(1);
        //assert here
    }

}