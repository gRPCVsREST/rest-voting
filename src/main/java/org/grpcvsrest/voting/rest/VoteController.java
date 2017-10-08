package org.grpcvsrest.voting.rest;

import org.grpcvsrest.voting.repo.Vote;
import org.grpcvsrest.voting.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {

    private final VotingService votingService;

    @Autowired
    public VoteController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping("/vote/{userId}/{itemId}")
    public void postVote(@RequestBody Vote vote) {
        votingService.record(vote);
    }
}
