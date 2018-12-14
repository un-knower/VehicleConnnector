package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 补发数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:05
 */
@Slf4j
@FrameProcessor
public class ResendFrameHandler implements FrameHandler {

    @Override
    public boolean support(BaseFrame frame) {
        return FrameType.RESEND == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
