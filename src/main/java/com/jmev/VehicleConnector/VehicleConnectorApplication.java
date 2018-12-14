package com.jmev.VehicleConnector;

import com.jmev.VehicleConnector.netty.NettyServerInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class VehicleConnectorApplication {

	public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(VehicleConnectorApplication.class, args);

        NettyServerInitializer server = ctx.getBean(NettyServerInitializer.class);
        server.start();
	}
}
