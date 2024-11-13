package net.axel.wrm.service;

import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;

import java.util.List;

public interface WaitingRoomService {

    List<WaitingRoomResponseDTO> getAll();

    WaitingRoomResponseDTO getById(Long id);

    WaitingRoomResponseDTO create(WaitingRoomRequestDTO dto);

    WaitingRoomResponseDTO update(Long id, WaitingRoomRequestDTO dto);

    void delete(Long id);
}
