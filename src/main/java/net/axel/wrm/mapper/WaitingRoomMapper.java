package net.axel.wrm.mapper;

import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.domain.entities.WaitingRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WaitingRoomMapper extends BaseMapper<WaitingRoom, WaitingRoomResponseDTO, WaitingRoomRequestDTO> {
}
