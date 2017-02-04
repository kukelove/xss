package com.xp.brushms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by hzm on 2015/6/1.
 */
public class JsonUtils {
    private static SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static <T> T parseJson(String json, Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dtFormat);
        T ret = null;
        try {
            ret = mapper.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dtFormat);
        String ret = null;
        try {
            ret = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
