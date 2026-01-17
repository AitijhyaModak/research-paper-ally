package com.aitijhya.research_paper_ally.DTO;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    @NotBlank String firstName,
    String middleName,
    @NotBlank String lastName,
    @NotBlank String email
) {}