package com.example.projet_service.web;

import com.example.projet_service.DTO.ProjetRequestDto;
import com.example.projet_service.DTO.ProjetResponseDto;
import com.example.projet_service.Service.ProjetServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des projets",
                description = "Cette API permet de gérer les projets (CRUD)",
                version = "1.0.0"
        ),
        servers = @Server(url = "http://localhost:8084") // change le port selon ton Projet microservice
)
@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    @Autowired
    private ProjetServiceImpl service;

    @Operation(
            summary = "Ajouter un projet",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjetRequestDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Projet créé",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetResponseDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProjetResponseDto> create(@RequestBody ProjetRequestDto dto) {
        return ResponseEntity.ok(service.ajouterProjet(dto));
    }

    @Operation(
            summary = "Récupérer tous les projets",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des projets",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProjetResponseDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProjetResponseDto>> getAll() {
        return ResponseEntity.ok(service.listerProjets());
    }

    @Operation(
            summary = "Récupérer un projet par ID",
            parameters = @Parameter(name = "id", description = "ID du projet", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Projet récupéré",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProjetResponseDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjetResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProjetById(id));
    }

    @Operation(
            summary = "Récupérer les projets d'un chercheur",
            parameters = @Parameter(name = "chercheurId", description = "ID du chercheur", required = true)
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/chercheur/{chercheurId}")
    public ResponseEntity<List<ProjetResponseDto>> getByChercheur(@PathVariable Long chercheurId) {
        return ResponseEntity.ok(service.getProjetsByChercheurId(chercheurId));
    }

    @Operation(
            summary = "Récupérer les projets d'un enseignant",
            parameters = @Parameter(name = "enseignantId", description = "ID de l'enseignant", required = true)
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<ProjetResponseDto>> getByEnseignant(@PathVariable Long enseignantId) {
        return ResponseEntity.ok(service.getProjetsByEnseignantId(enseignantId));
    }

    @Operation(
            summary = "Supprimer un projet",
            parameters = @Parameter(name = "id", description = "ID du projet", required = true)
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.supprimerProjet(id);
        return ResponseEntity.ok().build();
    }
}
