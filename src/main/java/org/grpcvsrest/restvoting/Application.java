package org.grpcvsrest.restvoting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.sleuth.SpanAdjuster;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
public class Application {

    @Bean
    public SpanAdjuster customSpanAdjuster(@Value("${spring.application.name}") String appName) {
        return span -> span.toBuilder().name("#" + appName + "/" + span.getName().replace("http:/", "")).build();
    }


    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(5000);
        httpRequestFactory.setConnectTimeout(500);
        httpRequestFactory.setReadTimeout(500);

        return new RestTemplate(httpRequestFactory);
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
