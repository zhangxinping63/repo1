package com.msb;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Zhangxp
 * @Date: 2020-04-28 14:29
 */
public class PropertyMgr {

    static Properties props = new Properties();
    static{
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(props == null){
            return null;
        }
        return props.get(key);
    }
}
