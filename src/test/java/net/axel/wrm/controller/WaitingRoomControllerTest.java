package net.axel.wrm.controller;

import lombok.RequiredArgsConstructor;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomRequestDTO;
import net.axel.wrm.domain.dtos.waitingRoom.WaitingRoomResponseDTO;
import net.axel.wrm.domain.entities.WaitingRoom;
import net.axel.wrm.domain.enums.Mode;
import net.axel.wrm.repository.WaitingRoomRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WaitingRoomControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WaitingRoomRepository repository;

    private Long waitingRoomId;

     @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        WaitingRoom waitingRoom = new WaitingRoom()
                .setDate(LocalDate.now())
                .setAlgorithm("FIFO")
                .setCapacity(100)
                .setMode(Mode.FULL_TIME);

        WaitingRoom savedRoom = repository.save(waitingRoom);
        waitingRoomId = savedRoom.getId();
    }

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void shouldFindAllWaitingRooms() {
        ResponseEntity<WaitingRoomResponseDTO[]> response = restTemplate.getForEntity("/api/v1/waiting-rooms", WaitingRoomResponseDTO[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    @Test
    void shouldFindWaitingRoomWhenValidWaitingRoomId() {
        ResponseEntity<WaitingRoomResponseDTO> response = restTemplate.getForEntity("/api/v1/waiting-rooms/"+waitingRoomId , WaitingRoomResponseDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().capacity()).isEqualTo(100);
    }

    @Test
    void shouldCreateNewWaitingRoom() {
        WaitingRoomRequestDTO requestDTO = new WaitingRoomRequestDTO(
                "FIFO", 12, Mode.PART_TIME);

        ResponseEntity<WaitingRoomResponseDTO> response = restTemplate.postForEntity("/api/v1/waiting-rooms", requestDTO, WaitingRoomResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().algorithm()).isEqualTo("FIFO");
    }

    @Test
    void shouldUpdateExistingWaitingRoom() {
        WaitingRoomResponseDTO createdRoom = restTemplate.postForObject("/api/v1/waiting-rooms", new WaitingRoomRequestDTO(
                "PRIORITY", 10, Mode.FULL_TIME), WaitingRoomResponseDTO.class);

        WaitingRoomRequestDTO updateRequest = new WaitingRoomRequestDTO(
                "EPT", 20, Mode.PART_TIME);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<WaitingRoomRequestDTO> requestEntity = new HttpEntity<>(updateRequest, headers);

        ResponseEntity<WaitingRoomResponseDTO> response = restTemplate.exchange(
                "/api/v1/waiting-rooms/" + createdRoom.id(), HttpMethod.PUT, requestEntity, WaitingRoomResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().algorithm()).isEqualTo("EPT");
        assertThat(response.getBody().capacity()).isEqualTo(20);
    }
}