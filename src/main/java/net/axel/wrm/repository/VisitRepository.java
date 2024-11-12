package net.axel.wrm.repository;

import net.axel.wrm.domain.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
