package com.jmev.VehicleConnector.netty;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import com.jmev.VehicleConnector.netty.handler.VehicleFrameHandleStrategy;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 上传报文的业务实现
 *
 * @author Jun
 * @date 2018-11-17 10:06
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public final class VehicleBusinessHandler extends SimpleChannelInboundHandler<BaseFrame> {

    /**
     * 用于保存车辆链接, {@code channels.size()} 即为活跃的车辆数目
     */
    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Autowired
    private VehicleFrameHandleStrategy vehicleFrameHandleStrategy;

    /**
     * TCP连接建立
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        log.info("channelId={},建立", id.asShortText());
        channels.add(ctx.channel());
    }

    /**
     * TCP连接断开
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelId id = ctx.channel().id();
        log.info("channelId={},断开", id.asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseFrame msg) throws Exception {
        vehicleFrameHandleStrategy.process(ctx, msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException) {

        } else {
            log.error(cause.getMessage(), cause);
        }
    }
}
