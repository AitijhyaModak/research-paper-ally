package com.aitijhya.research_paper_ally.requestDTO;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ResearchPaperDTO(
      @NotBlank String title,
      @NotBlank String abstractText,
      @NotNull List<Long> citationIds,
      @NotEmpty List<SectionDTO> sections,
      @NotEmpty List<AuthorContributionDTO> authorContributions) {
}
