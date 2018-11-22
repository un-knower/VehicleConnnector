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

    /**
     * 补发数据帧标示
     */
    private final int RESEND = FrameType.RESEND.getValue();

    @Override
    public boolean support(BaseFrame frame) {
        return RESEND == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
