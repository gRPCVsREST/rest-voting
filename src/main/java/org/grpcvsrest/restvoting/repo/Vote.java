package org.grpcvsrest.restvoting.repo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Vote {

    private final String userId;
    private final int itemId;
    private final String votedCategory;

    public Vote(String userId, int itemId, String votedCategory) {
        this.userId = userId;
        this.itemId = itemId;
        this.votedCategory = votedCategory;
    }

    public String getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getVotedCategory() {
        return votedCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return itemId == vote.itemId &&
                Objects.equal(userId, vote.userId) &&
                Objects.equal(votedCategory, vote.votedCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, itemId, votedCategory);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("itemId", itemId)
                .add("votedCategory", votedCategory)
                .toString();
    }

}
