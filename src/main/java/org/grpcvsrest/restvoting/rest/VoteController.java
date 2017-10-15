package org.grpcvsrest.restvoting.rest;

import org.grpcvsrest.restvoting.repo.Vote;
import org.grpcvsrest.restvoting.service.VotingService;
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
