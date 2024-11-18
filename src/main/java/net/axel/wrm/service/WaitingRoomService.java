package net.axel.wrm.service;

import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomStatisticsDTO;

import java.util.List;

public interface WaitingRoomService {

    List<WaitingRoomResponseDTO> getAll(int page, int size);

    WaitingRoomResponseDTO getById(Long id);

    WaitingRoomResponseDTO create(WaitingRoomRequestDTO dto);

    WaitingRoomResponseDTO update(Long id, WaitingRoomRequestDTO dto);

    void delete(Long id);

    WaitingRoomStatisticsDTO generateStatics(Long id);
}
