package com.jmev.VehicleConnector.netty.entity.upstream.realtime;

import lombok.Data;

/**
 * 驱动电机数据
 *
 * @author Jun
 * @date 2018-11-19 17:42
 */
@Data
public final class MotorData {

    /**
     * 驱动电机序号
     *
     * <pre>
     * 有效值范围：<b>1~253</b>
     * 偏移量：    <b>none</b>
     * 精度：      <b>none</b>
     * </pre>
     */
    private byte motorCode;

    /**
     * 驱动电机状态
     *
     * <pre>
     *  <b>0x01</b>：耗电
     *  <b>0x02</b>：发电
     *  <b>0x03</b>：关闭状态
     *  <b>0x04</b>：准备状态
     *
     *  <b>0xFE</b>：异常
     *  <b>0xFF</b>：无效
     * </pre>
     */
    private byte motorStatus;

    /**
     * 驱动电机控制器温度
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
    private byte motorControllerTemperature;

    /**
     * 驱动电机转速
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~65531</b>
     * 偏移量：    <b>-20000</b>
     * 精度：      <b>1r/min</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] motorRotatingSpeed;


    /**
     * 驱动电机转矩
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~65531</b>
     * 偏移量：    <b>-20000</b>
     * 精度：      <b>0.1rN*m</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] motorTorque;

    /**
     * 驱动电机温度
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
    private byte motorTemperature;

    /**
     * 驱动电机控制器输入电压
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~60000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.1V</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] motorControllerInputVoltage;

    /**
     * 电机控制器直流母线电流
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0-20000</b>
     * 偏移量：    <b>10000</b>
     * 精度：      <b>0.1A</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] motorControllerDcBusCurrent;
}
