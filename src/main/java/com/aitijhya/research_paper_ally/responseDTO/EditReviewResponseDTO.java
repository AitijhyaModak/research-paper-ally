package com.aitijhya.research_paper_ally.responseDTO;

import java.time.LocalDateTime;

public record EditReviewResponseDTO(
        Long reviewId,
        Long reviewerId,
        Long paperId,
        Integer rating,
        String comments,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
