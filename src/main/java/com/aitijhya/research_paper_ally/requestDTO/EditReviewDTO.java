package com.aitijhya.research_paper_ally.requestDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditReviewDTO(
        @NotNull Long reviewId,
        @NotNull Long reviewerId,
        @NotNull Long paperId,
        @NotBlank String newComment,
        @NotNull @Min(1) @Max(5) Integer newRating
) {
}
