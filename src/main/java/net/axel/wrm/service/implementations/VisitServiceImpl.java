package net.axel.wrm.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.config.applicationProprtiesConfig.VisitConfigProperties;
import net.axel.wrm.domain.dtos.visit.VisitRequestDTO;
import net.axel.wrm.domain.dtos.visit.VisitResponseDTO;
import net.axel.wrm.domain.dtos.visit.VisitUpdateRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.domain.entities.Visit;
import net.axel.wrm.domain.entities.Visitor;
import net.axel.wrm.domain.entities.WaitingRoom;
import net.axel.wrm.domain.enums.Status;
import net.axel.wrm.mapper.VisitMapper;
import net.axel.wrm.mapper.VisitorMapper;
import net.axel.wrm.mapper.WaitingRoomMapper;
import net.axel.wrm.repository.VisitRepository;
import net.axel.wrm.repository.VisitorRepository;
import net.axel.wrm.service.VisitService;
import net.axel.wrm.service.WaitingRoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository repository;
    private final VisitMapper mapper;
    private final VisitConfigProperties visitConfigProperties;
    private final WaitingRoomService waitingRoomService;
    private final WaitingRoomMapper waitingRoomMapper;
    private final VisitorRepository visitorRepository; //to change later to service
    private final VisitorMapper visitorMapper;


    @Override
    public List<VisitResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public VisitResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("visit not found with id : " + id));
    }

    @Override
    public VisitResponseDTO create(VisitRequestDTO dto) {
        Visitor visitor = visitorRepository.findById(dto.visitorId())
                .orElseThrow(() -> new RuntimeException("Visitor not found with id : " + dto.visitorId()));

        Visit visit = mapper.toEntity(dto)
                .setArrivalTime(LocalDateTime.now())
                .setStatus(Status.WAITING)
                .setPriority(dto.priority() != null ? dto.priority() : visitConfigProperties.priority())
                .setEpt(dto.ept() != null ? dto.ept() : visitConfigProperties.ept())
                .setVisitor(getVisitor(dto.visitorId()))
                .setWaitingRoom(getWaitingRoom(dto.waitingRoomId()));
        Visit savedVisit = repository.save(visit);
        return mapper.toResponseDto(savedVisit);
    }

    @Override
    public VisitResponseDTO update(Long id, VisitUpdateRequestDTO dto) {
        Visit visit = mapper.toEntityFromResponseDto(getById(id))
                .setArrivalTime(dto.arrivalTime())
                .setStatus(dto.status())
                .setEpt(dto.ept() != null ? dto.ept() : visitConfigProperties.ept())
                .setEpt(dto.ept() != null ? dto.ept() : visitConfigProperties.ept())
                .setVisitor(getVisitor(dto.visitorId()))
                .setWaitingRoom(getWaitingRoom(dto.waitingRoomId()));

        if (dto.startTime() != null) {
            visit.setStartTime(dto.startTime());
        }
        if (dto.endTime() != null) {
            visit.setEndTime(dto.endTime());
        }

        Visit updatedVisit = repository.save(visit);
        return mapper.toResponseDto(updatedVisit);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private WaitingRoom getWaitingRoom(Long waitingRoomId) {
        WaitingRoomResponseDTO waitingRoomResponse = waitingRoomService.getById(waitingRoomId);
        return waitingRoomMapper.toEntityFromResponseDto(waitingRoomResponse);
    }

    private Visitor getVisitor(Long visitorId) {
        return visitorRepository.findById(visitorId)
                .orElseThrow(() -> new RuntimeException("Visitor not found with id : " + visitorId));
    }
}
