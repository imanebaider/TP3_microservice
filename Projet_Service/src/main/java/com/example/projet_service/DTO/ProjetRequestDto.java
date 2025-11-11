package com.example.projet_service.DTO;
import lombok.Data;

@Data
public class ProjetRequestDto {
    private String titre;
    private String description;
    private Long chercheurId;
    private Long enseignantId;
}