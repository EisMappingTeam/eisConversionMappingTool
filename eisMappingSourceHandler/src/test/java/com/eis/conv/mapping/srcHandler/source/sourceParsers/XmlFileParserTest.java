package com.eis.conv.mapping.srcHandler.source.sourceParsers;

import com.eis.conv.mapping.srcHandler.source.sourceObjects.XmlFileHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.xmlObjects.XmlConstraintValidation;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlFileParserTest {

    private final String DIR = "src/test/resources/source";
    private final String FILE_NAME = "TestCrm-validation-constraints.xml";

    @Test
    public void parse() throws IOException, ParserConfigurationException, SAXException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(FILE_NAME);

        SourceXmlFile xmlFile = XmlFileHandler.loadFromFile(file.toString());

        assertThat(xmlFile.getXmlConstraintValidations().size()).isEqualTo(28);

        XmlConstraintValidation countryCdNotBlank = xmlFile.getValidationByCode("Address", "countryCd", "NotBlank");
        assertThat(countryCdNotBlank.getErrorMessage()).isEqualTo("crm00077");
        assertThat(countryCdNotBlank.getDataObject()).isEqualTo("com.exigen.ipb.base.datatypes.Address");

        XmlConstraintValidation countryCdSize = xmlFile.getValidationByCode("Address", "countryCd", "Size");
        assertThat(countryCdSize.getErrorMessage()).isEqualTo("crm00113");
        assertThat(countryCdSize.getMaximumLength()).isEqualTo("3");
        assertThat(countryCdSize.getDataObject()).isEqualTo("com.exigen.ipb.base.datatypes.Address");

        XmlConstraintValidation phoneNumberPattern = xmlFile.getValidationByCode("Phone", "phoneNumber", "Pattern");
        assertThat(phoneNumberPattern.getErrorMessage()).isEqualTo("crm00114");
        assertThat(phoneNumberPattern.getRegExpExpression()).isEqualTo("\\d*|\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}");
        assertThat(phoneNumberPattern.getDataObject()).isEqualTo("com.exigen.ipb.base.datatypes.Phone");
    }
}