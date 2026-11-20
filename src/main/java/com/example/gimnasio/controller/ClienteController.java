package com.example.gimnasio.controller;

import com.example.gimnasio.dto.request.ClienteRequest;
import com.example.gimnasio.dto.response.ClienteResponse;
import com.example.gimnasio.dto.response.RutinaResumenResponse;
import com.example.gimnasio.service.ClienteService;
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
@RequestMapping("/api/clientes")
@Tag(
        name = "Clientes",
        description = "Operaciones para administrar clientes del gimnasio"
)
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(
            summary = "Crear un cliente",
            description = "Registra un nuevo cliente en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos enviados no son válidos",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(
            @Valid @RequestBody ClienteRequest request) {

        ClienteResponse respuesta = clienteService.crearCliente(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {

        return ResponseEntity.ok(
                clienteService.listarClientes()
        );
    }

    @Operation(
            summary = "Consultar cliente",
            description = "Obtiene un cliente por su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado",
                    content = @Content(
                            schema = @Schema(implementation = ClienteResponse.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarCliente(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                clienteService.buscarCliente(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request) {

        return ResponseEntity.ok(
                clienteService.actualizarCliente(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
            @PathVariable Long id) {

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clienteId}/rutinas/{rutinaId}")
    public ResponseEntity<ClienteResponse> asignarRutina(
            @PathVariable Long clienteId,
            @PathVariable Long rutinaId) {

        return ResponseEntity.ok(
                clienteService.asignarRutina(clienteId, rutinaId)
        );
    }

    @GetMapping("/{clienteId}/rutinas")
    public ResponseEntity<List<RutinaResumenResponse>> listarRutinasDeCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                clienteService.listarRutinasDeCliente(clienteId)
        );
    }

    @DeleteMapping("/{clienteId}/rutinas/{rutinaId}")
    public ResponseEntity<Void> quitarRutina(
            @PathVariable Long clienteId,
            @PathVariable Long rutinaId) {

        clienteService.quitarRutina(clienteId, rutinaId);

        return ResponseEntity.noContent().build();
    }
}
