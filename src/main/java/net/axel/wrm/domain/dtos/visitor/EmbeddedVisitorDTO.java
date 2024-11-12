package net.axel.wrm.domain.dtos.visitor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmbeddedVisitorDTO(
        @NotNull Long id,
        @NotBlank String firstName,
        @NotBlank String lastName
) {
}
