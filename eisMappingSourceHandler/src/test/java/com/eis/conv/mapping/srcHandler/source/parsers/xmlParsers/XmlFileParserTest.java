package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers;

import com.eis.conv.mapping.srcHandler.source.files.handlers.XmlFileHandler;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.files.types.ContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.files.xml.xmlObjects.constraintValidations.XmlConstraintValidation;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlFileParserTest {

    private final String DIR = "src/test/resources/source";
    private final String FILE_NAME = "TestCrm-validation-constraints.xml";

    private final String CONTENT_DATA_OBJ_ADDR = "com.exigen.ipb.base.datatypes.Address";
    private final String CONTENT_DATA_OBJ_PHONE = "com.exigen.ipb.base.datatypes.Phone";
    private final String CONTENT_REGEXP = "\\d*|\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}";
    private final String CONTENT_ERRMSG_077 = "crm00077";
    private final String CONTENT_ERRMSG_113 = "crm00113";
    private final String CONTENT_ERRMSG_114 = "crm00114";

    @Test
    public void parseTest() throws IOException, ParserConfigurationException, SAXException {
        Path workingDir = Path.of("", DIR);
        Path file = workingDir.resolve(FILE_NAME);

        SourceXmlFile xmlFile = XmlFileHandler.loadFromFile(file.toString());

        assertThat(xmlFile.getContentType()).isEqualTo(ContentTypeXML.CONSTRAINT_VALIDATION_RULES);
        SourceXmlConstraintFile constraintFile = (SourceXmlConstraintFile) xmlFile;

        assertThat(constraintFile.getXmlConstraintValidations().size()).isEqualTo(28);
        XmlConstraintValidation countryCdNotBlank = constraintFile.getValidationByCode("Address", "countryCd", "NotBlank");
        assertThat(countryCdNotBlank.getErrorMessage()).isEqualTo(CONTENT_ERRMSG_077);
        assertThat(countryCdNotBlank.getDataObject()).isEqualTo(CONTENT_DATA_OBJ_ADDR);

        XmlConstraintValidation countryCdSize = constraintFile.getValidationByCode("Address", "countryCd", "Size");
        assertThat(countryCdSize.getErrorMessage()).isEqualTo(CONTENT_ERRMSG_113);
        assertThat(countryCdSize.getMaximumLength()).isEqualTo("3");
        assertThat(countryCdSize.getDataObject()).isEqualTo(CONTENT_DATA_OBJ_ADDR);

        XmlConstraintValidation phoneNumberPattern = constraintFile.getValidationByCode("Phone", "phoneNumber", "Pattern");
        assertThat(phoneNumberPattern.getErrorMessage()).isEqualTo(CONTENT_ERRMSG_114);
        assertThat(phoneNumberPattern.getRegExpExpression()).isEqualTo(CONTENT_REGEXP);
        assertThat(phoneNumberPattern.getDataObject()).isEqualTo(CONTENT_DATA_OBJ_PHONE);
    }
}