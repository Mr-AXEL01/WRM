package net.axel.wrm.domain.dtos.visitor;

import jakarta.validation.constraints.NotBlank;

public record VisitorRequestDTO(
        @NotBlank String firstName,
        @NotBlank String lastName
) {
}
