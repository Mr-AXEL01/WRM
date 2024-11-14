package net.axel.wrm.domain.dtos.visit;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.enums.Status;

import java.time.Duration;
import java.time.LocalDateTime;

public record VisitRequestDTO(
        @NotNull LocalDateTime arrivalTime,
        @NotNull Status status,
        Integer priority,
        Duration ept,
        @NotNull Long visitorId,
        @NotNull Long waitingRoomId
) {
}
