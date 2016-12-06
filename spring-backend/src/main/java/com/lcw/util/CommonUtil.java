package com.lcw.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>公共工具类</p>
 *
 * @author liuchenwei
 * @date 2015/12/20
 */
public class CommonUtil {

    /**
     * 判断参数字符串是否为空串
     *
     * @param s 字符串
     * @return boolean
     */
    public static boolean isEmpty(String s)
    {
        return StringUtils.isEmpty(s);
    }

    /**
     * 判断参数对象是否为 null 或空串
     *
     * @param object 对象
     * @return boolean
     */
    public static boolean isEmpty(Object object)
    {
        return object == null || isEmpty(object.toString());
    }

    /**
     * 判断参数数组是否为 null 或空
     *
     * @param array 数组对象
     * @return boolean
     */
    public static <T> boolean isEmpty(T[] array)
    {
        return array == null || array.length == 0;
    }
}
