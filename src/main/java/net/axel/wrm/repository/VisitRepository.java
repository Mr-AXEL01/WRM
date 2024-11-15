package net.axel.wrm.repository;

import net.axel.wrm.domain.embeddeds.VisitKey;
import net.axel.wrm.domain.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitKey> {
}
