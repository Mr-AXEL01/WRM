package net.axel.wrm.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.config.applicationProprtiesConfig.VisitConfigProperties;
import net.axel.wrm.domain.dtos.visit.VisitRequestDTO;
import net.axel.wrm.domain.dtos.visit.VisitResponseDTO;
import net.axel.wrm.domain.dtos.visit.VisitUpdateRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.domain.embeddeds.VisitKey;
import net.axel.wrm.domain.entities.Visit;
import net.axel.wrm.domain.entities.Visitor;
import net.axel.wrm.domain.entities.WaitingRoom;
import net.axel.wrm.domain.enums.Status;
import net.axel.wrm.mapper.VisitMapper;
import net.axel.wrm.mapper.VisitorMapper;
import net.axel.wrm.mapper.WaitingRoomMapper;
import net.axel.wrm.repository.VisitRepository;
import net.axel.wrm.repository.VisitorRepository;
import net.axel.wrm.service.ScheduleService;
import net.axel.wrm.service.VisitService;
import net.axel.wrm.service.WaitingRoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional

@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository repository;
    private final VisitMapper mapper;
    private final VisitConfigProperties visitConfigProperties;
    private final WaitingRoomService waitingRoomService;
    private final WaitingRoomMapper waitingRoomMapper;
    private final VisitorRepository visitorRepository;
    private final Map<String, ScheduleService> schedulingStrategies;

    @Override
    public List<VisitResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Visit> visitsPage = repository.findAll(pageable);

        return visitsPage.stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public VisitResponseDTO getById(VisitKey id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("visit not found with id : " + id));
    }

    @Override
    public List<VisitResponseDTO> schedule(Long waitingRoomId) {
        WaitingRoomResponseDTO waitingRoomResponse = waitingRoomService.getById(waitingRoomId);
        WaitingRoom waitingRoom = waitingRoomMapper.toEntityFromResponseDto(waitingRoomResponse);

        List<Visit> visits = repository.findVisitsByWaitingRoomIdAndStatus(waitingRoomId, Status.WAITING);
        ScheduleService strategy = schedulingStrategies.get(waitingRoom.getAlgorithm().toUpperCase());
        return strategy.schedule(visits)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public VisitResponseDTO create(VisitRequestDTO dto) {
        Visit visit = new Visit(new VisitKey(1L, 1L),
                LocalDateTime.now(),
                Status.WAITING,
                getDefult(dto.priority(), visitConfigProperties.priority()),
                getDefult(dto.ept(), visitConfigProperties.ept()),
                getVisitor(dto.visitorId()),
                getWaitingRoom(dto.waitingRoomId()));
        Visit savedVisit = repository.save(visit);
        return mapper.toResponseDto(savedVisit);
    }

    @Override
    public VisitResponseDTO startVisit(VisitKey id) {
        Visit visit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("visit not found with id : " + id));

        visit.setStartTime(LocalDateTime.now())
                .setStatus(Status.IN_PROGRESS);
        return mapper.toResponseDto(visit);
    }

    @Override
    public VisitResponseDTO completeVisit(VisitKey id) {
        Visit visit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("visit not found with id : " + id));

        visit.setEndTime(LocalDateTime.now())
                .setStatus(Status.FINISHED);
        return mapper.toResponseDto(visit);
    }

    @Override
    public VisitResponseDTO cancelVisit(VisitKey id) {
        Visit visit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("visit not found with id : " + id));

        visit.setStatus(Status.CANCELLED);
        return mapper.toResponseDto(visit);
    }

    @Override
    public VisitResponseDTO update(VisitKey id, VisitUpdateRequestDTO dto) {
        Visit visit = mapper.toEntityFromResponseDto(getById(id))
                .setArrivalTime(dto.arrivalTime())
                .setStatus(dto.status())
                .setPriority(getDefult(dto.priority(), visitConfigProperties.priority()))
                .setEpt(getDefult(dto.ept(), visitConfigProperties.ept()))
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
    public void delete(VisitKey id) {
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

    private <T> T getDefult(T v, T dv) {
        return v != null ? v : dv;
    }
}
