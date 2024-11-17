package net.axel.wrm.domain.dtos.waitingRoom;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.enums.Mode;

import java.time.LocalDate;

public record WaitingRoomStatisticsDTO(
        LocalDate date,
        String algorithm,
        Mode mode,
        Double averageWaitingTime,
        Integer visitorRotation
) {
}
