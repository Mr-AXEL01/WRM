package net.axel.wrm.mapper;

import net.axel.wrm.domain.dtos.visit.VisitRequestDTO;
import net.axel.wrm.domain.dtos.visit.VisitResponseDTO;
import net.axel.wrm.domain.entities.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper extends BaseMapper<Visit, VisitResponseDTO, VisitRequestDTO> {
}
