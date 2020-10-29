package com.eis.conv.mapping.srcHandler.source.parsers;

import com.eis.conv.mapping.srcHandler.source.files.properties.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.files.properties.pObjects.PropertyKeyValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public final class PropertiesFileParser {
    public static SourcePropertyFile parse(String fileContent) throws IOException {
        return readPropertiesFile(fileContent);
    }


    private static SourcePropertyFile readPropertiesFile(String fileContent) throws IOException {
        SourcePropertyFile result = new SourcePropertyFile();

        Properties pf = new Properties();
        pf.load(new ByteArrayInputStream(fileContent.getBytes()));

        Set<Object> keys = getAllKeys(pf);
        for (Object objKey : keys) {
            String key = (String) objKey;
            String value = pf.getProperty(key);
            result.getPropertyKeyValues().add(new PropertyKeyValue(key.trim(), value.trim()));
        }
        return result;
    }


    private static Set<Object> getAllKeys(Properties pf) {
        Set<Object> keys = pf.keySet();
        return keys;
    }
}
