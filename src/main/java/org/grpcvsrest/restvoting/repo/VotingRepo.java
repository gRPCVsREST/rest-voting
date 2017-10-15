package org.grpcvsrest.restvoting.repo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class VotingRepo {

    private final ConcurrentHashMap<VoteId, Vote> recordedVotes = new ConcurrentHashMap<>();

    public void save(Vote vote) {
        recordedVotes.put(new VoteId(vote.getUserId(), vote.getItemId()), vote);
    }

    public Vote find(String userId, int itemId) {
        return recordedVotes.get(new VoteId(userId, itemId));
    }

    public void clear() {
        recordedVotes.clear();
    }

    private static class VoteId {
        private final String userId;
        private final int itemId;

        public VoteId(String userId, int itemId) {
            this.userId = userId;
            this.itemId = itemId;
        }

        public String getUserId() {
            return userId;
        }

        public int getItemId() {
            return itemId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VoteId voteId = (VoteId) o;
            return itemId == voteId.itemId &&
                    Objects.equal(userId, voteId.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(userId, itemId);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("userId", userId)
                    .add("itemId", itemId)
                    .toString();
        }
    }

}
