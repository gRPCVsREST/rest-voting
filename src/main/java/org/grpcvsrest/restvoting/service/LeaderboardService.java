package org.grpcvsrest.restvoting.service;

import com.google.common.collect.ImmutableMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "doNothing")   
    public void sendToLeaderboard(String username, String category, boolean guessed) {
        restTemplate.put(
                leaderboardUrl+"/leaderboard/vote/{category}/{user_id}/{guessed}",
                null,
                ImmutableMap.of(
                        "user_id", username,
                        "category", category,
                        "guessed", guessed
                ));
    }

    public void doNothing() {}

}
