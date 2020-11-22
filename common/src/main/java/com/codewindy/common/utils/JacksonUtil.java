package com.codewindy.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author codewindy
 * @date 2020-04-09 8:29 PM
 * @since 1.0.0
 */
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JacksonUtil {

    private static ObjectMapper defaultMapper;
    private static ObjectMapper formatedMapper;

    static {
        // 默认的ObjectMapper
        defaultMapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        defaultMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        defaultMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        defaultMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // JSON节点不包含属性值为NULL
        defaultMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        formatedMapper = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        formatedMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 所有日期格式都统一为固定格式
        formatedMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        formatedMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    }

    /**
     * 将对象转化为json数据
     *
     * @param obj the obj
     * @return string string
     * @throws IOException the io exception
     */
    public static String toJson(Object obj) throws IOException {
        Preconditions.checkArgument(obj != null, "this argument is required; it must not be null");
        return defaultMapper.writeValueAsString(obj);
    }

    /**
     * json数据转化为对象(Class)
     * User u = JacksonUtil.parseJson(jsonValue, User.class);
     * User[] arr = JacksonUtil.parseJson(jsonValue, User[].class);
     *
     * @param <T>       the type parameter
     * @param jsonValue the json value
     * @param valueType the value type
     * @return t t
     * @throws IOException the io exception
     */
    public static <T> T parseJson(String jsonValue, Class<T> valueType) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonValue), "this argument is required; it must not be null");
        return defaultMapper.readValue(jsonValue, valueType);
    }

    /**
     * json数据转化为对象(JavaType)
     *
     * @param <T>       the type parameter
     * @param jsonValue the json value
     * @param valueType the value type
     * @return t t
     * @throws IOException the io exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseJson(String jsonValue, JavaType valueType) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonValue), "this argument is required; it must not be null");
        return (T) defaultMapper.readValue(jsonValue, valueType);
    }

    /**
     * json数据转化为对象(TypeReference)
     *
     * @param <T>          the type parameter
     * @param jsonValue    the json value
     * @param valueTypeRef the value type ref
     * @return t t
     * @throws IOException the io exception
     */
    public static <T> T parseJson(String jsonValue, TypeReference<T> valueTypeRef) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonValue), "this argument is required; it must not be null");
        return (T) defaultMapper.readValue(jsonValue, valueTypeRef);
    }

    /**
     * 将对象转化为json数据(时间转换格式： "yyyy-MM-dd HH:mm:ss")
     *
     * @param obj the obj
     * @return string string
     * @throws IOException the io exception
     */
    public static String toJsonWithFormat(Object obj) throws IOException {
        Preconditions.checkArgument(obj != null, "this argument is required; it must not be null");
        return formatedMapper.writeValueAsString(obj);
    }

    /**
     * json数据转化为对象(时间转换格式： "yyyy-MM-dd HH:mm:ss")
     * User u = JacksonUtil.parseJsonWithFormat(jsonValue, User.class);
     * User[] arr = JacksonUtil.parseJsonWithFormat(jsonValue, User[].class);
     *
     * @param <T>       the type parameter
     * @param jsonValue the json value
     * @param valueType the value type
     * @return t t
     * @throws IOException the io exception
     */
    public static <T> T parseJsonWithFormat(String jsonValue, Class<T> valueType) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonValue), "this argument is required; it must not be null");
        return formatedMapper.readValue(jsonValue, valueType);
    }

    /**
     * json数据转化为对象(JavaType)
     *
     * @param <T>       the type parameter
     * @param jsonValue the json value
     * @param valueType the value type
     * @return t t
     * @throws IOException the io exception
     */
    public static <T> T parseJsonWithFormat(String jsonValue, JavaType valueType) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonValue), "this argument is required; it must not be null");
        return (T) formatedMapper.readValue(jsonValue, valueType);
    }

    /**
     * json数据转化为对象(TypeReference)
     *
     * @param <T>          the type parameter
     * @param jsonValue    the json value
     * @param valueTypeRef the value type ref
     * @return t t
     * @throws IOException the io exception
     */
    public static <T> T parseJsonWithFormat(String jsonValue, TypeReference<T> valueTypeRef) throws IOException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(jsonValue), "jsonValue is not null");
        return (T) formatedMapper.readValue(jsonValue, valueTypeRef);
    }

    /*public static void main(String[] args) throws IOException {
        Map  params  =new HashMap();
        params.put("name","jack");
        params.put("birthday",new Date());
        params.put("",null);
        //{"birthday":"2020-04-09 21:06:33","":null,"name":"jack"} 非去空格式化时间
        System.out.println("JacksonUtil.toJson(params) = " + JacksonUtil.toJsonWithFormat(params));
        //{"birthday":1586437647407,"name":"jack"} 去空返回时间戳
        System.out.println("JacksonUtil.toJson(params) = " + JacksonUtil.toJson(params));
    }*/
}