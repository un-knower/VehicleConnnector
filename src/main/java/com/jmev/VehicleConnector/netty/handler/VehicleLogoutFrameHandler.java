package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import com.jmev.VehicleConnector.netty.entity.upstream.VehicleLogoutUpFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 车辆登出数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:02
 */
@Slf4j
@FrameProcessor
public class VehicleLogoutFrameHandler implements FrameHandler {

    /**
     * 车辆登出数据帧标示
     */
    private final int VEHICLE_LOGOUT = FrameType.VEHICLE_LOGOUT.getValue();

    @Override
    public boolean support(BaseFrame frame) {
        return VEHICLE_LOGOUT == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {
        log.info("车辆登出接口被调用");

        //解析数据，获取登录数据帧
        VehicleLogoutUpFrame logoutFrame = vehicleLogoutUpFrameAssembly(frame);

        log.info("VIN：{}的车辆上传了数据", frame.getVin());
        System.out.println(logoutFrame);
    }

    /**
     * @param frame 完整数据帧
     * @return
     */
    private VehicleLogoutUpFrame vehicleLogoutUpFrameAssembly(final BaseFrame frame) {
        //新建车辆登出数据帧对象，开始解析
        VehicleLogoutUpFrame logoutFrame = new VehicleLogoutUpFrame();

        //保存原始数据帧
        logoutFrame.setBaseFrame(frame);

        //获取需要解析的数据单元
        byte[] data = frame.getDataUnitByte();

        //时间戳
        logoutFrame.setTimestampByte(Arrays.copyOfRange(data, 0, 6));

        //登出流水号
        logoutFrame.setLogoutFlowNumByte(Arrays.copyOfRange(data, 6, 8));

        return logoutFrame;
    }
}
