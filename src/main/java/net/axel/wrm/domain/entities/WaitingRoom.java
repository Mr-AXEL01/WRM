package net.axel.wrm.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.axel.wrm.domain.enums.Mode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "waiting_rooms")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WaitingRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @NotBlank
    @Column(name = "algorithm")
    private String algorithm;

    @NotNull
    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    @OneToMany(mappedBy = "waitingRoom")
    private Set<Visit> visits;
}
