package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 平台登出数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:08
 */
@Slf4j
@FrameProcessor
public class PlatFormLogoutFrameHandler implements FrameHandler {

    /**
     * 平台登出数据帧标示
     */
    private final int PLATFORM_LOGOUT = FrameType.PLATFORM_LOGOUT.getValue();

    @Override
    public boolean support(BaseFrame frame) {
        return PLATFORM_LOGOUT == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
