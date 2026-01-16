package com.aitijhya.research_paper_ally.Section;

import java.util.HashSet;
import java.util.Set;

import com.aitijhya.research_paper_ally.ResearchPaper.ResearchPaper;

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
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper-id", insertable = false, updatable = false)
    private ResearchPaper paper;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "paper-citations",
        joinColumns = @JoinColumn(name = "paper-id"),
        inverseJoinColumns = @JoinColumn(name = "cited-paper-id")
    )
    private Set<ResearchPaper> st = new HashSet<>();

    @EqualsAndHashCode.Include
    @Column(nullable = false, updatable = false)
    private String uuid;
}
