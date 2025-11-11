package com.example.enseignant_service.web;
import com.example.enseignant_service.dto.EnseignantRequestDto;
import com.example.enseignant_service.dto.EnseignantResponseDto;
import com.example.enseignant_service.service.EnseignantService;

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
                title = "Gestion des enseignants",
                description = "Cette API permet de gérer les enseignants (CRUD)",
                version = "1.0.0"
        ),
        servers = @Server(url = "http://localhost:8080")
)
@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {

    @Autowired
    private EnseignantService service;

    @Operation(
            summary = "Ajouter un enseignant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnseignantRequestDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Enseignant créé",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EnseignantResponseDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<EnseignantResponseDto> create(@RequestBody EnseignantRequestDto dto) {
        return ResponseEntity.ok(service.createEnseignant(dto));
    }

    @Operation(
            summary = "Récupérer tous les enseignants",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des enseignants",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EnseignantResponseDto.class))
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<EnseignantResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAllEnseignants());
    }

    @Operation(
            summary = "Récupérer un enseignant par ID",
            parameters = @Parameter(name = "id", description = "ID de l'enseignant", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Enseignant récupéré",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EnseignantResponseDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EnseignantResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEnseignantById(id));
    }

    @Operation(
            summary = "Mettre à jour un enseignant",
            parameters = @Parameter(name = "id", description = "ID de l'enseignant à mettre à jour", required = true),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnseignantRequestDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Enseignant mis à jour",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EnseignantResponseDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EnseignantResponseDto> update(@PathVariable Long id, @RequestBody EnseignantRequestDto dto) {
        return ResponseEntity.ok(service.updateEnseignant(id, dto));
    }

    @Operation(
            summary = "Supprimer un enseignant",
            parameters = @Parameter(name = "id", description = "ID de l'enseignant à supprimer", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Enseignant supprimé"),
                    @ApiResponse(responseCode = "4xx", description = "Erreur client"),
                    @ApiResponse(responseCode = "5xx", description = "Erreur serveur")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEnseignant(id);
        return ResponseEntity.noContent().build();
    }
}
