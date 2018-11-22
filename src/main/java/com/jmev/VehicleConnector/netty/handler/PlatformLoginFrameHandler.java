package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 平台登录数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:07
 */
@Slf4j
@FrameProcessor
public class PlatformLoginFrameHandler implements FrameHandler {

    /**
     * 平台登录数据帧标示
     */
    private final int PLATFORM_LOGIN = FrameType.PLATFORM_LOGIN.getValue();

    @Override
    public boolean support(BaseFrame frame) {
        return PLATFORM_LOGIN == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
