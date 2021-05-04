package com.kandao.apiapplication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonConverter {

    private static final String EMPTY_STRING = "";

    public static String encode (Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        }
        return EMPTY_STRING;
    }
}
