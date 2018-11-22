package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;

/**
 * 定义数据帧处理方法
 *
 * @author Jun
 * @date 2018-11-17 12:09
 */
public interface FrameHandler {

    /**
     * 判断支持处理此类型数据帧
     *
     * @param frame 数据帧
     * @return true 处理器支持处理此数据帧时
     */
    boolean support(BaseFrame frame);

    /**
     * 处理方法
     *
     * @param ctx   @see ChannelHandlerContext
     * @param frame 解包后的数据
     */
    void process(ChannelHandlerContext ctx, BaseFrame frame);
}
