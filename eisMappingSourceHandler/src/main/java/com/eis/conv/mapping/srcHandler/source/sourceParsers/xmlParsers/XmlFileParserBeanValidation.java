package com.eis.conv.mapping.srcHandler.source.sourceParsers.xmlParsers;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.xml.SourceXmlBeanFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.xml.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.types.ContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.entities.xmlObjects.XmlConstraintValidation;

import java.util.List;

public final class XmlFileParserBeanValidation {

    public static SourceXmlBeanFile parse(XmlNode root) {
        SourceXmlBeanFile result = new SourceXmlBeanFile();

        //List<XmlConstraintValidation> xmlConstraintValidations = getConstraintsValidations(root);

        //result.setContentType(ContentTypeXML.CONSTRAINT_VALIDATION_RULES);
        //cleanTextValidations(xmlConstraintValidations);

        //result.setXmlConstraintValidations(xmlConstraintValidations);
        return result;
    }


}
