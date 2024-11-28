package net.axel.wrm;

import net.axel.wrm.domain.entities.Visitor;
import net.axel.wrm.domain.entities.WaitingRoom;
import net.axel.wrm.domain.enums.Mode;
import net.axel.wrm.repository.VisitorRepository;
import net.axel.wrm.repository.WaitingRoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class WrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(WrmApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(VisitorRepository visitorRepository, WaitingRoomRepository waitingRoomRepository) {
        return args -> {
            visitorRepository.save(Visitor.builder().firstName("Aymane").lastName("El maini").build());
            visitorRepository.save(Visitor.builder().firstName("Soufiane").lastName("Bouanani").build());
            visitorRepository.save(Visitor.builder().firstName("Hamza").lastName("Lamin").build());
            visitorRepository.save(Visitor.builder().firstName("Mohammed").lastName("Daali").build());
            visitorRepository.save(Visitor.builder().firstName("Zakaria").lastName("El hassad").build());

            waitingRoomRepository.save(WaitingRoom.builder().date(LocalDate.now()).mode(Mode.PART_TIME).algorithm("PRIORITY").capacity(18).build());
            waitingRoomRepository.save(WaitingRoom.builder().date(LocalDate.of(2024, 11, 20)).mode(Mode.FULL_TIME).algorithm("EPT").capacity(10).build());
            waitingRoomRepository.save(WaitingRoom.builder().date(LocalDate.of(2024, 11, 22)).mode(Mode.PART_TIME).algorithm("PRIORITY").capacity(18).build());
            waitingRoomRepository.save(WaitingRoom.builder().date(LocalDate.of(2024, 12, 17)).mode(Mode.PART_TIME).algorithm("FIFO").capacity(10).build());
            waitingRoomRepository.save(WaitingRoom.builder().date(LocalDate.of(2024, 12, 8)).mode(Mode.FULL_TIME).algorithm("PRIORITY").capacity(12).build());
        };
    }

}
