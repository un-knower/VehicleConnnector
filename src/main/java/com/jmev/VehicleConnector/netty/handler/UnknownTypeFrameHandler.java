package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 未知类型数据帧处理器
 * <p>
 * 在 {@link VehicleFrameHandleStrategy} 注册时，
 * 一定要放到处理队列{@code VehicleFrameHandleStrategy.frameHandlerList} 的最后
 *
 * @author Jun
 * @date 2018-11-17 12:51
 */
@Slf4j
@FrameProcessor
public class UnknownTypeFrameHandler implements FrameHandler {


    /**
     * 永远返回 {@code true}
     *
     * @param frame 数据帧
     * @return
     */
    @Override
    public boolean support(BaseFrame frame) {
        return true;
    }


    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {

    }
}
