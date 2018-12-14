package com.jmev.VehicleConnector.common.constant;

/**
 * 数据帧类型
 *
 * @author Jun
 * @date 2018-12-12 18:54
 */
public interface FrameType {

    /** 车辆登录 */
    int VEHICLE_LOGIN = 0x01;

    /** 实时信息 */
    int REALTIME_INFO = 0x02;

    /** 补发数据 */
    int RESEND = 0X03;

    /** 车辆登出 */
    int VEHICLE_LOGOUT = 0x04;

    /** 平台登录 */
    int PLATFORM_LOGIN = 0x05;

    /** 平台登出 */
    int PLATFORM_LOGOUT = 0X06;

    /** 参数查询 */
    int ARGS_QUERY = 0X80;

    /** 参数设置 */
    int ARGS_SETUP = 0x81;

    /** 车辆控制 */
    int VEHICLE_CONTROL = 0x82;

    /** 心跳 */
    int HEARTBEAT = 0x07;

    /** 终端校时 */
    int TIME_CORRECT = 0x08;
}
