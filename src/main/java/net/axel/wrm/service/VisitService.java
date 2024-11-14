package net.axel.wrm.service;

import net.axel.wrm.domain.dtos.visit.VisitRequestDTO;
import net.axel.wrm.domain.dtos.visit.VisitResponseDTO;

import java.util.List;

public interface VisitService {
    List<VisitResponseDTO> getAll();

    VisitResponseDTO getById(Long id);

    VisitResponseDTO create(VisitRequestDTO dto);

    VisitResponseDTO update(Long id, VisitRequestDTO dto);

    void delete(Long id);
}
