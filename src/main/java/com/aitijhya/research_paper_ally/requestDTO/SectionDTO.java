package com.aitijhya.research_paper_ally.requestDTO;

import jakarta.validation.constraints.NotBlank;

public record SectionDTO(
        @NotBlank String title,
        @NotBlank String content) {
}
