package com.jmev.VehicleConnector.netty.entity.upstream.realtime;

import lombok.Data;

/**
 * 燃料电池数据
 *
 * @author Jun
 * @date 2018-11-19 18:27
 */
@Data
public final class FuelCellData {

    /**
     * 燃料电池电压
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~20000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.1V</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] voltage;

    /**
     * 燃料电池电流
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~20000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.1V</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] current;

    /**
     * 燃料电池消耗率
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~60000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.01(kg/100km)</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] consumptionRate;

    /**
     * 燃料电池温度探针数量
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~65531</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] temperatureProbeNum;

    /**
     * 探针温度值
     *
     * <pre>
     * 字段长度：<b>temperatureProbeNum * 1</b>
     *
     * 有效值范围：<b>0~240</b>
     * 偏移量：    <b>-40</b>
     * 精度：      <b>1℃</b>
     *
     * <b>0xFE</b>：异常
     * <b>0xFF</b>：无效
     * </pre>
     */
    private byte[] probeTemperature;

    /**
     * 氢系统中最高温度
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~2400</b>
     * 偏移量：    <b>-40</b>
     * 精度：      <b>0.1℃</b>
     *
     * <b>0xFE</b>：异常
     * <b>0xFF</b>：无效
     * </pre>
     */
    private byte[] hydrogenSystemMaxTemperature;

    /**
     * 氢系统中最高温度探针代号
     *
     * <pre>
     * 有效值范围：<b>0~252</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte hydrogenSystemMaxTemperatureProbeCode;

    /**
     * 氢气最高浓度
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~50000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>1ppm</b>
     *
     * <b>0xFE</b>：异常
     * <b>0xFF</b>：无效
     * </pre>
     */
    private byte[] maxHydrogenConcentration;

    /**
     * 氢气最高浓度探针代号
     *
     * <pre>
     * 有效值范围：<b>0~252</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte maxHydrogenConcentrationProbeCode;

    /**
     * 氢气最高压力
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~1000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>01MPa</b>
     *
     * <b>0xFE</b>：异常
     * <b>0xFF</b>：无效
     * </pre>
     */
    private byte[] maxHydrogenPressure;

    /**
     * 氢气最高压力传感器代号
     *
     * <pre>
     * 有效值范围：<b>0~252</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] maxHydrogenPressureSensorCode;

    /**
     * 高压 DC/DC 状态
     *
     * <pre>
     *  <b>0x01</b>：工作
     *  <b>0x02</b>：断开
     *
     *  <b>0xFE</b>：异常
     *  <b>0xFF</b>：无效
     * </pre>
     */
    private byte highPressureDcdcStatus;
}
