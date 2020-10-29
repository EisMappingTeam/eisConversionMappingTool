package com.eis.conv.mapping.srcHandler.source.files.xml;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlAttribute;
import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers.tags.beans.XmlNodesBeans;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceXmlBeanFileTest {

    private final String NODE_ROOT_BEAN = XmlNodesBeans.BEANS.getName();
    private final String NODE_BEAN = XmlNodesBeans.BEAN.getName();
    private final String NODE_WRONG = "WRONG";
    private final String ATTR_NAME_PREFIX = "id_";


    @Test
    public void getBeanTest() {
        SourceXmlBeanFile sf = new SourceXmlBeanFile();
        sf.setRoot(getNodes());

        assertThat(sf.getRoot().getChildren().size()).isEqualTo(5);

        String attrName = ATTR_NAME_PREFIX + String.valueOf(2);
        String attrValue = String.valueOf(2);
        XmlNode r1 = sf.getBean(attrName, attrValue);
        assertThat(r1.getName()).isEqualTo(NODE_BEAN);
        assertThat(r1.getDate()).isEqualTo(String.valueOf(2));

        XmlAttribute attr = r1.getAttributeByName(attrName);
        assertThat(attr.getName()).isEqualTo(attrName);
        assertThat(attr.getValue()).isEqualTo(attrValue);

        //Not found check
        attrName = ATTR_NAME_PREFIX + String.valueOf(4);
        attrValue = String.valueOf(4);
        r1 = sf.getBean(attrName, attrValue);
        assertThat(r1.getName()).isEqualTo("");
    }


    private XmlNode getNodes() {
        //TODO - for refactoring
        XmlNode ch;
        XmlNode root = new XmlNode(NODE_ROOT_BEAN, "");

        for (int i = 0; i < 5; i++) {
            if (i < 3) {
                ch = new XmlNode(NODE_BEAN, String.valueOf(i));
            } else {
                ch = new XmlNode(NODE_WRONG, "");
            }

            for (int j = 0; j < i + 1; j++) {
                XmlAttribute attr = new XmlAttribute(ATTR_NAME_PREFIX + String.valueOf(j), String.valueOf(j));
                ch.getAttributes().add(attr);
            }
            root.getChildren().add(ch);
        }
        return root;
    }
}