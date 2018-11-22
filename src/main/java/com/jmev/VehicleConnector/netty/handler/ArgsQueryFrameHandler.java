package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 参数查询数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 13:10
 */
@Slf4j
@FrameProcessor
public class ArgsQueryFrameHandler implements FrameHandler {

    /**
     * 参数查询数据帧标示
     */
    private final int ARGS_QUERY = FrameType.ARGS_QUERY.getValue();

    @Override
    public boolean support(BaseFrame frame) {
        return ARGS_QUERY == frame.getCommandSymbol();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
