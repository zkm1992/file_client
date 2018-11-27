package com.file.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Json对象转换工具类
 *
 * @author zkm on 2018-09-15
 * @version 1.0
 */
public class TransJsonUtil {

    private static JSONObject RESULT;

    /**
     * 对象转Json字符串
     *
     * @param obj       响应对象
     * @param statusMsg 响应状态及消息实体类
     * @return
     */
    public static String Obj2Json(Object obj, StatusMsg statusMsg) {
        RESULT = JSONObject.parseObject(JSON.toJSONString(statusMsg));
        RESULT.put("data", obj);
        return RESULT.toJSONString();
    }

}