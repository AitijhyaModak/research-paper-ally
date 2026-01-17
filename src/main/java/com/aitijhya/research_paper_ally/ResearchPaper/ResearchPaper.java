package com.aitijhya.research_paper_ally.ResearchPaper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aitijhya.research_paper_ally.AuthorContribution.AuthorContribution;
import com.aitijhya.research_paper_ally.Review.Review;
import com.aitijhya.research_paper_ally.Section.Section;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ResearchPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String abstractText;

    @Version
    private Integer version;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper-id")
    @OrderColumn(name = "section-order")
    private List<Section> sections = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper-id")
    private List<AuthorContribution> authorContributions = new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "paper-citations",
        joinColumns = @JoinColumn(name = "paper-id"),
        inverseJoinColumns = @JoinColumn(name = "cited-paper-id")
    )
    private Set<ResearchPaper> citations = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper-id")
    private List<Review> reviews = new ArrayList<>();

    @Column(nullable = false, unique = true, updatable = false)
    @EqualsAndHashCode.Include
    private String uuid;
}
