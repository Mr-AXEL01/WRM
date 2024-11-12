package net.axel.wrm.domain.dtos.visit;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.embeddeds.VisitKey;
import net.axel.wrm.domain.enums.Status;

import java.time.LocalDateTime;

public record EmbeddedVisitDTO(
        @NotNull VisitKey id,
        @NotNull LocalDateTime arrivalTime,
        LocalDateTime startTime,
        LocalDateTime endTime,
        @NotNull Status status
) {
}
