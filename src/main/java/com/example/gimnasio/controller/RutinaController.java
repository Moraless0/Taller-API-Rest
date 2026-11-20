package com.example.gimnasio.controller;

import com.example.gimnasio.dto.request.RutinaRequest;
import com.example.gimnasio.dto.response.ClienteResumenResponse;
import com.example.gimnasio.dto.response.RutinaResponse;
import com.example.gimnasio.service.RutinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@Tag(
        name = "Rutinas",
        description = "Operaciones para administrar rutinas del gimnasio"
)
public class RutinaController {

    private final RutinaService rutinaService;

    public RutinaController(RutinaService rutinaService) {
        this.rutinaService = rutinaService;
    }

    @Operation(
            summary = "Crear una rutina",
            description = "Registra una nueva rutina en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Rutina creada correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos enviados no son válidos",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<RutinaResponse> crearRutina(
            @Valid @RequestBody RutinaRequest request) {

        RutinaResponse respuesta = rutinaService.crearRutina(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<RutinaResponse>> listarRutinas() {

        return ResponseEntity.ok(
                rutinaService.listarRutinas()
        );
    }

    @Operation(
            summary = "Consultar rutina",
            description = "Obtiene una rutina por su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rutina encontrada",
                    content = @Content(
                            schema = @Schema(implementation = RutinaResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<RutinaResponse> buscarRutina(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                rutinaService.buscarRutina(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutinaResponse> actualizarRutina(
            @PathVariable Long id,
            @Valid @RequestBody RutinaRequest request) {

        return ResponseEntity.ok(
                rutinaService.actualizarRutina(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRutina(
            @PathVariable Long id) {

        rutinaService.eliminarRutina(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{rutinaId}/clientes")
    public ResponseEntity<List<ClienteResumenResponse>> listarClientesDeRutina(
            @PathVariable Long rutinaId) {

        return ResponseEntity.ok(
                rutinaService.listarClientesDeRutina(rutinaId)
        );
    }
}
