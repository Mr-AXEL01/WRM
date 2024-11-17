package net.axel.wrm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.domain.dtos.visit.VisitRequestDTO;
import net.axel.wrm.domain.dtos.visit.VisitResponseDTO;
import net.axel.wrm.domain.dtos.visit.VisitUpdateRequestDTO;
import net.axel.wrm.domain.embeddeds.VisitKey;
import net.axel.wrm.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VisitController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class VisitController {
    public final static String CONTROLLER_PATH = "/api/v1/visits";

    private final VisitService service;

    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        List<VisitResponseDTO> visits = service.getAll(page, size);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{waitingRoomId}/{visitorId}")
    public ResponseEntity<VisitResponseDTO> findById(
            @PathVariable("waitingRoomId") Long waitingRoomId, @PathVariable("visitorId") Long visitorId) {
        VisitResponseDTO waitingRoom = service.getById(new VisitKey(visitorId, waitingRoomId));
        return ResponseEntity.ok(waitingRoom);
    }

    @GetMapping("/{waitingRoomId}/schedule")
    public ResponseEntity<List<VisitResponseDTO>> scheduleVisits(@PathVariable("waitingRoomId") Long waitingRoomId) {
        List<VisitResponseDTO> scheduledVisits = service.schedule(waitingRoomId);
        return ResponseEntity.ok(scheduledVisits);
    }

    @PostMapping
    public ResponseEntity<VisitResponseDTO> create(
            @RequestBody @Valid VisitRequestDTO dto) {
        VisitResponseDTO createdWaitingRoom = service.create(dto);
        return new ResponseEntity<>(createdWaitingRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{waitingRoomId}/{visitorId}")
    public ResponseEntity<VisitResponseDTO> update(
            @PathVariable("waitingRoomId") Long waitingRoomId, @PathVariable("visitorId") Long visitorId,
            @RequestBody @Valid VisitUpdateRequestDTO dto) {
        VisitResponseDTO updatedWaitingRoom = service.update(new VisitKey(visitorId, waitingRoomId), dto);
        return ResponseEntity.ok(updatedWaitingRoom);
    }
    @PutMapping("/start/{waitingRoomId}/{visitorId}")
    public ResponseEntity<VisitResponseDTO> startVisit(
            @PathVariable("waitingRoomId") Long waitingRoomId, @PathVariable("visitorId") Long visitorId) {
        VisitResponseDTO updatedWaitingRoom = service.startVisit(new VisitKey(visitorId, waitingRoomId));
        return ResponseEntity.ok(updatedWaitingRoom);
    }

    @PutMapping("/complete/{waitingRoomId}/{visitorId}")
    public ResponseEntity<VisitResponseDTO> completeVisit(
            @PathVariable("waitingRoomId") Long waitingRoomId, @PathVariable("visitorId") Long visitorId) {
        VisitResponseDTO updatedWaitingRoom = service.completeVisit(new VisitKey(visitorId, waitingRoomId));
        return ResponseEntity.ok(updatedWaitingRoom);
    }

    @PutMapping("/cancel/{waitingRoomId}/{visitorId}")
    public ResponseEntity<VisitResponseDTO> cancelVisit(
            @PathVariable("waitingRoomId") Long waitingRoomId, @PathVariable("visitorId") Long visitorId) {
        VisitResponseDTO updatedWaitingRoom = service.cancelVisit(new VisitKey(visitorId, waitingRoomId));
        return ResponseEntity.ok(updatedWaitingRoom);
    }

    @DeleteMapping("/{waitingRoomId}/{visitorId}")
    public ResponseEntity<Void> delete(
            @PathVariable("waitingRoomId") Long waitingRoomId, @PathVariable("visitorId") Long visitorId) {
        service.delete(new VisitKey(visitorId, waitingRoomId));
        return ResponseEntity.noContent().build();
    }
}
