package com.eis.conv.mapping.srcHandler.source.sourceParsers;

import com.eis.conv.mapping.srcHandler.source.entities.files.files.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.entities.pObjects.PropertyKeyValue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesFileParser {
    public SourcePropertyFile parse(String fileContent) throws IOException {
        return readPropertiesFile(fileContent);
    }


    private SourcePropertyFile readPropertiesFile(String fileContent) throws IOException {
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


    private Set<Object> getAllKeys(Properties pf) {
        Set<Object> keys = pf.keySet();
        return keys;
    }
}
