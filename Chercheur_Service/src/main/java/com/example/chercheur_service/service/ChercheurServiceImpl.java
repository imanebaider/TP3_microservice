package com.example.chercheur_service.service;

import com.example.chercheur_service.dto.ChercheurRequestDto;
import com.example.chercheur_service.dto.ChercheurResponseDto;
import com.example.chercheur_service.entities.Chercheur;
import com.example.chercheur_service.mapper.ChercheurMapper;
import com.example.chercheur_service.repository.ChercheurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChercheurServiceImpl implements ChercheurService {

    private final ChercheurRepository repository;
    private final ChercheurMapper mapper;

    public ChercheurServiceImpl(ChercheurRepository repository, ChercheurMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChercheurResponseDto createChercheur(ChercheurRequestDto dto) {
        Chercheur c = mapper.dtoToEntity(dto);
        Chercheur saved = repository.save(c);
        return mapper.entityToDto(saved);
    }

    @Override
    public ChercheurResponseDto getChercheurById(Long id) {
        Chercheur c = repository.findById(id).orElseThrow(() -> new RuntimeException("Chercheur not found"));
        return mapper.entityToDto(c);
    }

    @Override
    public List<ChercheurResponseDto> getAllChercheurs() {
        return repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ChercheurResponseDto updateChercheur(Long id, ChercheurRequestDto dto) {
        Chercheur c = repository.findById(id).orElseThrow(() -> new RuntimeException("Chercheur not found"));
        c.setNom(dto.getNom());
        c.setPrenom(dto.getPrenom());
        c.setNumInscription(dto.getNumInscription());
        c.setEnseignantId(dto.getEnseignantId());
        Chercheur updated = repository.save(c);
        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteChercheur(Long id) {
        repository.deleteById(id);
    }
}
