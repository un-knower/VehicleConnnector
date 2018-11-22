package com.jmev.VehicleConnector.netty.handler;

import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 车辆数据帧处理策略类
 *
 * @author Jun
 * @date 2018-11-17 12:05
 */
@Component
public class VehicleFrameHandleStrategy {

    /**
     * 数据帧处理器队列
     */
    private final LinkedList<FrameHandler> frameHandlerList = new LinkedList<>();

    /**
     * 注册数据帧处理队列，队列顺序有特殊定义（主要是开头和结尾的处理器顺序固定，其它处理器的顺序不固定），不能随意更改
     */
    @Autowired
    public VehicleFrameHandleStrategy(List<FrameHandler> handlers) {
        //获取开头和结尾的处理器
        FrameHandler head = handlers
                .stream()
                .filter(e -> e instanceof IllegalFrameHandler)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("找不到废除数据包处理器,IllegalFrameHandler必须存在"));

        FrameHandler tail = handlers
                .stream()
                .filter(e -> e instanceof UnknownTypeFrameHandler)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("找不到未知数据帧处理器,UnknownFrameHandler必须存在"));

        //过滤掉开头和结尾的处理器
        List<FrameHandler> handlerList = handlers
                .stream()
                .filter(e -> !((e instanceof IllegalFrameHandler) || (e instanceof UnknownTypeFrameHandler)))
                .collect(Collectors.toList());

        //置入处理器
        frameHandlerList.addFirst(head);
        frameHandlerList.addAll(handlerList);
        frameHandlerList.addLast(tail);
    }

    /**
     * 调用上行数据帧处理器进行处理
     *
     * @param ctx   {@link ChannelHandlerContext}
     * @param frame 上行数据帧
     */
    public void process(final ChannelHandlerContext ctx, final BaseFrame frame) {
        for (FrameHandler handler : frameHandlerList) {
            if (handler.support(frame)) {
                handler.process(ctx, frame);
                break;
            }
        }
    }
}
