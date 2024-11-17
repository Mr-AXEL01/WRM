package net.axel.wrm.exception;

import java.time.LocalDate;

public record ErrorResponse(
        Integer status,
        LocalDate date,
        String message,
        String description
) {
}
