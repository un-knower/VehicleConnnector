package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 车辆控制数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:18
 */
@Slf4j
@FrameProcessor
public class VehicleControlFrameHandler implements FrameHandler {

    /**
     * 车辆控制数据帧处理器
     */
    private final int VEHICLE_CONTROL = FrameType.VEHICLE_CONTROL.getValue();


    @Override
    public boolean support(BaseFrame frame) {
        return VEHICLE_CONTROL == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
