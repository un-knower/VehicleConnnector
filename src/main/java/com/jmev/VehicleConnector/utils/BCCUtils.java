package com.jmev.VehicleConnector.utils;

/**
 * BCC（数据异或校验）
 *
 * @author Jun
 * @date 2018-11-17 09:23
 */
public class BCCUtils {

    /**
     * 用于计算数据帧的bcc码
     *
     * @param target       给定的字节
     * @param jumpLastByte 传 {@code true} 计算时跳过最后一个字节
     * @return 校验码
     */
    public static byte bccCalculate(byte[] target, boolean jumpLastByte) {
        if (target == null || target.length < 22) {
            throw new IllegalArgumentException("给定的字节数据不能为null或长度小于22");
        }

        byte bcc;
        int len = target.length;

        //判断数据有无裁剪
        if ((target[0] == 0x23) && (target[1] == 0x23)) {
            bcc = target[2];

            if (jumpLastByte) {
                //去掉开头的[0x23,0x23],最后的bcc码
                for (int i = 3; i < len - 1; i++) {
                    bcc ^= target[i];
                }
            } else {
                //仅去掉开头的[0x23,0x23]
                for (int i = 3; i < len; i++) {
                    bcc ^= target[i];
                }
            }
        } else {
            bcc = target[0];

            for (int i = 1; i < len; i++) {
                bcc ^= target[i];
            }
        }

        return bcc;
    }
}
