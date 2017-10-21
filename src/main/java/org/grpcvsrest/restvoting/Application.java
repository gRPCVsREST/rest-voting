package org.grpcvsrest.restvoting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

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
