package com.jmev.VehicleConnector.netty.entity.upstream;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import com.jmev.VehicleConnector.util.ByteUtils;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <h2>车辆登出数据帧</h2>
 *
 * @author Jun
 * @date 2018-11-17 16:19
 */
@Data
public final class VehicleLogoutUpFrame {

    /**
     * 车辆上传的完整数据帧
     */
    private BaseFrame baseFrame;

    /**
     * 数据时间
     */
    private byte[] timestampByte;
    private LocalDateTime timestamp;

    /**
     * 车辆登出流水号
     */
    private byte[] logoutFlowNumByte;
    private int logoutFlowNum;


    public void setTimestampByte(byte[] timestampByte) {
        this.timestampByte = timestampByte;

        this.timestamp = LocalDateTime.of(
                2000 + timestampByte[0], timestampByte[1], timestampByte[2],
                timestampByte[3], timestampByte[4], timestampByte[5]);
    }

    public void setLogoutFlowNumByte(byte[] logoutFlowNumByte) {
        this.logoutFlowNumByte = logoutFlowNumByte;
        this.logoutFlowNum = ByteUtils.getUnsignedInt(logoutFlowNumByte);
    }

    @Override
    public String toString() {
        return "VehicleLogoutUpFrame{" +
                "timestamp=" + timestamp +
                ", logoutFlowNum=" + logoutFlowNum +
                '}';
    }
}
