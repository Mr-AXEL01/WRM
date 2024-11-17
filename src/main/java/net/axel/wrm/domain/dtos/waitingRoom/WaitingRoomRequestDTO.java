package net.axel.wrm.domain.dtos.waitingRoom;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.enums.Mode;

import java.time.LocalDate;
import java.util.Date;

public record WaitingRoomRequestDTO(
        String algorithm,
        Integer capacity,
        Mode mode
) {
}
