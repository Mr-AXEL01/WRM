package net.axel.wrm.repository;

import net.axel.wrm.domain.embeddeds.VisitKey;
import net.axel.wrm.domain.entities.Visit;
import net.axel.wrm.domain.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitKey> {

    List<Visit> findVisitsByWaitingRoomIdAndStatus(Long waitingRoomId, Status status);

    Page<Visit> findAll(Pageable pageable);
}
