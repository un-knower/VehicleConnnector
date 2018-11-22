package com.jmev.VehicleConnector.common.constant;

/**
 * 应答标示定义
 */
public enum ResponseSymbolType {
    //@formatter:off

    /** 操作成功 */
    SUCCESS(0X01),

    /** 操作失败 */
    FAILURE(0X02),

    /** VIN重复 */
    VIN_DUPLICATE(0X03),

    /** 命令 */
    COMMAND(0XFE);


    int value;

    ResponseSymbolType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
