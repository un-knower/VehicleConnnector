package com.jmev.VehicleConnector.utils;

import java.time.LocalDateTime;

/**
 * 字节相关工具
 *
 * @author Jun
 * @date 2018-11-17 17:19
 */
public class ByteUtils {

    /**
     * 多字节数组合并
     *
     * @param values 需要合并的自己数组
     * @return 合并后的字节数组
     */
    public static byte[] byteMerge(byte[]... values) {
        int len = 0;
        for (byte[] value : values) {
            len += value.length;
        }

        byte[] result = new byte[len];

        int destPos = 0;
        for (byte[] value : values) {
            System.arraycopy(value, 0, result, destPos, value.length);
            destPos += value.length;
        }

        return result;
    }

    /**
     * 获取长度
     *
     * @param values 需要计算的字节数组
     * @return 计算后的结果
     */
    public static int getUnsignedInt(byte[] values) {
        int result;
        switch (values.length) {
            case 2:
                result = ((0xff & values[0]) << 8) + (0xff & values[1]);
                break;
            default:
                throw new IllegalArgumentException("不支持长度为" + values.length + "字节数组，仅支持2字节");
        }

        return result;
    }

    /**
     * 将字节转换为时间,仅针对特定长度的字节数据
     *
     * @param values 时间数组
     * @return 时间
     */
    public static LocalDateTime bytesToLocalDateTime(byte[] values){
        return LocalDateTime.of(
                2000 + values[0], values[1], values[2],
                values[3], values[4], values[5]);
    }
}
