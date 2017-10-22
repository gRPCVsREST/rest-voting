package org.grpcvsrest.restvoting.service;

import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LeaderboardService {

    private final String leaderboardUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public LeaderboardService(@Value("${leaderboard.url}") String leaderboardUrl, RestTemplate restTemplate) {
        this.leaderboardUrl = leaderboardUrl;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "doNothing", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1000")
    })
    public String sendToLeaderboard(String username, String category, boolean guessed) {
        restTemplate.put(
                leaderboardUrl+"/leaderboard/vote/{category}/{user_id}/{guessed}",
                null,
                ImmutableMap.of(
                        "user_id", username,
                        "category", category,
                        "guessed", guessed
                ));
        return null;
    }

    public String doNothing(String username, String category, boolean guessed){
        return null;
    }


}
