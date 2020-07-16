package com.eis.conv.mapping.core.json;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHandler {
    public static List<Map<String, Object>> parseModelReportJson(String jsonString, String rootNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
        });

        map = getMapContainsKey(map, rootNode);
        if (map.containsKey(rootNode)) {
            return (List<Map<String, Object>>) map.get(rootNode);
        }

        return new ArrayList<Map<String, Object>>();
    }


    private static Map<String, Object> getMapContainsKey(Object obj, String key) {
        if (obj instanceof Map<?, ?>) {
            Map<String, Object> map = (Map<String, Object>) obj;
            if (map.containsKey(key)) {
                return map;
            } else {
                Map<String, Object> result;
                for (String k : map.keySet()) {
                    result = getMapContainsKey(map.get(k), key);
                    if (result.containsKey(key)) {
                        return result;
                    }

                }
            }

        }
        return  new HashMap<String, Object>();
    }
}






