package net.axel.wrm.repository;

import net.axel.wrm.domain.entities.WaitingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingRoomRepository extends JpaRepository<WaitingRoom, Long> {
}