package net.axel.wrm.domain.dtos.visit;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.enums.Status;

import java.time.LocalDateTime;

public record visitRequestDTO(
        @NotNull LocalDateTime arrivalTime,
        @NotNull Status status,
        @NotNull Long visitorId,
        @NotNull Long waitingRoomId
) {
}
