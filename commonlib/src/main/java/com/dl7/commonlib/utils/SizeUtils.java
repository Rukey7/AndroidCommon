package com.dl7.commonlib.utils;

import java.math.BigDecimal;

/**
 * SizeUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-5-15
 */
public class SizeUtils {

    /** gb to byte **/
    public static final long GB_2_BYTE = 1073741824;
    /** mb to byte **/
    public static final long MB_2_BYTE = 1048576;
    /** kb to byte **/
    public static final long KB_2_BYTE = 1024;

    private SizeUtils() {
        throw new AssertionError();
    }

    /**
     *
     *
     * @param size 传入的大小
     * @return  格式化单位返回格式化之后的值
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
