package com.jmev.VehicleConnector.netty;

import com.jmev.VehicleConnector.netty.codec.VehicleFrameDecoder;
import com.jmev.VehicleConnector.netty.codec.VehicleFrameEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * netty服务启动类
 *
 * @author Jun
 * @date 2018-11-16 12:54
 */
@Setter
@Component
@ConfigurationProperties(prefix = "netty.server")
public final class NettyServerInitializer {
    //@formatter:off

    /** 最大数据帧，<b>10KB</b> */
    private static final int MAX_FRAME_LENGTH = 1024 * 10;

    /** 服务器绑定端口 */
    private int port;

    /** 服务器绑定ip */
    private String ip;

    /** 心跳超时，单位秒 */
    private int heartBeatReadTimeout;

    /** 业务线程池大小 */
    private int businessThreadSize;

    /** 业务处理器 */
    @Autowired
    private VehicleBusinessHandler vehicleBusinessHandler;

    /** 封包处理器 */
    @Autowired
    private VehicleFrameEncoder vehicleFrameEncoder;


    /**
     * 服务启动
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        //业务执行线程池
        final EventExecutorGroup eventExecutors = new DefaultEventExecutorGroup(businessThreadSize,
                new DefaultThreadFactory("BusinessEventExecutor"));
        try {
            ServerBootstrap b = new ServerBootstrap();

            b
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            pipeline.addLast(vehicleFrameEncoder);
                            pipeline.addLast(new VehicleFrameDecoder(MAX_FRAME_LENGTH));
                            pipeline.addLast(new ReadTimeoutHandler(heartBeatReadTimeout));
                            pipeline.addLast(eventExecutors,vehicleBusinessHandler);
                        }
                    });

            Channel channel = b.bind(ip, port).sync().channel();

            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
