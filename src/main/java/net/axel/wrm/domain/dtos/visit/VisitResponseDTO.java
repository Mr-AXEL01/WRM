package net.axel.wrm.domain.dtos.visit;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.dtos.visitor.EmbeddedVisitorDTO;
import net.axel.wrm.domain.dtos.waitingRoom.EmbeddedWaitingRoomDTO;
import net.axel.wrm.domain.embeddeds.VisitKey;
import net.axel.wrm.domain.enums.Status;

import java.time.Duration;
import java.time.LocalDateTime;

public record VisitResponseDTO(
        @NotNull VisitKey id,
        @NotNull LocalDateTime arrivalTime,
        LocalDateTime startTime,
        LocalDateTime endTime,
        @NotNull Status status,
        Duration ept,
        EmbeddedVisitorDTO visitor,
        EmbeddedWaitingRoomDTO waitingRoom
) {
}
