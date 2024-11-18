package net.axel.wrm.repository;

import net.axel.wrm.domain.entities.WaitingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingRoomRepository extends JpaRepository<WaitingRoom, Long> {
    Page<WaitingRoom> findAllBy(Pageable pageable);
}