package com.jmev.VehicleConnector.common.config;

import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * @author Jun
 * @date 2018-11-21 11:05
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(){
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions
                .builder()
                .enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger.MOVED_REDIRECT,
                        ClusterTopologyRefreshOptions.RefreshTrigger.PERSISTENT_RECONNECTS)
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(30))
                .enablePeriodicRefresh()
                .build();

        ClusterClientOptions clientOptions = ClusterClientOptions
                .builder()
                .topologyRefreshOptions(topologyRefreshOptions)
                .build();

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration
                .builder()
                .clientOptions(clientOptions)
                .commandTimeout(Duration.ofSeconds(2))
                .build();

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        redisClusterConfiguration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());

        return new LettuceConnectionFactory(redisClusterConfiguration, clientConfiguration);
    }

    @Bean
    public ReactiveRedisTemplate<String,String> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory){
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, RedisSerializationContext.string());
    }
}
