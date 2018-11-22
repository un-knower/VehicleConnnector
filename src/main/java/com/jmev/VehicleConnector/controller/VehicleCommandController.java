package com.jmev.VehicleConnector.controller;

import com.alibaba.fastjson.JSONObject;
import com.jmev.VehicleConnector.entity.Response;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Optional;

/**
 * 车辆控制命令处理器
 *
 * @author Jun
 * @date 2018-11-16 12:29
 */
@RestController
@RequestMapping(value = "vehicleCommand", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VehicleCommandController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @RequestMapping("ping/{name}")
    public Response<String> test(@PathVariable String name) {
        redisTemplate.opsForValue().set("name", name, Duration.ofSeconds(5));

        return Response.success("pong");
    }


    @RequestMapping("redis")
    public Response<String> test1() {
        String name = redisTemplate.opsForValue().get("name");

        return Response.success(name);
    }


    @RequestMapping("kafka/{value}")
    public Response<String> test2(@PathVariable String value){
        kafkaTemplate.send("test", value);

        return Response.success(null);
    }


    @KafkaListener(topics = {"test"})
    public void receiver(ConsumerRecord<String, String> record) {
        String s = Optional.ofNullable(record)
                .map(ConsumerRecord::value)
                .orElse(null);

        System.out.println(s);
    }

    @GetMapping("mongo")
    public Response test3() {
        JSONObject json = new JSONObject();
        json.put("username", "123456");

        mongoTemplate.insert(Mono.just(json));

        return null;
    }
}
