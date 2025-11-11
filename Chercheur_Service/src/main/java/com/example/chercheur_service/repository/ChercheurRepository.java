package com.example.chercheur_service.repository;

import com.example.chercheur_service.entities.Chercheur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChercheurRepository extends JpaRepository<Chercheur, Long> {
}
