package com.example.chercheur_service.web;

import com.example.chercheur_service.dto.ChercheurRequestDto;
import com.example.chercheur_service.dto.ChercheurResponseDto;
import com.example.chercheur_service.service.ChercheurService;
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

// Documentation globale de l'API
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion des chercheurs",
                description = "Cette API permet de gérer les chercheurs (CRUD)",
                version = "1.0.0"
        ),
        servers = @Server(url = "http://localhost:8083") // change le port si besoin
)
@RestController
@RequestMapping("/api/chercheurs")
public class ChercheurController {

    @Autowired
    private ChercheurService service;

    @Operation(
            summary = "Ajouter un chercheur",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ChercheurRequestDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chercheur créé",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChercheurResponseDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<ChercheurResponseDto> create(@RequestBody ChercheurRequestDto dto) {
        return ResponseEntity.ok(service.createChercheur(dto));
    }

    @Operation(
            summary = "Récupérer tous les chercheurs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des chercheurs",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ChercheurResponseDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ChercheurResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAllChercheurs());
    }

    @Operation(
            summary = "Récupérer un chercheur par ID",
            parameters = @Parameter(name = "id", description = "ID du chercheur", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Chercheur récupéré",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChercheurResponseDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ChercheurResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getChercheurById(id));
    }

    @Operation(
            summary = "Mettre à jour un chercheur",
            parameters = @Parameter(name = "id", description = "ID du chercheur", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ChercheurRequestDto.class)
                    )
            )
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ChercheurResponseDto> update(@PathVariable Long id, @RequestBody ChercheurRequestDto dto) {
        return ResponseEntity.ok(service.updateChercheur(id, dto));
    }

    @Operation(
            summary = "Supprimer un chercheur",
            parameters = @Parameter(name = "id", description = "ID du chercheur", required = true)
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteChercheur(id);
        return ResponseEntity.ok().build();
    }
}
