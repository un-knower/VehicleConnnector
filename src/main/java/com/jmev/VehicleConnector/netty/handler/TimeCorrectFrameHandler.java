package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 终端校时数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:21
 */
@Slf4j
@FrameProcessor
public class TimeCorrectFrameHandler implements FrameHandler {

    @Override
    public boolean support(BaseFrame frame) {
        return FrameType.TIME_CORRECT == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
