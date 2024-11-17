package net.axel.wrm.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.axel.wrm.domain.embeddeds.VisitKey;
import net.axel.wrm.domain.enums.Status;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Visit implements Serializable {
    @EmbeddedId
    private VisitKey id;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "ept") //stands for the => Estimated Processing Time
    private Duration ept;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("visitorId")
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("waitingRoomId")
    @JoinColumn(name = "waiting_room_id")
    private WaitingRoom waitingRoom;

    public Visit(VisitKey id, LocalDateTime arrivalTime, Status status, Integer priority, Duration ept, Visitor visitor, WaitingRoom waitingRoom) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.priority = priority;
        this.ept = ept;
        this.visitor = visitor;
        this.waitingRoom = waitingRoom;
    }
}
