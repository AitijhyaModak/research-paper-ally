package com.aitijhya.research_paper_ally.DTO;

import jakarta.validation.constraints.NotBlank;

public record SectionDTO(
    @NotBlank String title,
    @NotBlank String content
) {}
