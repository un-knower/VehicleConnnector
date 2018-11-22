package com.jmev.VehicleConnector.netty.entity.upstream.realtime;

import lombok.Data;

/**
 * 极值数据
 *
 * @author Jun
 * @date 2018-11-19 19:33
 */
@Data
public final class ExtremeData {

    /**
     * 最高电压电池子系统号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte maxVoltageBatteryCode;

    /**
     * 最高电压电池单体代号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte maxVoltageSingleBatteryCode;

    /**
     * 电池单体电压最高值
     *
     * <pre>
     * 字段大小：<b>2</b>
     *
     * 有效值范围：<b>0~15000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.001V</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] maxVoltageSingleBatteryValue;

    /**
     * 最低电压电池子系统号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte minVoltageBatteryCode;

    /**
     * 最低电压电池单体代号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte minVoltageSingleBatteryCode;

    /**
     * 电池单体电压最低值
     *
     * <pre>
     * 字段大小：<b>2</b>
     *
     * 有效值范围：<b>0~15000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.001V</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] minVoltageSingleBatteryValue;

    /**
     * 最高温度子系统号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte maxTemperatureSubSystemCode;

    /**
     * 最高温度探针序号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte maxTemperatureProbeCode;

    /**
     * 最高温度值
     *
     * <pre>
     * 有效值范围：<b>0~250</b>
     * 偏移量：    <b>-40℃</b>
     * 精度：      <b>1℃</b>
     *
     * <b>0xFE</b>：异常
     * <b>0xFF</b>：无效
     * </pre>
     */
    private byte maxTemperature;

    /**
     * 最低温度子系统号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte minTemperatureSubSystemCode;

    /**
     * 最低温度探针序号
     *
     * <pre>
     * 有效值范围：<b>1~250</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte minTemperatureProbeCode;

    /**
     * 最低温度值
     *
     * <pre>
     * 有效值范围：<b>0~250</b>
     * 偏移量：    <b>-40℃</b>
     * 精度：      <b>1℃</b>
     *
     * <b>0xFE</b>：异常
     * <b>0xFF</b>：无效
     * </pre>
     */
    private byte minTemperature;
}
