package org.grpcvsrest.voting.service;

import org.grpcvsrest.voting.repo.Vote;
import org.grpcvsrest.voting.repo.VotingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {

    @Autowired
    private VotingRepo votingRepo;

    public void record(Vote vote) {
        votingRepo.save(vote);
    }
}
