package com.example.enseignant_service.service;

import com.example.enseignant_service.dto.EnseignantRequestDto;
import com.example.enseignant_service.dto.EnseignantResponseDto;

import java.util.List;

public interface EnseignantService {

    // Utilise RequestDto pour créer
    EnseignantResponseDto createEnseignant(EnseignantRequestDto dto);

    // Récupération par id renvoie ResponseDto
    EnseignantResponseDto getEnseignantById(Long id);

    // Liste de tous les enseignants
    List<EnseignantResponseDto> getAllEnseignants();

    // Mise à jour utilise RequestDto et renvoie ResponseDto
    EnseignantResponseDto updateEnseignant(Long id, EnseignantRequestDto dto);

    // Suppression par id
    void deleteEnseignant(Long id);
}
