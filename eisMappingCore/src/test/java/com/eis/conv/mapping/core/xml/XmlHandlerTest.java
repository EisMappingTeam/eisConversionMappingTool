package com.eis.conv.mapping.core.xml;


import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlHandlerTest {


    @Test
    public void xmlTransformToListTest() {
        XmlNode root = getTestXmlRoot();
        List<XmlNode> lst = XmlHandler.xmlTransformToList(root);
        assertThat(lst.size()).isEqualTo(6);
    }

    private static XmlNode getTestXmlRoot() {
        XmlNode root = new XmlNode("root", "root_Value_Lvl_0");

        XmlNode ch_1 = new XmlNode("ch_1", "ch_1_Value_Lvl_1");
        XmlNode ch_2 = new XmlNode("ch_2", "ch_2_Value_Lvl_1");
        root.addChild(ch_1);
        root.addChild(ch_2);

        XmlNode ch1_2_1 = new XmlNode("ch1_2_1", "ch1_2_1_Value_Lvl_2");
        XmlNode ch1_2_2 = new XmlNode("ch1_2_2", "ch1_2_2_Value_Lvl_2");
        ch_2.addChild(ch1_2_1);
        ch_2.addChild(ch1_2_2);

        XmlNode ch1_2_2_1 = new XmlNode("ch1_2_2_1", "ch1_2_2_1_Value_Lvl_3");
        ch1_2_2.addChild(ch1_2_2_1);

        return root;
    }
}