package com.jmev.VehicleConnector.netty.entity.upstream.realtime;

import lombok.Data;

/**
 * 发动机数据
 *
 * @author Jun
 * @date 2018-11-19 19:05
 */
@Data
public final class EngineData {

    /**
     * 发动机状态
     *
     * <pre>
     *  <b>0x01</b>：启动
     *  <b>0x02</b>：关闭
     *
     *  <b>0xFE</b>：异常
     *  <b>0xFF</b>：无效
     * </pre>
     */
    private byte engineStatus;

    /**
     * 曲轴转速
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~60000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>1r/min</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] crankshaftSpeed;

    /**
     * 燃料消耗率
     *
     * <pre>
     * 字段长度：<b>2</b>
     *
     * 有效值范围：<b>0~60000</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.01(L/100km)</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte[] fuelConsumptionRate;
}
