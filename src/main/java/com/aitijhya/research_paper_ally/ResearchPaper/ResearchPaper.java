package com.aitijhya.research_paper_ally.ResearchPaper;

import java.util.ArrayList;
import java.util.List;

import com.aitijhya.research_paper_ally.Section.Section;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
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
    @OneToMany
    @JoinColumn(name = "paper-id")
    @OrderColumn(name = "section-order")
    private List<Section> sections = new ArrayList<>();



}
