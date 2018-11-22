package com.jmev.VehicleConnector.netty.entity.upstream.realtime;

import lombok.Data;

/**
 * 车辆定位数据
 *
 * @author Jun
 * @date 2018-11-19 19:13
 */
@Data
public final class VehiclePositionData {

    /**
     * 定位状态
     *
     * <pre>
     *  <b>[bit7,bit6,bit5,bit4,bit3,bit2,bit1,bit0]</b>
     *
     *  <b>bit0</b>：
     *      0：有效定位
     *      1：无效定位（当数据通讯正常，而不能获取定位时，发送最后一次有效定位信息，并将定位状态置为无效）
     *  <b>bit1</b>：
     *      1：北纬
     *      0：南纬
     *  <b>bit2</b>：
     *      1：东经
     *      0：西经
     *  <b>bit3~bit7</b>：预留
     * </pre>
     */
    private byte positioningStatus;

    /**
     * 经度
     *
     * <pre>
     * 字段长度：<b>4</b>
     *
     * 有效值范围：<b>Integer.MAX * 2</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.000001</b>，百万分之一
     * </pre>
     */
    private byte[] longitude;

    /**
     * 纬度
     *
     * <pre>
     * 字段长度：<b>4</b>
     *
     * 有效值范围：<b>Integer.MAX * 2</b>
     * 偏移量：    <b>0</b>
     * 精度：      <b>0.000001</b>，百万分之一
     * </pre>
     */
    private byte[] latitude;
}
