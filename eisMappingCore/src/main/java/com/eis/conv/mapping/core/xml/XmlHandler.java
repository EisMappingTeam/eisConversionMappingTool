package com.eis.conv.mapping.core.xml;

import com.eis.conv.mapping.core.xml.xmlNodes.XmlNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlHandler {

    public static <T> T xmlToObject(String xmlData, TypeReference<T> tRef) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(xmlData, tRef);
    }

    public static List<XmlNode> xmlTransformToList(XmlNode root) {
        return traverseToList(root);
    }


    private static List<XmlNode> traverseToList(XmlNode root) {
        List<XmlNode> result = new ArrayList<>();
        traverseNodeToList(root, result);
        return result;

    }

    private static void traverseNodeToList(XmlNode node, List<XmlNode> lst) {
        lst.add(node);
        node.getChildren().stream().forEach(item -> traverseNodeToList(item, lst));

    }

}
