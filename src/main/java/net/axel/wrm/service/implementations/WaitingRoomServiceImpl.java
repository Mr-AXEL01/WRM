package net.axel.wrm.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.config.applicationProprtiesConfig.WaitingRoomConfigProperties;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.domain.entities.WaitingRoom;
import net.axel.wrm.mapper.WaitingRoomMapper;
import net.axel.wrm.repository.WaitingRoomRepository;
import net.axel.wrm.service.WaitingRoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class WaitingRoomServiceImpl implements WaitingRoomService {

    private final WaitingRoomConfigProperties waitingRoomConfigProperties;
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
        WaitingRoom waitingRoom = mapper.toEntity(dto)
                .setDate(LocalDate.now())
                .setAlgorithm(getDefult(dto.algorithm(), waitingRoomConfigProperties.algorithm()))
                .setCapacity(getDefult(dto.capacity(), waitingRoomConfigProperties.capacity()))
                .setMode(getDefult(dto.mode(), waitingRoomConfigProperties.mode()));

        WaitingRoom savedWaitingRoom = repository.save(waitingRoom);

        return mapper.toResponseDto(savedWaitingRoom);
    }

    @Override
    public WaitingRoomResponseDTO update(Long id, WaitingRoomRequestDTO dto) {
        WaitingRoom waitingRoom = mapper.toEntityFromResponseDto(getById(id))
                .setDate(dto.date())
                .setAlgorithm(getDefult(dto.algorithm(), waitingRoomConfigProperties.algorithm()))
                .setCapacity(getDefult(dto.capacity(), waitingRoomConfigProperties.capacity()))
                .setMode(getDefult(dto.mode(), waitingRoomConfigProperties.mode()));
        WaitingRoom updatedWaitingRoom = repository.save(waitingRoom);
        return mapper.toResponseDto(updatedWaitingRoom);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private <T> T getDefult(T v, T dv) {
        return v != null ? v : dv;
    }

}
