package com.zpq.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 获取properties属性文件里的键值对
 * @author 35147
 */
public class PropertiesUtil {
    /**
     *
     * @param name properties文件的名字,不加后缀
     * @return
     */
    public static Map<String, String> getMap(String name) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(name);
        //遍历取值
        Enumeration enumeration = resourceBundle.getKeys();
        Map map = new HashMap(4);
        while (enumeration.hasMoreElements()) {
            try {
                String key = (String) enumeration.nextElement();
                String value = resourceBundle.getString(key);
                value = URLEncoder.encode(value, "ISO-8859-1");
                value = URLDecoder.decode(value, "GBK");
                map.put(key,value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
