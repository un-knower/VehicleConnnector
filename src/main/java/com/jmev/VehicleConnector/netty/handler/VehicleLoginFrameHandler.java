package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.common.annotation.FrameProcessor;
import com.jmev.VehicleConnector.common.constant.FrameType;
import com.jmev.VehicleConnector.common.constant.ResponseSymbolType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import com.jmev.VehicleConnector.netty.entity.upstream.VehicleLoginUpFrame;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 车辆登录数据帧处理器
 *
 * @author Jun
 * @date 2018-11-17 12:58
 */
@Slf4j
@FrameProcessor
public class VehicleLoginFrameHandler implements FrameHandler {

    /**
     * 车辆登录数据帧标示
     */
    private final int VEHICLE_LOGIN = FrameType.VEHICLE_LOGIN.getValue();

    @Override
    public boolean support(BaseFrame frame) {
        return VEHICLE_LOGIN == frame.getCommandSymbol();
    }

    @Override
    public void process(final ChannelHandlerContext ctx, final BaseFrame frame) {
        //解析数据，获取登录数据帧
        VehicleLoginUpFrame vehicleLoginUpFrame = vehicleLoginUpFrameAssembly(frame);

        System.out.println(vehicleLoginUpFrame);

        ctx.writeAndFlush(frame.requestToResponse(ResponseSymbolType.SUCCESS));
    }

    /**
     * 登录数据帧组装，组装方式请参考具体文档
     *
     * @param frame
     * @return
     */
    private VehicleLoginUpFrame vehicleLoginUpFrameAssembly(final BaseFrame frame) {
        //建立用户登录数据帧对象，开始解析
        VehicleLoginUpFrame loginFrame = new VehicleLoginUpFrame();

        //保存原始数据帧
        loginFrame.setBaseFrame(frame);

        //数据单元
        byte[] data = frame.getDataUnitByte();

        //数据采集时间
        loginFrame.setTimestampByte(Arrays.copyOfRange(data, 0, 6));

        //登录流水号
        loginFrame.setLoginFlowNumByte(Arrays.copyOfRange(data, 6, 8));

        //iccic
        loginFrame.setIccidByte(Arrays.copyOfRange(data, 8, 28));

        //可充电储能子系统个数
        loginFrame.setBatteryNumByte(data[28]);

        //可充电储能系统编码长度
        loginFrame.setBatteryCodeLengthByte(data[29]);

        //可充电储能系统编码,只有当编码长度不为0时才需要解析
        //todo 当上传的数据存在多个子系统时，如何解析？
        if (loginFrame.getCessCodeLength() != 0) {
            byte[] bytes = Arrays.copyOfRange(data, 30,
                    loginFrame.getCessNum() * loginFrame.getCessCodeLength());
            loginFrame.setBatteryCodeByte(bytes);
        }

        return loginFrame;
    }

}
