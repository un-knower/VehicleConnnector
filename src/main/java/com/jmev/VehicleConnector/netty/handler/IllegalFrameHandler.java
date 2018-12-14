package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于处理 {@link BaseFrame} 对象方法 {@code isAvailable()} 结果为
 * {@code false} 的数据帧
 *
 * @author Jun
 * @date 2018-11-17 12:42
 */
@Slf4j
@FrameProcessor
public class IllegalFrameHandler implements FrameHandler {

    @Override
    public boolean support(BaseFrame frame) {
        return frame.unavailable();
    }

    @Override
    public void process(ChannelHandlerContext ctx, BaseFrame frame) {
        log.info("vin={} 的车辆上传了非法数据帧", frame.getVin());
    }
}
