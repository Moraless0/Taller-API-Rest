package com.example.gimnasio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Información de una rutina")
public class RutinaResponse {

    @Schema(example = "1")
    Long id;

    @Schema(example = "Entrenamiento funcional")
    String nombre;

    @Schema(example = "BASICO")
    String nivel;

    @Schema(description = "Clientes asignados a la rutina")
    List<ClienteResumenResponse> clientes;

    public RutinaResponse() {
    }

    public RutinaResponse(Long id, String nombre, String nivel,
                          List<ClienteResumenResponse> clientes) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<ClienteResumenResponse> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteResumenResponse> clientes) {
        this.clientes = clientes;
    }
}
