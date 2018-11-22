package com.jmev.VehicleConnector.netty.entity.upstream.realtime;

import lombok.Data;

/**
 * 整车数据
 *
 * @author Jun
 * @date 2018-11-19 17:43
 */
@Data
public final class VehicleData {

    /**
     * 整车状态
     *
     * <pre>
     *  <b>0x01</b>：车辆启动
     *  <b>0x02</b>：熄火
     *  <b>0x03</b>：其它
     *
     *  <b>0xFE</b>：异常
     *  <b>0xFF</b>：无效
     * </pre>
     */
    private byte vehicleStatus;

    /**
     * 充电状态
     *
     * <pre>
     *  <b>0x01</b>：停车充电
     *  <b>0x02</b>：行驶充电
     *  <b>0x03</b>：未充电
     *  <b>0x04</b>：充电完成
     *
     *  <b>0xFE</b>：异常
     *  <b>0xFF</b>：无效
     * </pre>
     */
    private byte chargingStatus;

    /**
     * 运行模式
     *
     * <pre>
     *  <b>0x01</b>：纯电
     *  <b>0x02</b>：混动
     *  <b>0x03</b>：燃油
     *
     *  <b>0xFE</b>：异常
     *  <b>0xFF</b>：无效
     * </pre>
     */
    private byte workMode;

    /**
     * 车速
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~2200</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.1km/h</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] speed;

    /**
     * 累计里程
     *
     * <pre>
     * 字段长度：<b>4</b>
     *
     * 有效值范围：<b>0~9999999</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.1km</b>
     *
     * <b>0xFF,0xFF,0xFF,0XFE</b>：异常
     * <b>0xFF,0xFF,0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] cumulativeMileage;

    /**
     * 总电压
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~10000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.1v</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] totalVoltage;

    /**
     * 总电流
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~20000</b>
     * 偏移量：    <b>-10000</b>
     * 精度：      <b>0.1A</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] totalCurrent;

    /**
     * 剩余电量
     *
     * <pre>
     * 有效值范围：<b>0~100</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>1%</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte soc;

    /**
     * DC/DC状态
     *
     * <pre>
     *  <b>0x01</b>：工作
     *  <b>0x02</b>：断开
     *
     *  <b>0xFE</b>：异常
     *  <b>0xFF</b>：无效
     * </pre>
     */
    private byte dcdcStatus;

    /**
     * 档位
     *
     * <pre>
     *  <b>[bit7,bit6,bit5,bit4,bit3,bit2,bit1,bit0]</b>
     *
     *  <b>bit7</b>：预留，预留位用0表示
     *  <b>bit6</b>：预留，预留位用0表示
     *  <b>bit5</b>：
     *      1：有驱动力
     *      0：无驱动力
     *  <b>bit4</b>：
     *      1：有制动力
     *      0：无制动力
     *
     *  以下为<b>[bit3,bit2,bit1,bit0]</b>定义：
     *
     *  <b>[0,0,0,0]</b>：空档
     *  <b>[0,0,0,1]</b>：1档
     *  <b>[0,0,1,0]</b>：2档
     *  <b>[0,0,1,1]</b>：3档
     *  ...
     *  <b>[1,1,0,1]</b>：倒挡
     *  <b>[1,1,1,0]</b>：自动 D 挡
     *  <b>[1,1,1,1]</b>：停车 P 挡
     * </pre>
     */
    private byte gear;

    /**
     * 绝缘电阻
     *
     * <pre>
     * 有效值范围：<b>0~60000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>1kΩ</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] insulationResistance;

    /**
     * <strong>国标预留位</strong>
     * <p>
     * 加速踏板行程值
     *
     * <pre>
     * 有效值范围：<b>0~100</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>1%</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte acceleratorPedalPercent;

    /**
     * <strong>国标预留位</strong>
     * <p>
     * 制动踏板行程值
     *
     * <pre>
     * 有效值范围：<b>0~100</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>1%</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte brakePedalDistancePercent;
}
