package net.axel.wrm.service.implementations.strategies;

import net.axel.wrm.domain.entities.Visit;
import net.axel.wrm.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service("FIFO")
public class FifoSchedulingStrategy implements ScheduleService {
    @Override
    public List<Visit> schedule(List<Visit> visits) {
        return visits.stream()
                .sorted(Comparator.comparing(Visit::getArrivalTime))
                .toList();
    }
}