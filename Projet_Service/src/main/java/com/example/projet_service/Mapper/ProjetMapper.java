package com.example.projet_service.mapper;

import com.example.projet_service.DTO.ProjetRequestDto;
import com.example.projet_service.DTO.ProjetResponseDto;
import com.example.projet_service.entities.Projet;
import org.springframework.stereotype.Component;

@Component
public class ProjetMapper {

    public Projet dtoToEntity(ProjetRequestDto dto) {
        Projet projet = new Projet();
        projet.setTitre(dto.getTitre());
        projet.setDescription(dto.getDescription());
        projet.setChercheurId(dto.getChercheurId());
        projet.setEnseignantId(dto.getEnseignantId());
        return projet;
    }

    public ProjetResponseDto entityToDto(Projet projet) {
        ProjetResponseDto dto = new ProjetResponseDto();
        dto.setId(projet.getId());
        dto.setTitre(projet.getTitre());
        dto.setDescription(projet.getDescription());
        dto.setChercheurId(projet.getChercheurId());
        dto.setEnseignantId(projet.getEnseignantId());
        return dto;
    }
}
