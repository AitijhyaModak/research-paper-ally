package com.aitijhya.research_paper_ally.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthorContributionDTO(
    @NotBlank String userEmail,
    @NotBlank Double contributionPercentage
) {}
