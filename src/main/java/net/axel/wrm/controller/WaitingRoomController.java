package net.axel.wrm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomStatisticsDTO;
import net.axel.wrm.service.WaitingRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WaitingRoomController.CONTROLLER_PATH)

@RequiredArgsConstructor
public class WaitingRoomController {
    public final static String CONTROLLER_PATH = "/api/v1/waiting-rooms";

    private final WaitingRoomService service;

    @GetMapping
    public ResponseEntity<List<WaitingRoomResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        List<WaitingRoomResponseDTO> waitingRooms = service.getAll(page, size);
        return ResponseEntity.ok(waitingRooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaitingRoomResponseDTO> findById(
            @PathVariable("id") Long id) {
        WaitingRoomResponseDTO waitingRoom = service.getById(id);
        return ResponseEntity.ok(waitingRoom);
    }

    @PostMapping
    public ResponseEntity<WaitingRoomResponseDTO> create(
            @RequestBody @Valid WaitingRoomRequestDTO dto) {
        WaitingRoomResponseDTO createdWaitingRoom = service.create(dto);
        return new ResponseEntity<>(createdWaitingRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaitingRoomResponseDTO> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid WaitingRoomRequestDTO dto) {
        WaitingRoomResponseDTO updatedWaitingRoom = service.update(id, dto);
        return ResponseEntity.ok(updatedWaitingRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/statistics")
    public ResponseEntity<WaitingRoomStatisticsDTO> getStatistics(@PathVariable("id") Long id) {
        WaitingRoomStatisticsDTO statistics = service.generateStatics(id);
        return ResponseEntity.ok(statistics);
    }
}
