package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳帧处理器
 *
 * @author Jun
 * @date 2018-11-17 12:30
 */
@Slf4j
@FrameProcessor
public class HeartBeatFrameHandler implements FrameHandler {

    /**
     * 心跳帧识别标示
     */
    private final int HEARTBEAT_SYMBOL = FrameType.HEARTBEAT.getValue();


    @Override
    public boolean support(BaseFrame frame) {
        return HEARTBEAT_SYMBOL == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
