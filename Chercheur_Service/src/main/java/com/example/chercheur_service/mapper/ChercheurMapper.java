package com.example.chercheur_service.mapper;

import com.example.chercheur_service.dto.ChercheurRequestDto;
import com.example.chercheur_service.dto.ChercheurResponseDto;
import com.example.chercheur_service.entities.Chercheur;
import org.springframework.stereotype.Component;

@Component
public class ChercheurMapper {

    // Convertir Request DTO en Entity
    public Chercheur dtoToEntity(ChercheurRequestDto dto) {
        Chercheur c = new Chercheur();
        c.setNom(dto.getNom());
        c.setPrenom(dto.getPrenom());
        c.setNumInscription(dto.getNumInscription());
        c.setEmail(dto.getEmail());
        c.setEnseignantId(dto.getEnseignantId());
        return c;
    }

    // Convertir Entity en Response DTO
    public ChercheurResponseDto entityToDto(Chercheur c) {
        ChercheurResponseDto dto = new ChercheurResponseDto();
        dto.setId(c.getId());
        dto.setNom(c.getNom());
        dto.setPrenom(c.getPrenom());
        dto.setNumInscription(c.getNumInscription());
        dto.setEmail(c.getEmail());
        dto.setEnseignantId(c.getEnseignantId());
        return dto;
    }
}
