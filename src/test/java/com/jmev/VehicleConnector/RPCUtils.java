package com.jmev.VehicleConnector;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jun
 * @date 2018-11-26 19:04
 */
@Slf4j
public class RPCUtils {

    @Test
    public void httpAsync() throws InterruptedException {
        String[] s = {"Hello","World"};

        String s1 = "hello";
        String s2 = "world";

            Stream.of(s2, s1)
                .map(e -> e.split(""))
                .flatMap(Stream::of)
                .distinct()
                .forEach(System.out::println);

        List<String> collect = Stream.of(s)
                .map(str -> str.split(""))
                .flatMap(Stream::of)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 异步HTTP请求
     *
     * @param uri 接口地址
     * @param headers 请求头
     * @param param 请求参数
     */
    @SuppressWarnings("unchecked")
    public static <T> void post(String uri, Map<String, String> headers, T param) {
        Assert.hasText(uri, "请求url不能为空");

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();

        final HttpPost httpPost = new HttpPost(uri);

        //请求头处理
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(httpPost::setHeader);
        }

        //请求参数处理
        if (param instanceof String) {
            NStringEntity nStringEntity = new NStringEntity((String) param, Charset.forName("utf8"));

            httpPost.setEntity(nStringEntity);
        } else if (param instanceof Map && !CollectionUtils.isEmpty((Collection<?>) param)) {
            Map<String, String> params = (Map<String, String>) param;

            List<NameValuePair> nvps = new ArrayList<>();

            params.forEach((k, v) -> nvps.add(new BasicNameValuePair(k, v)));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("utf8")));
        } else {
            throw new IllegalArgumentException("不支持的参数类型");
        }


        httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    log.info(EntityUtils.toString(httpResponse.getEntity()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception e) {
                log.info("请求失败");
            }

            @Override
            public void cancelled() {
                log.info("请求被取消");
            }
        });
    }
}