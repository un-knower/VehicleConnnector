package com.jmev.VehicleConnector.netty.codec;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.ReferenceCountUtil;

/**
 * 数据帧解码器
 *
 * @author Jun
 * @date 2018-11-16 13:15
 */
public final class VehicleFrameDecoder extends LengthFieldBasedFrameDecoder {

    public VehicleFrameDecoder(int maxFrameSize){
        super(maxFrameSize, 22, 2, 1, 0);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

        return upstreamFrameAssembly(frame);
    }

    /**
     * 上行数据帧组装
     *
     * @param frame netty解包后的数据
     * @return
     */
    private BaseFrame upstreamFrameAssembly(ByteBuf frame){
        //建立上行数据帧对象，开始解析
        BaseFrame baseFrame = new BaseFrame();

        //获取数据单元长度
        /*int len = frame.getUnsignedShort(22);
        baseFrame.setDataUnitLength(len);*/

        //原始数据帧
        byte[] originFrame = new byte[frame.readableBytes()];
        frame.getBytes(0, originFrame);
        baseFrame.setOriginFrameByte(originFrame);

        //起始符
        byte[] framePrefix = new byte[2];
        frame.readBytes(framePrefix);
        baseFrame.setFramePrefixByte(framePrefix);

        //命令标示
        baseFrame.setCommandSymbolByte(frame.readByte());

        //应答标示
        baseFrame.setResponseSymbolByte(frame.readByte());

        //车辆VIN
        byte[] vin = new byte[17];
        frame.readBytes(vin);
        baseFrame.setVinByte(vin);

        //数据加密方式
        baseFrame.setEncryptionTypeByte(frame.readByte());

        //数据单元长度
        byte[] dataUnitLength = new byte[2];
        frame.readBytes(dataUnitLength);
        baseFrame.setDataUnitLengthByte(dataUnitLength);

        //数据单元
        byte[] dataUnit = new byte[baseFrame.getDataUnitLength()];
        frame.readBytes(dataUnit);
        baseFrame.setDataUnitByte(dataUnit);

        //校验码
        baseFrame.setBccCodeByte(frame.readByte());

        //释放ByteBuf,防止内存泄露
        ReferenceCountUtil.release(frame);

        return baseFrame;
    }
}
