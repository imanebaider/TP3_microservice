package com.example.projet_service.Service;


import com.example.projet_service.DTO.ProjetRequestDto;
import com.example.projet_service.DTO.ProjetResponseDto;
import com.example.projet_service.entities.Projet;
import com.example.projet_service.mapper.ProjetMapper;
import com.example.projet_service.Repository.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetServiceImpl {

    private final ProjetRepository projetRepository;
    private final ProjetMapper projetMapper;

    public ProjetServiceImpl(ProjetRepository projetRepository, ProjetMapper projetMapper) {
        this.projetRepository = projetRepository;
        this.projetMapper = projetMapper;
    }

    public ProjetResponseDto ajouterProjet(ProjetRequestDto dto){
        Projet p = projetMapper.dtoToEntity(dto);
        Projet saved = projetRepository.save(p);
        return projetMapper.entityToDto(saved);
    }

    public List<ProjetResponseDto> listerProjets(){
        return projetRepository.findAll().stream().map(projetMapper::entityToDto).collect(Collectors.toList());
    }

    public ProjetResponseDto getProjetById(Long id){
        Optional<Projet> opt = projetRepository.findById(id);
        return opt.map(projetMapper::entityToDto).orElse(null);
    }

    public List<ProjetResponseDto> getProjetsByChercheurId(Long chercheurId){
        return projetRepository.findByChercheurId(chercheurId).stream().map(projetMapper::entityToDto).collect(Collectors.toList());
    }

    public List<ProjetResponseDto> getProjetsByEnseignantId(Long enseignantId){
        return projetRepository.findByEnseignantId(enseignantId).stream().map(projetMapper::entityToDto).collect(Collectors.toList());
    }

    public void supprimerProjet(Long id){
        projetRepository.deleteById(id);
    }
}

