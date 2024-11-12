package net.axel.wrm.repository;

import net.axel.wrm.domain.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
