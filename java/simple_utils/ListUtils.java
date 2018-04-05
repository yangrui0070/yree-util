package com.sunlands.clearing.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: YRee
 * Date: 2017/8/23
 * Time: 下午9:24
 */
public class ListUtils {
    /**
     * 将一个list拆为n个,将元素平分到个个小数组
     *
     * @param n
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> getLittleList(int n, List<T> list) {
        List<List<T>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }

        for (int i = 0; i < n; i++) {
            List<T> llist = new ArrayList<>();
            result.add(llist);
        }
        int i = 0;
        for (T t : list) {
            int index = ++i % n;
            result.get(index).add(t);
        }
        return result;
    }

    /**
     * 将字符数组,转换为可以使用数据库查询,可以用in的方式
     * @param strArray
     * @return
     */
    public static String arrayToString(String[] strArray) {
        if (strArray == null || strArray.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer(" (");

        for (int i = 0; i < strArray.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("'");
            sb.append(strArray[i]);
            sb.append("'");
        }
        sb.append(") ");
        return sb.toString();
    }
}
