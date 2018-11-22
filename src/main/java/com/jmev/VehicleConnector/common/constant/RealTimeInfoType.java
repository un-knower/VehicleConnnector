package com.jmev.VehicleConnector.common.constant;

/**
 * 实时信息中整车信息类型
 *
 * @author Jun
 * @date 2018-11-17 15:51
 */
public enum RealTimeInfoType {

    /**
     * 整车数据
     */
    VEHICLE_DATA(0X01),

    /**
     * 驱动电机数据
     */
    MOTOR_INFO(0X02),

    /**
     * 燃料电池数据
     */
    FUEL_CELL_DATA(0x03),

    /**
     * 发动机数据
     */
    ENGINE_INFO(0x04),

    /**
     * 车辆位置数据
     */
    VEHICLE_POSITION_DATA(0X05),


    /**
     * 极值数据
     */
    EXTREME_DATA(0X06),

    /**
     * 报警数据
     */
    ALARM_DATA(0x07),

    /**
     * 可充电储能装置电压数据
     * <p>
     * <b>RESD</b> 是 <em>Rechargeable energy storage device</em> 的缩写
     */
    RESD_VOLTAGE_INFO(0X08),

    /**
     * 可充电储能装置温度数据
     * <p>
     * <b>RESD</b> 是 <em>Rechargeable energy storage device</em> 的缩写
     */
    RESD_TEMPERATURE_INFO(0X09),

    /**
     * 自定义信息，由公司自己定义的信息
     */
    SELF_DEFINITION_INFO(0X80);


    private int value;

    RealTimeInfoType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
