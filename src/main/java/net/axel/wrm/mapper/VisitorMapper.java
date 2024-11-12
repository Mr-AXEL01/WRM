package net.axel.wrm.mapper;

import net.axel.wrm.domain.dtos.visitor.VisitorRequestDTO;
import net.axel.wrm.domain.dtos.visitor.VisitorResponseDTO;
import net.axel.wrm.domain.entities.Visitor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorMapper extends BaseMapper<Visitor, VisitorResponseDTO, VisitorRequestDTO> {
}
