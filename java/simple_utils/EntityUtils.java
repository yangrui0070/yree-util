package com.sunlands.clearing.common.utils;

import com.sunlands.clearing.common.exception.FieLdIsNullException;
import lombok.extern.apachecommons.CommonsLog;

import java.lang.reflect.Field;

/**
 * @author: YRee
 * @data: 2017/12/15
 * @time: 下午9:38
 * @function: 类处理对象
 */
@CommonsLog
public class EntityUtils {

    public static final String EMPTYSTRING = "";

    /**
     * 判断属性是否为空
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
        boolean flag = false;
        if (obj == null) {
            return flag;
        }
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) == null || EMPTYSTRING.equals(f.get(obj))) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * 判断属性是否为空
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static void checkObjFieldIsNullAndThrowExcetpion(Object obj) {
        if (obj == null) {
            throw new FieLdIsNullException("对象本身为空");
        }

        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(obj) == null || EMPTYSTRING.equals(f.get(obj))) {
                    throw new FieLdIsNullException(String.format("%s的%s属性为空  %s", obj.getClass(), f.getName(), obj.toString()));
                }
            }
        } catch (IllegalAccessException e) {
            log.error("反射时出现异常: " + obj.toString(), e);
        }
    }

}
