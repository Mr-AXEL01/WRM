package net.axel.wrm.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.axel.wrm.domain.enums.Mode;
import net.axel.wrm.domain.enums.PartTime;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "waiting_rooms")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaitingRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotBlank
    @Column(name = "algorithm")
    private String algorithm;

    @NotNull
    @Column(name = "capacity")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    @Enumerated(EnumType.STRING)
    private PartTime partTime;

    @OneToMany(mappedBy = "waitingRoom")
    private Set<Visit> visits;
}
