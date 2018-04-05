package com.sunlands.clearing.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtils {

    private static final double ZERO = 0.001;
    private static DecimalFormat df = new DecimalFormat("#.00");


    public static Double getDecimalFormatDouble(Double value) {
        return getDecimalFormatDouble(value, null);
    }


    public static Double getDecimalFormatDouble(Double value, String format) {
        DecimalFormat decimalFormat;
        if (format != null) {
            decimalFormat = new DecimalFormat(format);
        } else {
            decimalFormat = df;
        }
        return Double.valueOf(decimalFormat.format(value));
    }


    /**
     * 判断两个浮点数d1和d2是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(BigDecimal d1, BigDecimal d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("illegal argument, for input null");
        }
        return equals(d1.doubleValue(), d2.doubleValue());
    }

    /**
     * 判断浮点数d1是否大于d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean great(BigDecimal d1, BigDecimal d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("illegal argument, for input null");
        }
        return great(d1.doubleValue(), d2.doubleValue());
    }

    /**
     * 判断浮点数d1是否大于等于d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean greatOrEquals(BigDecimal d1, BigDecimal d2) {
        if (d1 == null || d2 == null) {
            throw new IllegalArgumentException("illegal argument, for input null");
        }
        return greatOrEquals(d1.doubleValue(), d2.doubleValue());
    }

    /**
     * 判断两个浮点数d1和d2是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) < ZERO;
    }

    /**
     * 判断浮点数d1是否大于d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean great(double d1, double d2) {
        return d1 - d2 > ZERO;
    }

    /**
     * 判断浮点数d1是否大于等于d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean greatOrEquals(double d1, double d2) {
        return equals(d1, d2) || great(d1, d2);
    }

}
