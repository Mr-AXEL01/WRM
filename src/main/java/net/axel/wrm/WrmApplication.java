package net.axel.wrm;

import net.axel.wrm.domain.entities.Visitor;
import net.axel.wrm.repository.VisitorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(WrmApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(VisitorRepository visitorRepository) {
        return args -> {
            visitorRepository.save(Visitor.builder().firstName("Aymane").lastName("El maini").build());
            visitorRepository.save(Visitor.builder().firstName("Soufiane").lastName("Bouanani").build());
            visitorRepository.save(Visitor.builder().firstName("Hamza").lastName("Lamin").build());
            visitorRepository.save(Visitor.builder().firstName("Mohammed").lastName("Daali").build());
            visitorRepository.save(Visitor.builder().firstName("Zakaria").lastName("El hassad").build());
        };
    }

}
