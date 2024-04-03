package com.data_structure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String parseJson(Object ob) {
        try {
            return objectMapper.writeValueAsString(ob);
        } catch (JsonProcessingException ignore) {
            return "ERROR";
        }
    }
}
