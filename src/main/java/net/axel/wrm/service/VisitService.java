package net.axel.wrm.service;

import net.axel.wrm.domain.dtos.visit.VisitRequestDTO;
import net.axel.wrm.domain.dtos.visit.VisitResponseDTO;
import net.axel.wrm.domain.dtos.visit.VisitUpdateRequestDTO;
import net.axel.wrm.domain.embeddeds.VisitKey;

import java.util.List;

public interface VisitService {
    List<VisitResponseDTO> getAll();

    VisitResponseDTO getById(VisitKey id);

    List<VisitResponseDTO> schedule(Long waitingRoomId);

    VisitResponseDTO create(VisitRequestDTO dto);

    VisitResponseDTO startVisit(VisitKey id);

    VisitResponseDTO update(VisitKey id, VisitUpdateRequestDTO dto);

    void delete(VisitKey id);
}
