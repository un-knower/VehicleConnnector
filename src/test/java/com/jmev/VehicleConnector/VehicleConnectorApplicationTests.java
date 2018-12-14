package com.jmev.VehicleConnector;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j*/
public class VehicleConnectorApplicationTests {

    @Autowired
    private ReactiveRedisTemplate<String,String> reactiveRedisTemplate;


    @Test
    public void contextLoads() throws InterruptedException {
        System.out.println((byte)0xdc);
    }

}
