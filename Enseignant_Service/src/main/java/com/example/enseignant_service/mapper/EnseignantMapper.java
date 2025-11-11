package com.example.enseignant_service.mapper;

import com.example.enseignant_service.dto.EnseignantRequestDto;
import com.example.enseignant_service.dto.EnseignantResponseDto;
import com.example.enseignant_service.entities.Enseignant;
import org.springframework.stereotype.Component;

@Component
public class EnseignantMapper {

    // تحويل Request DTO إلى Entity
    public Enseignant dtoToEntity(EnseignantRequestDto dto) {
        Enseignant enseignant = new Enseignant(); // نستعمل الكونستركتور الفارغ
        enseignant.setNom(dto.getNom());
        enseignant.setPrenom(dto.getPrenom());
        enseignant.setCne(dto.getCne());
        enseignant.setEmail(dto.getEmail());
        enseignant.setMotDePasse(dto.getMotDePasse());
        enseignant.setThematiqueRecherche(dto.getThematiqueRecherche());
        return enseignant;
    }

    // تحويل Entity إلى Response DTO
    public EnseignantResponseDto entityToDto(Enseignant enseignant) {
        EnseignantResponseDto dto = new EnseignantResponseDto();
        dto.setId(enseignant.getId());
        dto.setNom(enseignant.getNom());
        dto.setPrenom(enseignant.getPrenom());
        dto.setCne(enseignant.getCne());
        dto.setEmail(enseignant.getEmail());
        dto.setThematiqueRecherche(enseignant.getThematiqueRecherche());
        return dto;
    }
}
