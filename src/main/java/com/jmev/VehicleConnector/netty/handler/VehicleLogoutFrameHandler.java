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

    @Override
    public boolean support(BaseFrame frame) {
        return FrameType.VEHICLE_LOGOUT == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {
        log.info("车辆登出接口被调用");

        //解析数据，获取登出数据帧
        VehicleLogoutUpFrame logoutFrame = vehicleLogoutUpFrameAssembly(frame);

        log.info("VIN：{}的车辆登出", frame.getVin());
        System.out.println(logoutFrame);

        //关闭连接
        ctx.close();
    }

    /**
     * 车辆登出数据帧解析
     *
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
