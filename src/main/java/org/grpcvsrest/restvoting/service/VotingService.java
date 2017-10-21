package org.grpcvsrest.restvoting.service;

import com.google.common.collect.ImmutableMap;
import org.grpcvsrest.restvoting.repo.Vote;
import org.grpcvsrest.restvoting.repo.VotingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class VotingService {

    private final VotingRepo votingRepo;
    private final RestTemplate restTemplate;
    private final String aggregatorUrl;
    private final String leaderboardUrl;

    @Autowired
    public VotingService(
            VotingRepo votingRepo,
            RestTemplate restTemplate,
            @Value("${aggregator.url}") String aggregatorUrl,
            @Value("${leaderboard.url}") String leaderboardUrl
            ) {
        this.votingRepo = votingRepo;
        this.restTemplate = restTemplate;
        this.aggregatorUrl = aggregatorUrl;
        this.leaderboardUrl = leaderboardUrl;
    }

    public void record(Vote vote) {
        AggregatedContentResponse content = getContent(vote.getItemId());
        if (content != null) {
            votingRepo.save(vote);
            sendToLeaderboard(
                    vote.getUserId(),
                    vote.getVotedCategory(),
                    content.getType().equals(vote.getVotedCategory()));
        }
    }

    private void sendToLeaderboard(String username, String category, boolean guessed) {
        restTemplate.put(
                leaderboardUrl+"/leaderboard/vote/{category}/{user_id}/{guessed}",
                null,
                ImmutableMap.of(
                        "user_id", username,
                        "category", category,
                        "guessed", guessed
                ));
    }

    private AggregatedContentResponse getContent(int itemId) {
        try {
            return restTemplate.getForObject(aggregatorUrl+"/content/"+itemId, AggregatedContentResponse.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                return null;
            }
            throw e;
        }
    }
}
