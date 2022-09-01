package com.mth.consumer.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @description:
 * @author: mth
 * @date: 2022/8/30
 **/
@RestController
public class ConsumerController {
    private final String SERVICE_URL = "http://33091f9t08.zicp.fun:21469/api";
    private final String SERVICE_URL1 = "http://33091f9t08.zicp.fun:42380/api";
    private final String STF_URL = "http://2imtpy.natappfree.cc";
    private final String CHL_URL = "http://d4pcqk.natappfree.cc/api";
    private final String SYK_URL = "http://uqc82x.natappfree.cc/api";

    @Resource
    private RestTemplate restTemplate;

    private WebClient webClient1 = WebClient.builder().baseUrl(STF_URL).build();
    private WebClient webClient2 = WebClient.builder().baseUrl(CHL_URL).build();
    private WebClient webClient3 = WebClient.builder().baseUrl(SYK_URL).build();

    @GetMapping("/consumer")
    public String httpClientTest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/books");
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpClient.execute(httpGet);
//            CloseableHttpResponse response = httpClient.execute(httpGet)/;
            // 判断状态码
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(result);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "请求成功" + result;
    }

    @GetMapping("/consumer1")
    public String restTemplateTest() {
        System.out.println(restTemplate.getForObject(SERVICE_URL1 + "/me", String.class));
        return restTemplate.getForObject(SERVICE_URL1 + "/me", String.class);
    }

    @GetMapping("/consumer2")
    public String webClientTest() {
        Mono<String> mono = webClient1.get().uri("/test1").retrieve().bodyToMono(String.class);
        mono.subscribe(result -> {
            System.out.println(result);
        });
        return "请求成功" + mono.block();
    }

    @GetMapping("/consumer3")
    public String webClientTest1() {
        Mono<String> mono = webClient2.get().uri("/getbook?bookName=aaa&number=123").retrieve().bodyToMono(String.class);
        mono.subscribe(result -> {
            System.out.println(result);
        });
        return "请求成功" + mono.block();
    }

    @GetMapping("/consumer4")
    public String webClientTest2() {
        Mono<String> mono = webClient3.get().uri("/searching").retrieve().bodyToMono(String.class);
        mono.subscribe(result -> {
            System.out.println(result);
        });
        return "请求成功" + mono.block();
    }
}
