package org.grpcvsrest.restvoting.repo;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VotingRepoTest {

    public static final Vote VOTE = new Vote("user_id", 115, "Pokemon");
    private VotingRepo votingRepo = new VotingRepo();

    @Before
    public void clear() {
        votingRepo.clear();
    }

    @Test
    public void testSaveFindClear() {
        // when
        Vote notFound = votingRepo.find("user_id", 115);
        // then
        assertThat(notFound).isNull();

        // and when
        votingRepo.save(VOTE);
        Vote found = votingRepo.find("user_id", 115);
        // then
        assertThat(found).isEqualTo(VOTE);

        // and when
        votingRepo.clear();
        notFound = votingRepo.find("user_id", 115);
        // then
        assertThat(notFound).isNull();

    }

}