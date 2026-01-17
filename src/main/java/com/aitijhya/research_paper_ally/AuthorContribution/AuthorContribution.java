package com.aitijhya.research_paper_ally.AuthorContribution;

import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;
import com.aitijhya.research_paper_ally.User.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class AuthorContribution {
    @EmbeddedId
    private AuthorContributionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user-id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("paperId")
    @JoinColumn(name = "paper-id")
    private ResearchPaper paper;

    private Double contributionPercentage;
}
