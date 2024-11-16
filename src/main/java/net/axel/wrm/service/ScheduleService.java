package net.axel.wrm.service;

import net.axel.wrm.domain.entities.Visit;

import java.util.List;

public interface ScheduleService {
    List<Visit> schedule(List<Visit> visits);
}
