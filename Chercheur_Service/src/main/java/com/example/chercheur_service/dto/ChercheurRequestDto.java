package com.example.chercheur_service.dto;

import lombok.Data;

@Data
public class ChercheurRequestDto {
    private String nom;
    private String prenom;
    private String numInscription;
    private String email;
    private Long enseignantId;
}
