package net.axel.wrm.domain.embeddeds;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record VisitKey(
        @Column(name = "visitor_id") Long visitorId,
        @Column(name = "waiting_room_id") Long waitingRoomId
) {
}
