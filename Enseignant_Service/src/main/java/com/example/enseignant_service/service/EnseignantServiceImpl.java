package com.example.enseignant_service.service;

import com.example.enseignant_service.dto.EnseignantRequestDto;
import com.example.enseignant_service.dto.EnseignantResponseDto;
import com.example.enseignant_service.entities.Enseignant;
import com.example.enseignant_service.mapper.EnseignantMapper;
import com.example.enseignant_service.repository.EnseignantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EnseignantServiceImpl implements EnseignantService {
    private final EnseignantRepository enseignantRepository;
    private final EnseignantMapper enseignantMapper;

    public EnseignantServiceImpl(EnseignantRepository enseignantRepository, EnseignantMapper enseignantMapper) {
        this.enseignantRepository = enseignantRepository;
        this.enseignantMapper = enseignantMapper;
    }

    @Override
    public EnseignantResponseDto createEnseignant(EnseignantRequestDto dto) {
        Enseignant enseignant = enseignantMapper.dtoToEntity(dto);
        Enseignant saved = enseignantRepository.save(enseignant);
        return enseignantMapper.entityToDto(saved);
    }

    @Override
    public EnseignantResponseDto updateEnseignant(Long id, EnseignantRequestDto dto) {
        Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(id);
        if (optionalEnseignant.isPresent()) {
            Enseignant enseignant = optionalEnseignant.get();
            enseignant.setNom(dto.getNom());
            enseignant.setPrenom(dto.getPrenom());
            enseignant.setCne(dto.getCne());
            enseignant.setEmail(dto.getEmail());
            enseignant.setMotDePasse(dto.getMotDePasse());
            enseignant.setThematiqueRecherche(dto.getThematiqueRecherche());
            Enseignant updated = enseignantRepository.save(enseignant);
            return enseignantMapper.entityToDto(updated);
        }
        return null; // أو Exception
    }

    @Override
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }

    @Override
    public List<EnseignantResponseDto> getAllEnseignants() {
        return enseignantRepository.findAll()
                .stream()
                .map(enseignantMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnseignantResponseDto getEnseignantById(Long id) {
        Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(id);
        return optionalEnseignant.map(enseignantMapper::entityToDto).orElse(null);
    }
}
