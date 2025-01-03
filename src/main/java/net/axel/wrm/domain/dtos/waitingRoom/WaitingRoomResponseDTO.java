package net.axel.wrm.domain.dtos.waitingRoom;

import jakarta.validation.constraints.NotNull;
import net.axel.wrm.domain.dtos.visit.EmbeddedVisitDTO;
import net.axel.wrm.domain.enums.Mode;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record WaitingRoomResponseDTO(
        @NotNull Long id,
        @NotNull LocalDate date,
        String algorithm,
        Integer capacity,
        @NotNull Mode mode,
        Set<EmbeddedVisitDTO> visits
) {
}
