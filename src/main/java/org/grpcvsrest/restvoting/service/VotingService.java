package org.grpcvsrest.restvoting.service;

import org.grpcvsrest.restvoting.repo.Vote;
import org.grpcvsrest.restvoting.repo.VotingRepo;
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
