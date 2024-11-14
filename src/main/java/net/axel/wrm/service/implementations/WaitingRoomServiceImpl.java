package net.axel.wrm.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.mapper.WaitingRoomMapper;
import net.axel.wrm.repository.WaitingRoomRepository;
import net.axel.wrm.service.WaitingRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class WaitingRoomServiceImpl implements WaitingRoomService {

    private final WaitingRoomRepository repository;
    private final WaitingRoomMapper mapper;

    @Override
    public List<WaitingRoomResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public WaitingRoomResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("WaitingRoom not found with id : " + id));
    }

    @Override
    public WaitingRoomResponseDTO create(WaitingRoomRequestDTO dto) {
        return null;
    }

    @Override
    public WaitingRoomResponseDTO update(Long id, WaitingRoomRequestDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
