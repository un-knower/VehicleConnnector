package com.jmev.VehicleConnector.netty.codec;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.stereotype.Component;

/**
 * 封包处理
 *
 * @author Jun
 * @date 2018-11-17 16:40
 */
@Component
@ChannelHandler.Sharable
public class VehicleFrameEncoder extends MessageToByteEncoder<BaseFrame> {


    @Override
    protected void encode(ChannelHandlerContext ctx, BaseFrame msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.getOriginFrameByte());
    }
}
