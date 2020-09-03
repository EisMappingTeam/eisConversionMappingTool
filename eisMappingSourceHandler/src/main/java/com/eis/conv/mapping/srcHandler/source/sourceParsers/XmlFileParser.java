package com.eis.conv.mapping.srcHandler.source.sourceParsers;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.core.xml.XmlDOMParser;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlAttribute;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.entities.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.types.SourceFileContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.entities.xmlObjects.XmlConstraintValidation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlFileParser {
    private final String NODE_CONSTRAINT_VALIDATION_CONSTRAINTMAPPINGS = "constraint-mappings";
    private final String NODE_CONSTRAINT_VALIDATION_BEAN = "bean";
    private final String NODE_CONSTRAINT_VALIDATION_FIELD = "field";
    private final String NODE_CONSTRAINT_VALIDATION_GETTER = "getter";
    private final String NODE_CONSTRAINT_VALIDATION_CONSTRAINT = "constraint";
    private final String NODE_CONSTRAINT_VALIDATION_MESSAGE = "message";
    private final String NODE_CONSTRAINT_VALIDATION_GROUPS = "groups";
    private final String NODE_CONSTRAINT_VALIDATION_VALUE = "value";
    private final String NODE_CONSTRAINT_VALIDATION_ELEMENT = "element";

    private final String ATTR_CONSTRAINT_VALIDATION_CLASS = "class";
    private final String ATTR_CONSTRAINT_VALIDATION_NAME = "name";
    private final String ATTR_CONSTRAINT_VALIDATION_ANNOTATION = "annotation";

    private final String ATTRVALUE_CONSTRAINT_VALIDATION_MAX = "max";
    private final String ATTRVALUE_CONSTRAINT_VALIDATION_REGEXP = "regexp";


    public SourceXmlFile parse(String fileContent) throws IOException, SAXException, ParserConfigurationException {
        SourceXmlFile result = new SourceXmlFile();

        XmlNode root = parseDOM(fileContent);

        if (getContentType(root) == SourceFileContentTypeXML.CONSTRAINT_VALIDATION_RULES) {
            List<XmlConstraintValidation> xmlConstraintValidations = getConstraintsValidations(root);

            result.setContentType(SourceFileContentTypeXML.CONSTRAINT_VALIDATION_RULES);
            cleanTextValidations(xmlConstraintValidations);

            result.setXmlConstraintValidations(xmlConstraintValidations);
        }
        return result;
    }


    private XmlNode parseDOM(String fileContent) throws ParserConfigurationException, SAXException, IOException {
        XmlDOMParser mp = new XmlDOMParser();
        XmlNode result = mp.parseXml(fileContent);
        return result;
    }


    private SourceFileContentTypeXML getContentType(XmlNode root) {
        XmlNode cm = root.getChildByName(NODE_CONSTRAINT_VALIDATION_CONSTRAINTMAPPINGS);
        if (!cm.getName().equalsIgnoreCase(NODE_CONSTRAINT_VALIDATION_CONSTRAINTMAPPINGS)) {
            return SourceFileContentTypeXML.UNKNOWN;
        }

        XmlNode bn = cm.getChildByName(NODE_CONSTRAINT_VALIDATION_BEAN);
        if (!bn.getName().equalsIgnoreCase(NODE_CONSTRAINT_VALIDATION_BEAN)) {
            return SourceFileContentTypeXML.UNKNOWN;
        }
        return SourceFileContentTypeXML.CONSTRAINT_VALIDATION_RULES;
    }


    private List<XmlConstraintValidation> getConstraintsValidations(XmlNode root) {
        List<XmlConstraintValidation> result = new ArrayList<>();
        XmlNode cm = root.getChildByName(NODE_CONSTRAINT_VALIDATION_CONSTRAINTMAPPINGS);

        for (XmlNode beanNode : cm.getChildren()) {
            if (beanNode.getName().equalsIgnoreCase(NODE_CONSTRAINT_VALIDATION_BEAN)) {
                List<XmlConstraintValidation> beanResult = constraintsValidation_ParseBean(beanNode);
                result.addAll(beanResult);

            } else {
                //TODO: Unknown tag instead of bean
            }

        }
        return result;
    }

    private List<XmlConstraintValidation> constraintsValidation_ParseBean(XmlNode beanNode) {
        List<XmlConstraintValidation> beanResult = new ArrayList<>();

        for (XmlNode field : beanNode.getChildren()) {
            if (field.getName().equalsIgnoreCase(NODE_CONSTRAINT_VALIDATION_FIELD) || field.getName().equalsIgnoreCase(NODE_CONSTRAINT_VALIDATION_GETTER)) {
                List<XmlConstraintValidation> fieldResult = constraintsValidation_ParseField(field);
                fieldResult.stream().forEach(item -> {
                    item.setContext(beanNode.getAttributeByName(ATTR_CONSTRAINT_VALIDATION_CLASS).getValue());
                    item.setDataObject(beanNode.getAttributeByName(ATTR_CONSTRAINT_VALIDATION_CLASS).getValue());
                });
                beanResult.addAll(fieldResult);

            } else {
                //TODO: Unknown tag instead of field | getter
            }
        }
        return beanResult;
    }


    private List<XmlConstraintValidation> constraintsValidation_ParseField(XmlNode fieldNode) {
        List<XmlConstraintValidation> fieldResult = new ArrayList<>();
        for (XmlNode constraint : fieldNode.getChildren()) {
            if (constraint.getName().equalsIgnoreCase(NODE_CONSTRAINT_VALIDATION_CONSTRAINT)) {
                XmlConstraintValidation cv = new XmlConstraintValidation();

                cv.setApplyedTo(fieldNode.getAttributeByName(ATTR_CONSTRAINT_VALIDATION_NAME).getValue());

                //message
                XmlNode message = constraint.getChildByName(NODE_CONSTRAINT_VALIDATION_MESSAGE);
                cv.setCode(constraint.getAttributeByName(ATTR_CONSTRAINT_VALIDATION_ANNOTATION).getValue()); //need to split '.'
                cv.setErrorMessage(message.getDate());

                //element
                XmlNode element = constraint.getChildByName(NODE_CONSTRAINT_VALIDATION_ELEMENT);
                XmlAttribute attrName = element.getAttributeByName(ATTR_CONSTRAINT_VALIDATION_NAME);
                if (attrName.getValue().equalsIgnoreCase(ATTRVALUE_CONSTRAINT_VALIDATION_MAX)) {
                    cv.setMaximumLength(element.getDate());
                } else if (attrName.getValue().equalsIgnoreCase(ATTRVALUE_CONSTRAINT_VALIDATION_REGEXP)) {
                    cv.setRegExpExpression(element.getDate());
                }
                fieldResult.add(cv);
            }
        }
        return fieldResult;
    }

    private void cleanTextValidations(List<XmlConstraintValidation> xmlConstraintValidations) {
        xmlConstraintValidations.stream().forEach(item -> {
            item.setContext(StringHelper.getLastDotSplitted(item.getContext()));
            item.setErrorMessage(item.getErrorMessage().replace("{", ""));
            item.setErrorMessage(item.getErrorMessage().replace("}", ""));
            item.setCode(StringHelper.getLastDotSplitted(item.getCode()));
        });
    }

}