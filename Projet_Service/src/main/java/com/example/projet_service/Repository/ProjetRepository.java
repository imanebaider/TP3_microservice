package com.example.projet_service.Repository;
import com.example.projet_service.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
    List<Projet> findByChercheurId(Long chercheurId);
    List<Projet> findByEnseignantId(Long enseignantId);
}
