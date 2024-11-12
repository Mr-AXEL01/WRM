package net.axel.wrm.domain.dtos.visitor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.dtos.visit.EmbeddedVisitDTO;

import java.util.Set;

public record VisitorResponseDTO(
        @NotNull Long id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        Set<EmbeddedVisitDTO> visits
) {
}
