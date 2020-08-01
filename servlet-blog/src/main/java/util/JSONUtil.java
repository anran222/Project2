package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author:xiang
 * json序列化与反序列化工具类
 */
public class JSONUtil {

    private static volatile ObjectMapper MAPPER;

    public static ObjectMapper get(){
        if (MAPPER==null){
            synchronized (JSONUtil.class){
                if (MAPPER==null){
                    MAPPER=new ObjectMapper();
                }
            }
        }
        return MAPPER;
    }

    //java对象序列化为json字符串
    //o   对象
    public static String serialize(Object o){
        try {
            return get().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON序列化失败，对象为："+o,e);
        }
    }

    //将json字符串反序列化为java对象
    /*
    *json    字符串
    *clazz    java类型
     */
    public static <T> T deserialize(String json,Class<T> clazz){
        try {
            return get().readValue(json,clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON反序列化失败，JSON字符串为："+json,e);
        }
    }

    public static <T> T deserialize(InputStream is, Class<T> clazz){
        try {
            return get().readValue(is,clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON反序列化失败",e);
        }
    }
}
