package net.axel.wrm.domain.dtos.visit;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record VisitRequestDTO(
        Integer priority,
        Duration ept,
        @NotNull Long visitorId,
        @NotNull Long waitingRoomId
) {
}
