package com.jmev.VehicleConnector.netty.entity.upstream;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import com.jmev.VehicleConnector.util.ByteUtils;
import io.netty.util.CharsetUtil;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <h2>车辆登录数据帧定义</h2>
 *
 * @author Jun
 * @date 2018-11-17 14:07
 */
@Data
public final class VehicleLoginUpFrame {

    /**
     * 完整数据帧
     */
    private BaseFrame baseFrame;

    /**
     * 数据采集时间
     */
    private byte[] timestampByte;

    /**
     * 登录流水号
     */
    private byte[] loginFlowNumByte;

    /**
     * sim卡iccid
     */
    private byte[] iccidByte;

    /**
     * 可充电储能子系统(电池)个数
     */
    private byte batteryNumByte;

    /**
     * 可充电储能子系统(电池)编码长度
     */
    private byte batteryCodeLengthByte;

    /**
     * 可充电储能子系统(电池)编码
     */
    private byte[] batteryCodeByte;

    public LocalDateTime getTimestamp(){
        return ByteUtils.bytesToLocalDateTime(timestampByte);
    }

    public int getLoginFlowNum(){
        return ByteUtils.getUnsignedInt(loginFlowNumByte);
    }

    public String getIccid(){
        return new String(iccidByte, CharsetUtil.US_ASCII);
    }

    public int getBatteryNum(){
        return 0xff & batteryNumByte;
    }

    public int getBatteryCodeLength(){
        return 0xff & batteryCodeLengthByte;
    }

    public String getBatteryCode(){
        return new String(batteryCodeByte, CharsetUtil.US_ASCII);
    }
}
