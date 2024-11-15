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
    public ResponseEntity<List<VisitResponseDTO>> findAll() {
        List<VisitResponseDTO> visits = service.getAll();
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{waiting-room-id}/{visitor-id}")
    public ResponseEntity<VisitResponseDTO> findById(
            @PathVariable("waiting-room-id") Long waitingRoomId, @PathVariable("visitor-id") Long visitorId) {
        VisitResponseDTO waitingRoom = service.getById(new VisitKey(visitorId, waitingRoomId));
        return ResponseEntity.ok(waitingRoom);
    }

    @PostMapping
    public ResponseEntity<VisitResponseDTO> create(
            @RequestBody @Valid VisitRequestDTO dto) {
        VisitResponseDTO createdWaitingRoom = service.create(dto);
        return new ResponseEntity<>(createdWaitingRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{waiting-room-id}/{visitor-id}")
    public ResponseEntity<VisitResponseDTO> update(
            @PathVariable("waiting-room-id") Long waitingRoomId, @PathVariable("visitor-id") Long visitorId,
            @RequestBody @Valid VisitUpdateRequestDTO dto) {
        VisitResponseDTO updatedWaitingRoom = service.update(new VisitKey(visitorId, waitingRoomId), dto);
        return ResponseEntity.ok(updatedWaitingRoom);
    }

    @DeleteMapping("/{waiting-room-id}/{visitor-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("waiting-room-id") Long waitingRoomId, @PathVariable("visitor-id") Long visitorId) {
        service.delete(new VisitKey(visitorId, waitingRoomId));
        return ResponseEntity.noContent().build();
    }
}
