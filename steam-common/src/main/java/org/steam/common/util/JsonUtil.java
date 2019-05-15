package org.steam.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Json 工具类，封装自fastjson
 *
 */
public class JsonUtil {
    /**
     * 数组转为字符串
     *
     * @param source 源数组
     * @return 转化后的字符串
     */
    public static String arrayToString(Object[] source) {
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(source);
        return jsonArray.toJSONString();
    }

    /**
     * 对象转为json字符串
     *
     * @param source 源对象
     * @return 转后的json字符串
     */
    public static String objectToString(Object source) {
        if (source == null) {
            return null;
        }
        return JSON.toJSONString(source);
    }

    /**
     * Map 转为json字符串
     *
     * @param source 源map
     * @return 转化后的字符串
     */
    public static String mapToString(Map<?, ?> source) {
        if (source == null) {
            return null;
        }
        return JSON.toJSONString(source, true);
    }

    /**
     * Json字符串转为Map
     *
     * @param source 源字符串
     * @return 转化后的Map
     */
    public static Map<?, ?> stringToMap(String source) {
        if (source == null) {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(source);
        return jsonObject;
    }

    /**
     * json字符串转Javabean
     *
     * @param json        待转字符串
     * @param targetClass 目标bean
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> targetClass) {
        return JSON.parseObject(json, targetClass);
    }
}
