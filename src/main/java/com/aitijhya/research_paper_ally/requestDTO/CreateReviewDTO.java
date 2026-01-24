package com.aitijhya.research_paper_ally.requestDTO;

import jakarta.validation.constraints.*;

public record CreateReviewDTO(
    @NotNull Long reviewerId,
    @NotNull @Min(1) @Max(5) Integer rating,
    @NotBlank String comments,
    @NotNull Long paperId
) {
}
