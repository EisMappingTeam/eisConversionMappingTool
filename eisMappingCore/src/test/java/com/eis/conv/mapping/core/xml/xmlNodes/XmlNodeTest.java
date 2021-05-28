package com.eis.conv.mapping.core.xml.xmlNodes;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;


public class XmlNodeTest {

    @Test
    public void getPathTest() {
        XmlNode root = new XmlNode("root", "rootValue");
        XmlNode ch1 = new XmlNode("ch1", "ch1Value", root);
        XmlNode ch2 = new XmlNode("ch2", "ch2Value", ch1);

        assertThat(root.getPath()).isEqualTo("root");
        assertThat(ch1.getPath()).isEqualTo("root/ch1");
        assertThat(ch2.getPath()).isEqualTo("root/ch1/ch2");
    }


}