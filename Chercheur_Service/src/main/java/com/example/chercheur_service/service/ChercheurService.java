package com.example.chercheur_service.service;

import com.example.chercheur_service.dto.ChercheurRequestDto;
import com.example.chercheur_service.dto.ChercheurResponseDto;
import java.util.List;

public interface ChercheurService {
    ChercheurResponseDto createChercheur(ChercheurRequestDto dto);
    ChercheurResponseDto getChercheurById(Long id);
    List<ChercheurResponseDto> getAllChercheurs();
    ChercheurResponseDto updateChercheur(Long id, ChercheurRequestDto dto);
    void deleteChercheur(Long id);
}
