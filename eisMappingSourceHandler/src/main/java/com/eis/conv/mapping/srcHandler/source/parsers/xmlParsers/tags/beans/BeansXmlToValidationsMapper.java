package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.files.xml.xmlObjects.beanValidations.XmlBeanValidation;

import java.util.ArrayList;
import java.util.List;

public class BeansXmlToValidationsMapper {
    private List<String> usedBeanIds = new ArrayList<>();
    private List<XmlBeanValidation> validations = new ArrayList<>();
    private XmlNode rootBean;

    public BeansXmlToValidationsMapper(XmlNode rootBean) {
        this.rootBean = rootBean;
        this.usedBeanIds = new ArrayList<>();
        this.validations = new ArrayList<>();
        parseAndMap();
    }

    public List<XmlBeanValidation> getValidations() {
        return validations;
    }

    private void parseAndMap() {
        if (!rootBean.getName().equalsIgnoreCase(XmlNodesBeans.BEANS.getName())) {
            return;
        }

        List<XmlNode> allBeans = rootBean.getChildren();
        for (XmlNode item : allBeans) {
            String id = item.getAttributeByName(XmlNodeBeanAttributes.ID.getName()).getValue();
            if (!isProcessed(id)) {
                processNode(allBeans, id, "", "");
            }
        }
    }

    private boolean isProcessed(String id) {
        return usedBeanIds.stream().filter(x -> x.equalsIgnoreCase(id)).findFirst().isPresent();
    }

    private void processNode(List<XmlNode> allBeans, String currentId, String componentName, String fieldName) {
        usedBeanIds.add(currentId);
    }

}
