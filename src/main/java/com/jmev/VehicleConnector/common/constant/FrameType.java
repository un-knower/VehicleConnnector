package com.jmev.VehicleConnector.common.constant;

/**
 * 数据帧类型
 *
 * @author Jun
 * @date 2018-11-17 10:23
 */
public enum FrameType {

    /** 车辆登录 */
    VEHICLE_LOGIN(0x01),

    /** 实时信息包 */
    REALTIME_INFO(0x02),

    /** 补发数据 */
    RESEND(0X03),

    /** 车辆登出 */
    VEHICLE_LOGOUT(0x04),

    /** 平台登录 */
    PLATFORM_LOGIN(0x05),

    /** 平台登出 */
    PLATFORM_LOGOUT(0X06),

    /** 参数查询 */
    ARGS_QUERY(0X80),

    /** 参数设置 */
    ARGS_SETUP(0x81),

    /** 车辆控制 */
    VEHICLE_CONTROL(0x82),

    /** 心跳 */
    HEARTBEAT(0x07),

    /** 终端校时 */
    TIME_CORRECT(0x08);

    private int value;

    FrameType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
