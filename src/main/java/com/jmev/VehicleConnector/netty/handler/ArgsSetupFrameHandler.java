package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 参数设置数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:12
 */
@Slf4j
@FrameProcessor
public class ArgsSetupFrameHandler implements FrameHandler {

    /**
     * 参数设置数据帧标示
     */
    private final int ARGS_SETUP = FrameType.ARGS_SETUP.getValue();

    @Override
    public boolean support(BaseFrame frame) {
        return ARGS_SETUP == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
