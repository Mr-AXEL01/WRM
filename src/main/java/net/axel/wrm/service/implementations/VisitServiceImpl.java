package net.axel.wrm.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.axel.wrm.domain.dtos.visit.VisitRequestDTO;
import net.axel.wrm.domain.dtos.visit.VisitResponseDTO;
import net.axel.wrm.mapper.VisitMapper;
import net.axel.wrm.repository.VisitRepository;
import net.axel.wrm.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository repository;
    private final VisitMapper mapper;


    @Override
    public List<VisitResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public VisitResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("visit not found with id : " + id));
    }

    @Override
    public VisitResponseDTO create(VisitRequestDTO dto) {
        return null;
    }

    @Override
    public VisitResponseDTO update(Long id, VisitRequestDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
