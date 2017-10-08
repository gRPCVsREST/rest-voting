package org.grpcvsrest.voting.rest;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.grpcvsrest.voting.repo.Vote;
import org.grpcvsrest.voting.service.VotingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class VoteControllerTest {

    public static final Vote EXPECTED_VOTE = new Vote("USER_ID", 115, "Pokemon");

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VotingService votingService;

    @Test
    public void testVote_Post() throws Exception {
        // when
        mockMvc.perform(
                post("/vote/USER_ID/115")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voteToRecord())
        ) // then
        .andExpect(status().is(200));

        Mockito.verify(votingService).record(EXPECTED_VOTE);
    }

    private String voteToRecord() throws IOException {
        return Resources.toString(Resources.getResource("vote_to_record.json"), Charsets.UTF_8);
    }


}