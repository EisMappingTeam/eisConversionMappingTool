package com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles.xml;


import com.eis.conv.mapping.srcHandler.source.entities.xmlObjects.XmlConstraintValidation;

import java.util.ArrayList;
import java.util.List;

public class SourceXmlConstraintFile extends SourceXmlFile {
    List<XmlConstraintValidation> xmlConstraintValidations = new ArrayList<>();

    public List<XmlConstraintValidation> getXmlConstraintValidations() {
        return xmlConstraintValidations;
    }

    public void setXmlConstraintValidations(List<XmlConstraintValidation> xmlConstraintValidations) {
        this.xmlConstraintValidations = xmlConstraintValidations;
    }

    public XmlConstraintValidation getValidationByCode(String context, String appliedTo, String code) {
        return xmlConstraintValidations.stream()
                .filter(item -> item.getContext().equalsIgnoreCase(context) &
                        item.getApplyedTo().equalsIgnoreCase(appliedTo) &
                        item.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(new XmlConstraintValidation());
    }

    public boolean isConstraintValidation() {
        return xmlConstraintValidations.size() > 0;

    }
}
