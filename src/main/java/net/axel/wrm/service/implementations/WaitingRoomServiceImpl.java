package net.axel.wrm.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.mapper.WaitingRoomMapper;
import net.axel.wrm.repository.WaitingRoomRepository;
import net.axel.wrm.service.WaitingRoomService;
import org.springframework.stereotype.Service;

@Service
@Transactional

@RequiredArgsConstructor
public class WaitingRoomServiceImpl implements WaitingRoomService {

    private final WaitingRoomRepository repository;
    private final WaitingRoomMapper mapper;
}
