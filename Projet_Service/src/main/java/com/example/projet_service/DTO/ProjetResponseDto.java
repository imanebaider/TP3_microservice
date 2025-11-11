package com.example.projet_service.DTO;
import lombok.Data;

@Data
public class ProjetResponseDto {
    private Long id;
    private String titre;
    private String description;
    private Long chercheurId;
    private Long enseignantId;


}
