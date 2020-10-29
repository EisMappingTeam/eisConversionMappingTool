package com.eis.conv.mapping.srcHandler.source.parsers.xmlParsers;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlBeanFile;

public final class XmlFileParserBeanValidation {

    public static SourceXmlBeanFile parse(XmlNode root) {
        SourceXmlBeanFile result = new SourceXmlBeanFile();
        XmlNode beans = root.getChildren().get(0); //XML structure must be checked before call 'parse'
        result.setRoot(beans);

        return result;
    }

}
