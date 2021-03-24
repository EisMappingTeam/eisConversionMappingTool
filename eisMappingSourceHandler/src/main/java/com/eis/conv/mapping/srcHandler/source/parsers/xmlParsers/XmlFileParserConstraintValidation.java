package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers;

import com.eis.conv.mapping.core.stringsSupport.StringHelper;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlAttribute;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlConstraintFile;
import com.eis.conv.mapping.srcHandler.source.files.types.ContentTypeXML;
import com.eis.conv.mapping.srcHandler.source.files.xml.xmlObjects.XmlConstraintValidation;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.constraints.XmlAttributesConstraints;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.constraints.XmlNodesConstraints;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.constraints.XmlValuesConstraints;

import java.util.ArrayList;
import java.util.List;

public final class XmlFileParserConstraintValidation {


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
        XmlNode cm = root.getChildByName(XmlNodesConstraints.CONSTRAINT_MAPPINGS.getName());

        for (XmlNode beanNode : cm.getChildren()) {
            if (beanNode.getName().equalsIgnoreCase(XmlNodesConstraints.BEAN.getName())) {
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
            if (field.getName().equalsIgnoreCase(XmlNodesConstraints.FIELD.getName()) || field.getName().equalsIgnoreCase(XmlNodesConstraints.GETTER.getName())) {
                List<XmlConstraintValidation> fieldResult = constraintsValidation_ParseField(field);
                fieldResult.stream().forEach(item -> {
                    item.setContext(beanNode.getAttributeByName(XmlAttributesConstraints.CLASS.getName()).getValue());
                    item.setDataObject(beanNode.getAttributeByName(XmlAttributesConstraints.CLASS.getName()).getValue());
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
            if (constraint.getName().equalsIgnoreCase(XmlNodesConstraints.CONSTRAINT.getName())) {
                XmlConstraintValidation cv = new XmlConstraintValidation();

                cv.setApplyedTo(fieldNode.getAttributeByName(XmlAttributesConstraints.NAME.getName()).getValue());

                //message
                XmlNode message = constraint.getChildByName(XmlNodesConstraints.VALIDATION_MESSAGE.getName());
                cv.setCode(constraint.getAttributeByName(XmlAttributesConstraints.ANNOTATION.getName()).getValue()); //need to split '.'
                cv.setErrorMessage(message.getDate());

                //element
                XmlNode element = constraint.getChildByName(XmlNodesConstraints.VALIDATION_ELEMENT.getName());
                XmlAttribute attrName = element.getAttributeByName(XmlAttributesConstraints.NAME.getName());
                if (attrName.getValue().equalsIgnoreCase(XmlValuesConstraints.MAX.getName())) {
                    cv.setMaximumLength(element.getDate());
                } else if (attrName.getValue().equalsIgnoreCase(XmlValuesConstraints.REGEXP.getName())) {
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
