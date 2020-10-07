package com.eis.conv.mapping.srcHandler.source.sourceParsers.xmlParsers;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlAttribute;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.entities.files.types.ContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.entities.xmlObjects.XmlConstraintValidation;

import java.util.ArrayList;
import java.util.List;

public final class XmlFileParserConstraintValidation {

    private static final String NODE_CONSTRAINTMAPPINGS = "constraint-mappings";
    private static final String NODE_BEAN = "bean";
    private static final String NODE_CONSTRAINT_VALIDATION_FIELD = "field";

    private static final String NODE_CONSTRAINT_VALIDATION_GETTER = "getter";
    private static final String NODE_CONSTRAINT_VALIDATION_CONSTRAINT = "constraint";
    private static final String NODE_CONSTRAINT_VALIDATION_MESSAGE = "message";
    private static final String NODE_CONSTRAINT_VALIDATION_GROUPS = "groups";
    private static final String NODE_CONSTRAINT_VALIDATION_VALUE = "value";
    private static final String NODE_CONSTRAINT_VALIDATION_ELEMENT = "element";

    private static final String ATTR_CONSTRAINT_VALIDATION_CLASS = "class";
    private static final String ATTR_CONSTRAINT_VALIDATION_NAME = "name";
    private static final String ATTR_CONSTRAINT_VALIDATION_ANNOTATION = "annotation";

    private static final String ATTRVALUE_CONSTRAINT_VALIDATION_MAX = "max";
    private static final String ATTRVALUE_CONSTRAINT_VALIDATION_REGEXP = "regexp";


    public static SourceXmlConstraintFile parse(XmlNode root) {
        SourceXmlConstraintFile result = new SourceXmlConstraintFile();

        List<XmlConstraintValidation> xmlConstraintValidations = getConstraintsValidations(root);

        result.setContentType(ContentTypeXML.CONSTRAINT_VALIDATION_RULES);
        cleanTextValidations(xmlConstraintValidations);

        result.setXmlConstraintValidations(xmlConstraintValidations);
        return result;
    }


    private static List<XmlConstraintValidation> getConstraintsValidations(XmlNode root) {
        List<XmlConstraintValidation> result = new ArrayList<>();
        XmlNode cm = root.getChildByName(NODE_CONSTRAINTMAPPINGS);

        for (XmlNode beanNode : cm.getChildren()) {
            if (beanNode.getName().equalsIgnoreCase(NODE_BEAN)) {
                List<XmlConstraintValidation> beanResult = constraintsValidation_ParseBean(beanNode);
                result.addAll(beanResult);

            } else {
                //TODO: Unknown tag instead of bean
            }

        }
        return result;
    }

    private static List<XmlConstraintValidation> constraintsValidation_ParseBean(XmlNode beanNode) {
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


    private static List<XmlConstraintValidation> constraintsValidation_ParseField(XmlNode fieldNode) {
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

    private static void cleanTextValidations(List<XmlConstraintValidation> xmlConstraintValidations) {
        xmlConstraintValidations.stream().forEach(item -> {
            item.setContext(StringHelper.getLastDotSplitted(item.getContext()));
            item.setErrorMessage(item.getErrorMessage().replace("{", ""));
            item.setErrorMessage(item.getErrorMessage().replace("}", ""));
            item.setCode(StringHelper.getLastDotSplitted(item.getCode()));
        });
    }

}
