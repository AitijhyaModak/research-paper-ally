package com.aitijhya.research_paper_ally.requestDTO;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record ResearchPaperDTO(
    @NotBlank String title,
    @NotBlank String abstractText,
    @NotBlank List<Long> citationIds,
    @NotBlank List<SectionDTO> sections,
    @NotBlank List<AuthorContributionDTO> authorContributions
) {}
