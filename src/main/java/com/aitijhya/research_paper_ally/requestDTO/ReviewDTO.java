package com.aitijhya.research_paper_ally.requestDTO;

import java.time.LocalDateTime;

public record ReviewDTO(
    UserDTO reviewer,
    Integer rating,
    String comments,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt) {
}
