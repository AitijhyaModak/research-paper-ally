package com.aitijhya.research_paper_ally.AuthorContribution;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class AuthorContributionId implements Serializable {
    private Long userId;
    private Long paperId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != getClass()) return false;
        AuthorContributionId that = (AuthorContributionId) o;
        return userId == that.userId && paperId == that.paperId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, paperId);
    }
}
