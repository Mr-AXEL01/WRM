package net.axel.wrm.domain.dtos.waitingRoom;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.enums.Mode;

import java.util.Date;

public record EmbeddedWaitingRoomDTO(
        @NotNull Long id,
        @NotNull Date date,
        String algorithm,
        Integer capacity,
        @NotNull Mode mode
) {
}
